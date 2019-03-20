package Diseño;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class ComboBox extends JComboBox {
    private Shape shape;
    private Color borderColor = Utils.COLOR_INTERACTIVE;

    public ComboBox() {
    	
        setOpaque(false);
        setForeground(Utils.COLOR_OUTLINE);
		setBackground(Utils.COLOR_BACKGROUND);
        setBorderColor(Utils.COLOR_OUTLINE);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setFont(Utils.FONT_GENERAL_UI);
		setSize(200, 24);
		setUI(CustomUI.createUI(this)); 
  
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