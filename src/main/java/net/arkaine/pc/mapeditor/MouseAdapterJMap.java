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
        addCase(e);
//        System.out.println("Mon type : "+e.getSource().getClass());
//        System.out.println("Je suis : "+((JMap)e.getSource()).getName());
//        System.out.println("press !!!");
    }

    private void addCase(MouseEvent e) {
        JMap map = (JMap)e.getSource();
        int life = Editeur.lifeSlider.getValue();
        float alpha = Editeur.alphaSlider.getValue();
        int light = Editeur.lightSlider.getValue();
        if(Editeur.selectCase > -1)
            map.drawCase(Editeur.selectCase+"_"
                +life
                +"___"
                +light
                +"__"
                +alpha
                +"_N", e.getX(), e.getY(), Editeur.currentZ);
        else
            map.drawCase("-1_0_0_0_255_0_1.0_N", e.getX(), e.getY(), Editeur.currentZ);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
//        System.out.println("Mon type : "+e.getSource().getClass());
//        System.out.println("Je suis : "+((JMap)e.getSource()).getName());
//        System.out.println("press release !!!");
    }
}
