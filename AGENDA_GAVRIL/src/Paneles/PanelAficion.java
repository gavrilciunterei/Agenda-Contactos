package Paneles;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BaseDatos.ContactoDAO;
import Clases.Aficion;
import Clases.Apodo;
import Clases.Contacto;
import Clases.Correo;
import Clases.Empresa;
import Clases.Persona;
import Clases.Provincias;
import Clases.Telefono;
import Diseño.Button;
import Diseño.ComboBox;
import Diseño.Label;
import Diseño.Radio;
import Diseño.TextField;
import Diseño.Utils;

import javax.swing.JComboBox;

public class PanelAficion extends JFrame implements ActionListener {

	private JPanel panel1, panel2;
	private TextField textFieldAficion;

	private TextField textFieldBuscar;
	private Button btnBuscar, btnAceptar, btnModificar;
	private Label label;
	private JCheckBox checkEditar;

	private ContactoDAO ag;
	private DefaultTableModel modelo;
	private JTable table;
	private JScrollPane scrollPane;
	private JMenuItem menuItemRemove;
	private JPopupMenu popupMenu;

	private String idModificarAficion;

	/**
	 * @throws SQLException
	 * @wbp.parser.entryPoint
	 */
	public JPanel aficion() throws SQLException {

		ag = new ContactoDAO();

		panel1 = new JPanel();
		panel1.setSize(Utils.DIMENSION_);
		panel1.setPreferredSize(Utils.DIMENSION_);
		panel1.setBackground(Utils.COLOR_BACKGROUND);
		panel1.setVisible(true);
		panel1.setLayout(null);

		label = new Label();
		label.setText(Utils.TEXTO_AFICION);
		label.setBounds(981, 12, 117, 30);
		panel1.add(label);

		textFieldAficion = new TextField();
		textFieldAficion.setBounds(1110, 12, 200, 30);
		textFieldAficion.setEnabled(true);
		panel1.add(textFieldAficion);

		btnAceptar = new Button(Utils.TEXTO_ACCEPTAR);
		btnAceptar.setBounds(1028, 54, 117, 29);
		btnAceptar.setEnabled(true);
		btnAceptar.setForeground(Utils.COLOR_OUTLINE);
		btnAceptar.addActionListener((ActionListener) this);

		MouseAdapter flup = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnAceptar.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnAceptar.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnAceptar.addMouseListener(flup);

		panel1.add(btnAceptar);

		panel1.add(tabla());
		ArrayList<String> campos = new ArrayList<String>();
		campos = ag.getAficion();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				campos.toArray(new String[campos.size()]));

		btnBuscar = new Button(Utils.TEXTO_BUSCAR);
		btnBuscar.setBounds(658, 9, 117, 36);
		btnBuscar.setForeground(Utils.COLOR_OUTLINE);
		btnBuscar.addActionListener((ActionListener) this);

		MouseAdapter flupBuscar = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnBuscar.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnBuscar.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnBuscar.addMouseListener(flupBuscar);
		btnBuscar.setEnabled(true);

		panel1.add(btnBuscar);

		textFieldBuscar = new TextField();
		textFieldBuscar.setBounds(161, 11, 441, 34);
		textFieldBuscar.setEnabled(true);
		panel1.add(textFieldBuscar);

		checkEditar = new JCheckBox("Editar");
		checkEditar.setBounds(837, 618, 100, 30);
		checkEditar.setOpaque(false);
		checkEditar.setBackground(Utils.COLOR_BACKGROUND);
		checkEditar.setForeground(Color.white);
		checkEditar.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		checkEditar.setHorizontalAlignment(SwingConstants.LEFT);
		checkEditar.setFont(Utils.FONT_GENERAL_UI);
		checkEditar.addActionListener((ActionListener) this);
		panel1.add(checkEditar);

		Button button = new Button("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnModificar = new Button(Utils.TEXTO_MODIFICAR);
		btnModificar.setBounds(1188, 56, 117, 29);
		btnModificar.setEnabled(false);
		btnModificar.setForeground(Utils.COLOR_OUTLINE);
		btnModificar.addActionListener((ActionListener) this);

		MouseAdapter flup2 = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnModificar.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnModificar.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnModificar.addMouseListener(flup2);
		panel1.add(btnModificar);

		return panel1;
	}

