package Agenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Diseño.BlackTabbedPane;
import Diseño.BlackTabbedPaneUI;
import Diseño.Utils;
import Paneles.PanelAficion;
import Paneles.PanelContacto;

import javax.swing.JTabbedPane;

public class Main implements ActionListener{

	private JFrame frame;
    private JButton btnClose;
    private BlackTabbedPane tabbedPane;
	private ImageIcon salir = new ImageIcon("exit.png");
	private ImageIcon agenda = new ImageIcon("logoAgenda.png");
	private ImageIcon contacto = new ImageIcon("contacto.png");
	private ImageIcon aficion = new ImageIcon("aficion.png");
	private PanelContacto cc;
	private PanelAficion af;
	
    private int pX, pY;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Main() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Utils.COLOR_BACKGROUND);
		frame.setUndecorated(true);
		frame.setSize(Utils.DIMENSION_);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
		//Metodos para poder arrastrar la ventana//
		frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                pX = me.getX();
                pY = me.getY();
            }
        });
       frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(frame.getLocation().x + me.getX() - pX, 
                        frame.getLocation().y + me.getY() - pY);
            }
        });
		//Fin
		
		
		//LOGO
		JLabel picLabel = new JLabel(agenda);
		picLabel.setLocation(31, 6);
		picLabel.setSize(303, 108);
		frame.getContentPane().add(picLabel);
		
		
		btnClose = new JButton("", salir);
		btnClose.setVerticalTextPosition(SwingConstants.CENTER);
		btnClose.setHorizontalTextPosition(SwingConstants.CENTER);
		  
		btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnClose.setForeground(Color.white);
		btnClose.setBounds(1439, 6, 61, 29);

		btnClose.addActionListener((ActionListener) this);
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setContentAreaFilled(false);
		
		
		MouseAdapter flup = new MouseAdapter() {
	          @Override
	          public void mouseEntered(MouseEvent m) {
					btnClose.setText("X");

	          }
	          @Override
	          public void mouseExited(MouseEvent m) {
				btnClose.setText("");

	          }
		};
		btnClose.addMouseListener(flup);
	
		
		frame.getContentPane().add(btnClose);	
		
		tabbedPane = new BlackTabbedPane();
		frame.getContentPane().add(tabbedPane);
		
		cc = new PanelContacto();
		tabbedPane.addTab("Contactos", contacto, cc.contacto());
		
		af = new PanelAficion();
		tabbedPane.addTab("Aficion", aficion, af.aficion());


	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnClose) {
			System.exit(0);
			
		}
		
	}
}
