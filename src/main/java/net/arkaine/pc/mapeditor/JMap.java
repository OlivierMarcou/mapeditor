package net.arkaine.pc.mapeditor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by olivier on 05/07/16.
 */
public class JMap extends JPanel {

    private MapLoader map;
    private int caseSize=0;

    public JMap (MapLoader map, int caseSize) {
        this.map = map;
        this.caseSize = caseSize;
    }

    public void drawCase(String selectCase, int x, int y, int z){
        map.carte[x/caseSize][y/caseSize][z]= new Case(selectCase);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        for(int x =0; x<map.getTailleX();x++){
            for(int y =0; y<map.getTailleX();y++){
                Image image =null ;
                if(Editeur.currentZ%2==0){
                    try {
                        image = map.getSols().get(map.carte[x][y][Editeur.currentZ].type);
                    }catch(NullPointerException e){

                    }
                }
                else
                {
                    try {
                        image = map.getMurs().get(map.carte[x][y][Editeur.currentZ].type);
                    }catch(NullPointerException e){

                    }
                }
                g2d.drawImage(image, caseSize*x, caseSize*y, caseSize, caseSize, this);
            }
        }

    }

}