	public JPanel tabla() throws SQLException {

		panel2 = new JPanel();

		modelo = new DefaultTableModel() {

			// no deja editar las celdas
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modelo.addColumn("AFICION");

		table = new JTable(modelo);
		llenarTabla("normal", "");

		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(600, 200));
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setBackground(Utils.COLOR_BACKGROUND);
		table.setFont(Utils.FONT_GENERAL_UI);
		table.setForeground(Color.white);
		table.setRowHeight(30);
		table.setSelectionBackground(new Color(37, 55, 61));

		scrollPane.setLocation(10, 54);
		scrollPane.setSize(927, 563);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

				if (checkEditar.isSelected()) {

					if (table.getSelectedRow() != -1) {
						int fila = table.getSelectedRow();
						idModificarAficion = (String) table.getValueAt(fila, 0);
						try {
							llenarTextField();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			}

		});

		// Color head tabla
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Utils.COLOR_INTERACTIVE);
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}

		scrollPane.setPreferredSize(new Dimension(600, 200));
		panel1.add(scrollPane);

		popupMenu = new JPopupMenu();
		menuItemRemove = new JMenuItem("Borrar Contacto");
		menuItemRemove.setEnabled(false);
		menuItemRemove.addActionListener(this);
		popupMenu.add(menuItemRemove);

		table.setComponentPopupMenu(popupMenu);

		return panel2;

	}

	private void llenarTabla(String accion, String identificador) throws SQLException {

		ArrayList<Aficion> lc = ag.getAficionTabla(accion, identificador);
		modelo.setRowCount(0);
		for (Aficion cf : lc) {
			modelo.addRow(new Object[] { cf.getAficion()

			});
		}
	}

	private void llenarTextField() throws SQLException {

		ArrayList<Aficion> afi = ag.getAficionTabla("aficion", "" + idModificarAficion);

		for (Aficion af : afi) {
			textFieldAficion.setText(af.getAficion());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAceptar) {

			try {
				if (!textFieldAficion.getText().isEmpty()) {
					Aficion af = new Aficion(textFieldAficion.getText());
					if (!ag.comprobarExistenciaAficion(af.getAficion())) {
						if (ag.anadirAficion(af)) {
							textFieldAficion.setText(null);
							llenarTabla("normal", "");
							
						} 
					}else {
						JOptionPane.showMessageDialog(this, "Ya existe esta aficion.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}}else {
						JOptionPane.showMessageDialog(this, "Fallo al añadir la aficion", "Error",
								JOptionPane.ERROR_MESSAGE);
			}} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == menuItemRemove) {

			int fila = table.getSelectedRow();
			Aficion af = new Aficion(table.getValueAt(fila, 0).toString());
			try {
				
				if (ag.comprobarExistenciaAficion(af.getAficion())) {
					if (!ag.comprobarModificacionAficion(af.getAficion())) {
						if (ag.borrarAficion(af.getAficion())) {
							llenarTabla("normal", "");
						} 
					}else {
						JOptionPane.showMessageDialog(this, "Esta aficion esta siendo utilizada.", "Error",
								JOptionPane.ERROR_MESSAGE);
				}}else {
					JOptionPane.showMessageDialog(this, "Fallo al borrar la aficion.", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == btnBuscar) {
			try {
				if (!textFieldBuscar.getText().isEmpty()) {
					llenarTabla("aficion", textFieldBuscar.getText());

				} else {
					llenarTabla("normal", "");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			textFieldBuscar.setText("");
		}

		if (e.getSource() == checkEditar) {

			if (checkEditar.isSelected()) {
				menuItemRemove.setEnabled(true);
				btnModificar.setEnabled(true);

			}
			if (!checkEditar.isSelected()) {
				menuItemRemove.setEnabled(false);
				btnModificar.setEnabled(false);

			}
		}

		if (e.getSource() == btnModificar) {

			String newAf = textFieldAficion.getText();
			Aficion af = new Aficion(textFieldAficion.getText());
			
			try {
				if(ag.comprobarExistenciaAficion(af.getAficion())) {
					if (!ag.comprobarModificacionAficion(af.getAficion())) {
						if (ag.modificarAficion(idModificarAficion, af.getAficion())) {
							llenarTabla("normal", "");
							textFieldAficion.setText(null);
						}
					}else {
						JOptionPane.showMessageDialog(this, "La aficion esta siendo utilizada.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					}else {
						JOptionPane.showMessageDialog(this, "Fallo al modificar esta  aficion.", "Error",
								JOptionPane.ERROR_MESSAGE);	
					}	

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
