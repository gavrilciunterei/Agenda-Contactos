package Diseño;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;


public class TextField extends JTextField {
    private Shape shape;
    private Color borderColor = Utils.COLOR_INTERACTIVE;

    public TextField() {
        setOpaque(false);
        setBackground(Utils.COLOR_BACKGROUND);
        setCaretColor(Color.white);        
        setForeground(Utils.COLOR_OUTLINE);
        setBorderColor(Utils.COLOR_OUTLINE);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setMargin(new Insets(2, 10, 2, 2));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(Utils.FONT_GENERAL_UI);
        setEnabled(false);
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	
            	setForeground(Color.white);
            	setBorderColor(Utils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
           
            	setForeground(Utils.COLOR_OUTLINE);
            	setBorderColor(Utils.COLOR_OUTLINE);
            }
        });
        
        
    }
    
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = Utils.get2dGraphics(g);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Utils.ROUNDNESS, Utils.ROUNDNESS);
        super.paintComponent(g2);
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2 = Utils.get2dGraphics(g);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Utils.ROUNDNESS, Utils.ROUNDNESS);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, Utils.ROUNDNESS, Utils.ROUNDNESS);
        }
        return shape.contains(x, y);
    }

    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}