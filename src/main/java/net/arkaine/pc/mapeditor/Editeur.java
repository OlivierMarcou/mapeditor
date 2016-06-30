package net.arkaine.pc.mapeditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by olivier on 17/06/16.
 */
public class Editeur extends JFrame{
    private int selectImage = 1;
    private JCheckBox traversableCheckBox;
    private JTextField indexImage;
    private JButton decreaseIndex;
    private JButton increaseIndex;

    private JTextField filename = new JTextField(), dir = new JTextField();

    private JPanel all;
    private JPanel casesContener;

    private JPanel selectImage2;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel selectImage0;
    private JPanel selectImage1;
    private JPanel selectImage3;
    private JPanel selectImage4;
    private Map<Integer,Image> images = Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/mursShoot/");

    private MouseAdapter caseEvent = new MouseAdapterJPanel();

    public Editeur(){
        setContentPane(all);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1024,800);
        setSize(1024,800);
//        setLayout(new GridBagLayout());
        List<JPanel> cases = new ArrayList<>();
        loadButton.addActionListener(new OpenL());
        saveButton.addActionListener(new SaveL());
        decreaseIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSelectImage(-1);
            }
        });


        increaseIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSelectImage(1);
            }
        });
        setVisible(false);
        all.setBounds(0,0,1024,800);
        all.setAlignmentX(0.0f);
        casesContener.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int x = 0; x<20 ; x++){
            for (int y = 0; y<20 ; y++){
                JPanel panel = new JPanel();
                String name = "map_x"+x+"y"+y;
                panel.setName(name);
                panel.setBounds(x*20,y*20,20,20);
               // panel.add(new JLabel(x + " " + y));
                panel.setVisible(true);
                panel.setBorder(new LineBorder(Color.black));
                panel.setBackground(Color.WHITE);
                panel.addMouseListener(caseEvent);
                cases.add(panel);

//                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx=x;
                c.gridy=y+1;
                casesContener.add(panel, c);
            }
        }
        selectImage0
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        changeSelectImage( -2);
                    }
                });
        selectImage1
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        changeSelectImage( -1);
                    }
                });
        selectImage2
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        changeSelectImage( 0);
                    }
                });
        selectImage3
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        changeSelectImage( +1);
                    }
                });
        selectImage4
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        changeSelectImage( +2);
                    }
                });
        changeSelectImage(2);
    }

    //TODO : en cours
    private void changeSelectImage(int modifier){
        selectImage = selectImage + modifier;
        selectImage0.getGraphics().drawImage(images.get(selectImage-2),0,0,null);
        selectImage1.getGraphics().drawImage(images.get(selectImage-1),0,0,null);
        selectImage2.getGraphics().drawImage(images.get(selectImage),0,0,null);
        selectImage3.getGraphics().drawImage(images.get((selectImage+1)),0,0,null);
        selectImage4.getGraphics().drawImage(images.get(selectImage+2),0,0,null);
        indexImage.setMaximumSize(new Dimension(20,20));
        decreaseIndex.setMaximumSize(new Dimension(20,20));
        increaseIndex.setMaximumSize(new Dimension(20,20));
        selectImage2.setMaximumSize(new Dimension(20,20));
        selectImage4.setMaximumSize(new Dimension(20,20));
        indexImage.setText(String.valueOf(selectImage));
    }



    public static void showOnScreen( int screen, JFrame frame )
    {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if( screen > -1 && screen < gs.length )
        {
            frame.setLocation(gs[screen].getDefaultConfiguration().getBounds().x, frame.getY());
            frame.setVisible(true);
//            gs[screen].setFullScreenWindow( frame );
        }
        else if( gs.length > 0 )
        {
            frame.setLocation(gs[0].getDefaultConfiguration().getBounds().x, frame.getY());
            frame.setVisible(true);
//            gs[0].setFullScreenWindow( frame );
        }
        else
        {
            throw new RuntimeException( "No Screens Found" );
        }
    }


    class OpenL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser(System.getenv("HOME"));
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(Editeur.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    class SaveL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser(System.getenv("HOME"));
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(Editeur.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

}
