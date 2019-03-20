package Diseño;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class CustomUI extends BasicComboBoxUI{
    
    private Color red = new Color(153,3,3);
    private Color borderColor = Utils.COLOR_INTERACTIVE;
    
    public static ComboBoxUI createUI(JComponent c) {
        return new CustomUI();
    }
  
      
    //Pinta los items
    @Override
    protected ListCellRenderer createRenderer()
    {
        return new DefaultListCellRenderer() {      
            
        @Override
        public Component getListCellRendererComponent(JList list,Object value,int index,
          boolean isSelected,boolean cellHasFocus) {
      
        super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
        list.setSelectionBackground(Utils.COLOR_BACKGROUND);
        if (isSelected)
        {
            setBackground(Utils.COLOR_INTERACTIVE);
            setForeground(Color.WHITE);
        }
        else
        {
            setBackground(Utils.COLOR_BACKGROUND);            
            setForeground(Utils.COLOR_OUTLINE);
        }
     
        return this;
      }
    };
    }
}
