package net.arkaine.pc.mapeditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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

    private JTextField filename = new JTextField(), dir = new JTextField();

    private static MapLoader map = new MapLoader(10000,10000,11);
    private JPanel all;
    private JPanel casesContener;

    private JPanel selectImage2;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel murs;
    private JPanel sols;
    private List<SelectPanel> panelsMurs = new ArrayList<>();;
    private List<SelectPanel> panelsSols = new ArrayList<>();;

    private Map<Integer,Image> imagesMurs = Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/mursShoot/");
    private Map<Integer,Image> imagesSols = Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/solsShoot/");

    private MouseAdapter caseEvent = new MouseAdapterJPanel();

    public Editeur(){
        this.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e)
            {
    /* code run when component hidden*/
            }
            public void componentShown(ComponentEvent e) {

            }
        });
        setContentPane(all);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1024,800);
        setSize(1024,800);
        List<JPanel> cases = new ArrayList<>();
        loadButton.addActionListener(new OpenL());
        saveButton.addActionListener(new SaveL());

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

        int indexImg = 0 ;
        murs.setLayout(new GridBagLayout());
        for(Image image: imagesMurs.values()){
            SelectPanel panel = new SelectPanel(image);
            String name = "selectMur"+indexImg;
            panel.setName(name);
            panel.setBounds(indexImg*40,400,40,40);
            panel.setVisible(true);
            panel.setBorder(new LineBorder(Color.black));
            panel.setBackground(Color.BLUE);
            cases.add(panel);
            c.gridx=indexImg;
            c.gridy=0;
            murs.add(panel, c);
            panelsMurs.add(panel);
            indexImg ++;
            final int finalIndexImg = indexImg;
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changeSelectImage(finalIndexImg);
                }
            });
        }

        indexImg = 0 ;
        sols.setLayout(new GridBagLayout());
        for(Image image: imagesSols.values()){
            SelectPanel panel = new SelectPanel(image);
            String name = "selectMur"+indexImg;
            panel.setName(name);
            panel.setBounds(indexImg*40,400,40,40);
            panel.setVisible(true);
            panel.setBorder(new LineBorder(Color.black));
            panel.setBackground(Color.BLUE);
            cases.add(panel);
            c.gridx=indexImg;
            c.gridy=0;
            sols.add(panel, c);
            panelsSols.add(panel);
            indexImg ++;
            final int finalIndexImg = indexImg;
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changeSelectImage(finalIndexImg);
                }
            });
        }
    }

    //TODO : en cours
    private void changeSelectImage(int index){
        selectImage = index;
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
                map.load(c.getCurrentDirectory().toString() + File.separatorChar + c.getSelectedFile().getName());
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
