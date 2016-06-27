package net.arkaine.pc.mapeditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 17/06/16.
 */
public class Editeur extends JFrame{
    private JCheckBox traversableCheckBox;
    private JTextField textField1;
    private JButton decreaseIndex;
    private JButton increaseIndex;

    private JPanel all;
    private JPanel casesContener;


    private MouseAdapter caseEvent = new MouseAdapterJPanel();

    public Editeur(){
        setContentPane(all);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1024,800);
        setSize(1024,800);
//        setLayout(new GridBagLayout());
        List<JPanel> cases = new ArrayList<>();

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



}
