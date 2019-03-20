package Diseño;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

public class BlackTabbedPane extends JTabbedPane{
	
	BlackTabbedPaneUI blackTabbedPaneUI = new BlackTabbedPaneUI();

	  public BlackTabbedPane(){
	
		 	setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			setTabPlacement(JTabbedPane.TOP);
	        setLocation(0, 94);
			setSize(Utils.DIMENSION_TAB);
			setForeground(Color.white);
			setFont(Utils.FONT_GENERAL_UI);
			setUI( blackTabbedPaneUI );
		    setVisible(true);
	    }

}
