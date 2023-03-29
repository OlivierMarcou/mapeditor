package net.arkaine.pc.mapeditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by olivier on 27/06/16.
 */
public class MouseMotionJMap extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e){
        super.mouseMoved(e);
        //  addCase(e);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        addCase(e);}

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
                    +"__N", e.getX(), e.getY(), Editeur.currentZ);
        else
            map.drawCase("-1_0_0_0_255_0_1.0_-1_N", e.getX(), e.getY(), Editeur.currentZ);
    }

}
