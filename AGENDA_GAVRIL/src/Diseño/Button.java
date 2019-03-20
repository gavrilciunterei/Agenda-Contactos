package Diseño;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Button extends JButton {

	
	public Button(String texto) {
		setText(texto);
		setOpaque(false);
		setBackground(Utils.COLOR_BACKGROUND);
		setBorder(new LineBorder(Utils.COLOR_OUTLINE));
		setFont(Utils.FONT_GENERAL_UI);
		setForeground(Utils.COLOR_INTERACTIVE);
		setEnabled(false);
	}
	
}
