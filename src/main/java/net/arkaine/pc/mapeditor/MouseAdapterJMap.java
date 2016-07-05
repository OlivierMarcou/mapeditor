package net.arkaine.pc.mapeditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by olivier on 27/06/16.
 */
public class MouseAdapterJMap extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JMap map = (JMap)e.getSource();
        map.drawCase(Editeur.selectCase+"_255_0_0_255_0_100_255", e.getX(), e.getY(), Editeur.currentZ);
        System.out.println("Mon type : "+e.getSource().getClass());
        System.out.println("Je suis : "+((JMap)e.getSource()).getName());
        System.out.println("press !!!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        System.out.println("Mon type : "+e.getSource().getClass());
        System.out.println("Je suis : "+((JMap)e.getSource()).getName());
        System.out.println("press release !!!");
    }
}