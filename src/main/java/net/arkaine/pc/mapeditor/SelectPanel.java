package net.arkaine.pc.mapeditor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by olivier on 04/07/16.
 */
public class SelectPanel extends JPanel {
    private Image image;

    public SelectPanel (Image image) {
        this.image = image;
        setPreferredSize(new Dimension(40,40));
        repaint();

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.drawImage(image, 0, 0, 40,40, this);
    }
}