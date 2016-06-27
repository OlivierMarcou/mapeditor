package net.arkaine.pc.mapeditor;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by olivier on 17/06/16.
 */
public class main {

    public static void main(String[] args){
        Editeur editeur = new Editeur();
        Editeur.showOnScreen(0,editeur);
        Map<Integer,ImageIcon> images= Utils.loadImages("/home/olivier/workspace/mapeditor/src/main/resources/mursIso/");

        JLabel image = new JLabel((Icon) images.get(0));
        editeur.getCenterImage().setLayout(new BorderLayout());
        editeur.getCenterImage().add(image);

    }

}
