package net.arkaine.pc.mapeditor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by olivier on 05/07/16.
 */
public class JMap extends JPanel {

    private MapLoader map;

    public JMap (MapLoader map) {
        this.map = map;
    }

    public void drawCase(String selectCase, int x, int y, int z){
        try {
            map.carte[x / Editeur.caseSizeX][y / Editeur.caseSizeY][z] = new Case(selectCase);
            repaint();
        }catch(IndexOutOfBoundsException e){

        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        for(int x =0; x<map.getTailleX();x++){
            for(int y =0; y<map.getTailleY();y++){
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
                g2d.drawImage(image, Editeur.caseSizeX*x, Editeur.caseSizeY*y, Editeur.caseSizeX, Editeur.caseSizeY, this);
                g2d.setColor(Color.blue);
                g2d.drawRect(Editeur.caseSizeX*x, Editeur.caseSizeY*y, Editeur.caseSizeX, Editeur.caseSizeY);
            }
        }

    }

}
