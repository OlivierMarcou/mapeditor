package net.arkaine.pc.mapeditor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by olivier on 27/06/16.
 */
public class MouseAdapterJPanel extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.println("Mon type : "+e.getSource().getClass());
        System.out.println("Je suis : "+((JPanel)e.getSource()).getName());
        System.out.println("press !!!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        System.out.println("Mon type : "+e.getSource().getClass());
        System.out.println("Je suis : "+((JPanel)e.getSource()).getName());
        System.out.println("press release !!!");
    }

}
