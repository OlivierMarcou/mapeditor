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

    private static int tailleMapX = 100;
    private static int tailleMapY = 100;
    private static int tailleMapZ = 10;
    private static int caseSize = 5;

    public static int currentZ = 0;
    public static int selectCase = 0;

    private static MapLoader map = new MapLoader(tailleMapX, tailleMapY, tailleMapZ);
    private JPanel all = new JPanel();
    private JMap casesContener = new JMap(map, caseSize);

    private JButton loadButton = new JButton("Load");
    private JButton saveButton = new JButton("Save");

    private JButton haut = new JButton("/\\");
    private JButton bas = new JButton("\\/");

    private JTextField filename = new JTextField(), dir = new JTextField();
    private JLabel transparence = new JLabel("Transparence");
    private JCheckBox traversableCheckBox = new JCheckBox("Traversable");
    private JLabel lumiere= new JLabel("Lumiere");
    private JLabel atmosphere = new JLabel("Atmosphere");
    private JLabel life = new JLabel("Life");
    private JLabel typeCase = new JLabel("Type case :");

    private List<SelectPanel> panelsMurs = new ArrayList<>();
    private List<SelectPanel> panelsSols = new ArrayList<>();

    private JTextField indexImage = new JTextField(0);
    private JPanel murs = new JPanel();
    private JPanel sols = new JPanel();

    private Map<Integer,Image> imagesMurs = Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/mursShoot/");
    private Map<Integer,Image> imagesSols = Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/solsShoot/");


    public Editeur(){
        this.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e)
            {
    /* code run when component hidden*/
            }
            public void componentShown(ComponentEvent e) {

            }
        });

        map.loadImagesShoot("/home/olivier/workspace/mapeditor/src/main/resources/");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1024,800);
        setSize(1024,800);
        loadButton.addActionListener(new OpenL());
        saveButton.addActionListener(new SaveL());
        haut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentZ<tailleMapZ)
                    currentZ++;
                afficheBarImage();
            }
        });
        bas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentZ>0)
                    currentZ--;
                afficheBarImage();
            }
        });
        setLayout(new GridBagLayout());
        setContentPane(all);
        all.setBorder(new LineBorder(Color.black));
        all.setLayout(new GridBagLayout());

        all.setBounds(0,0,1024,800);
        all.setAlignmentX(0.0f);

        casesContener.setLayout(new GridBagLayout());
        casesContener.setPreferredSize(new Dimension(caseSize*tailleMapX,caseSize*tailleMapY));
        casesContener.setBorder(new LineBorder(Color.BLACK));
        casesContener.addMouseListener(new MouseAdapterJMap());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight= 11;
        all.add(casesContener, c);

        c.gridx=1;
        c.gridy=1;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(loadButton, c);

        c.gridx=1;
        c.gridy=2;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(saveButton, c);

        c.gridx=1;
        c.gridy=3;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(transparence , c);

        c.gridx=1;
        c.gridy=4;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(traversableCheckBox , c);

        c.gridx=1;
        c.gridy=5;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(lumiere, c);

        c.gridx=1;
        c.gridy=6;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(atmosphere , c);

        c.gridx=1;
        c.gridy=7;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(life , c);

        c.gridx=1;
        c.gridy=8;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(typeCase , c);

        c.gridx=1;
        c.gridy=9;
        c.gridwidth=1;
        c.gridheight= 1;
        all.add(haut , c);

        c.gridx=1;
        c.gridy=10;
        c.gridwidth=1;
        c.gridheight= 1;
        c.anchor = GridBagConstraints.NORTH;
        all.add(bas , c);

//-----------------------------------------------------------------
        c.gridx=0;
        c.gridy=11;
        c.gridwidth=1;
        c.gridheight= 1;
        indexImage.setPreferredSize(new Dimension(60,20));
        all.add(indexImage, c);

        c.gridx=0;
        c.gridy=12;
        c.gridwidth=3;
        c.gridheight= 1;
        all.add(murs, c);

        c.gridx=0;
        c.gridy=13;
        c.gridwidth=3;
        c.gridheight= 1;
        all.add(sols, c);

        setVisible(false);
        for (int x = 0; x<tailleMapX ; x++){
            for (int y = 0; y<tailleMapY ; y++){
                String name = "map_x"+x+"y"+y;


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
            c.gridx=indexImg;
            c.gridy=0;
            c.gridwidth = 1;
            c.gridheight = 1;
            murs.add(panel, c);
            panelsMurs.add(panel);
            final int finalIndexImg = indexImg;
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changeSelectImage(finalIndexImg);
                }
            });
            indexImg ++;
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
            c.gridx=indexImg;
            c.gridy=0;
            c.gridwidth = 1;
            c.gridheight = 1;
            sols.add(panel, c);
            panelsSols.add(panel);
            final int finalIndexImg = indexImg;
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changeSelectImage(finalIndexImg);
                }
            });
            indexImg ++;
        }
        afficheBarImage();
    }

    private void afficheBarImage() {
        if(currentZ%2==0){
            sols.setVisible(true);
            murs.setVisible(false);
        }else{
            murs.setVisible(true);
            sols.setVisible(false);
        }
    }

    private void changeSelectImage(int index){
        selectCase = index;
        indexImage.setText(String.valueOf(index));
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
