package Diseño;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class Radio extends JRadioButton {
    private Shape shape;
    private Color borderColor = Utils.COLOR_INTERACTIVE;

    public Radio() {
        setOpaque(false);
        setBackground(Utils.COLOR_BACKGROUND);
        setForeground(Color.white);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(Utils.FONT_GENERAL_UI);
        setFocusable(false);
        
        
    }
 


}