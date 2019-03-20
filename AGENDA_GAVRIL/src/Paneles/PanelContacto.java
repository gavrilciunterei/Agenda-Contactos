package Paneles;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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

public class PanelContacto extends JFrame implements ActionListener, ChangeListener {

	private JPanel panel1, panel2;
	private TextField textFieldApodo, textFieldEmpresa, textFieldNombre, textFieldApellido, textFieldTelefono,
			textFieldCorreo, textFieldNotas;
	private TextField textFieldDireccion1, textFieldDireccion2, textFieldDireccion3, textFieldDireccion4,
			textFieldDireccion5, textFieldDireccion6;
	private TextField textFieldBuscar;
	private Button btnBuscar, btnAceptar, btnModificar;
	private Button btnMasTelefonos, btnMasEliminar, btnMasCorreo, btnMasEliminar2;
	private JList<String> jlistAficiones, jlistCorreos;
	private Label label, label1, label2, label3, label4, label6, label7, label8, label9;
	private Label label5, label51, label52, label53, label54, label55, label56;
	private Radio radio1, radio2, radio3;;
	private ButtonGroup bg;
	private ComboBox comboProvincias, comboBuscar, comboSexo, comboTelefono;
	private JCheckBox checkEditar;

	private ContactoDAO ag;
	private DefaultTableModel modelo, modeloTelefono;
	private JTable table, tableTelefono;
	private JScrollPane scrollPane, scrollPaneTelefono;
	private String idBorrar;
	private JMenuItem menuItemRemove;
	private JPopupMenu popupMenu;

	private int idModificarContacto;
	private DefaultComboBoxModel<String> modelCorreo;

	private static String llenarTabla = "normal";
	
	private ArrayList<Contacto> lc =null;
	private ArrayList<Telefono> tt =null;
	private ArrayList<Correo> cor =null;
	private ArrayList<Aficion> afi =null;

	/**
	 * @throws SQLException
	 * @wbp.parser.entryPoint
	 */
	public JPanel contacto() throws SQLException {

		ag = new ContactoDAO();

		panel1 = new JPanel();
		panel1.setSize(Utils.DIMENSION_);
		panel1.setPreferredSize(Utils.DIMENSION_);
		panel1.setBackground(Utils.COLOR_BACKGROUND);
		panel1.setVisible(true);
		panel1.setLayout(null);

		bg = new ButtonGroup();
		radio1 = new Radio();
		radio1.setBounds(949, 12, 38, 30);
		radio1.addChangeListener(this);
		bg.add(radio1);
		panel1.add(radio1);

		radio2 = new Radio();
		radio2.setBounds(949, 96, 38, 30);
		radio2.addChangeListener(this);
		bg.add(radio2);
		panel1.add(radio2);

		radio3 = new Radio();
		radio3.setBounds(949, 54, 32, 30);
		radio3.addChangeListener(this);
		bg.add(radio3);
		panel1.add(radio3);

		label = new Label();
		label.setText(Utils.TEXTO_APODO);
		label.setBounds(981, 12, 88, 30);
		panel1.add(label);

		textFieldApodo = new TextField();
		textFieldApodo.setBounds(1081, 12, 200, 30);
		panel1.add(textFieldApodo);

		label1 = new Label();
		label1.setText(Utils.TEXTO_EMPRESA);
		label1.setBounds(981, 54, 100, 30);
		panel1.add(label1);

		textFieldEmpresa = new TextField();
		textFieldEmpresa.setBounds(1081, 54, 200, 30);
		panel1.add(textFieldEmpresa);

		label2 = new Label();
		label2.setText(Utils.TEXTO_NOMBRE);
		label2.setBounds(981, 96, 100, 30);
		panel1.add(label2);

		textFieldNombre = new TextField();
		textFieldNombre.setBounds(1081, 96, 200, 30);
		panel1.add(textFieldNombre);

		label3 = new Label();
		label3.setText(Utils.TEXTO_APELLIDO);
		label3.setBounds(981, 138, 100, 30);
		panel1.add(label3);

		textFieldApellido = new TextField();
		textFieldApellido.setBounds(1081, 138, 200, 30);
		panel1.add(textFieldApellido);

		label4 = new Label();
		label4.setText(Utils.TEXTO_SEXO);
		label4.setBounds(981, 175, 100, 30);
		panel1.add(label4);

		comboSexo = new ComboBox();
		comboSexo.setBounds(1081, 175, 200, 30);
		comboSexo.setEnabled(true);

		String[] buscarSexo = { "", "Hombre", "Mujer" };
		for (int i = 0; i < buscarSexo.length; i++) {
			comboSexo.addItem(buscarSexo[i]);
		}
		panel1.add(comboSexo);

		// Calle, Número, Piso, Puerta, Código Postal, Ciudad, Provincia]

		label5 = new Label();
		label5.setText(Utils.TEXTO_CALLE);
		label5.setBounds(981, 217, 88, 30);
		panel1.add(label5);

		textFieldDireccion1 = new TextField();
		textFieldDireccion1.setBounds(1081, 217, 200, 30);
		panel1.add(textFieldDireccion1);

		label51 = new Label();
		label51.setText(Utils.TEXTO_NUMERO);
		label51.setBounds(981, 259, 100, 30);
		panel1.add(label51);

		textFieldDireccion2 = new TextField();
		textFieldDireccion2.setBounds(1081, 259, 54, 30);
		panel1.add(textFieldDireccion2);

		label52 = new Label();
		label52.setText(Utils.TEXTO_PISO);
		label52.setBounds(1165, 259, 54, 30);
		panel1.add(label52);

		textFieldDireccion3 = new TextField();
		textFieldDireccion3.setBounds(1227, 259, 54, 30);
		panel1.add(textFieldDireccion3);

		label53 = new Label();
		label53.setText(Utils.TEXTO_PUERTA);
		label53.setBounds(981, 301, 78, 30);
		panel1.add(label53);

		textFieldDireccion4 = new TextField();
		textFieldDireccion4.setBounds(1081, 301, 54, 30);
		panel1.add(textFieldDireccion4);

		label54 = new Label();
		label54.setText(Utils.TEXTO_CODIGO);
		label54.setBounds(1165, 301, 54, 30);
		panel1.add(label54);

		textFieldDireccion5 = new TextField();
		textFieldDireccion5.setBounds(1227, 301, 54, 30);
		panel1.add(textFieldDireccion5);

		label55 = new Label();
		label55.setText(Utils.TEXTO_CIUDAD);
		label55.setBounds(981, 343, 100, 30);
		panel1.add(label55);

		textFieldDireccion6 = new TextField();
		textFieldDireccion6.setBounds(1081, 343, 200, 30);

		panel1.add(textFieldDireccion6);

		label56 = new Label();
		label56.setText(Utils.TEXTO_PROVINCIA);
		label56.setBounds(981, 379, 100, 30);
		panel1.add(label56);

		// Modificar estilo
		comboProvincias = new ComboBox();
		comboProvincias.setLocation(1081, 385);
		comboProvincias.setEnabled(false);

		Provincias p = new Provincias();
		for (int i = 0; i < p.getProvincias().size(); i++) {
			comboProvincias.addItem(p.getProvincias().toArray()[i]);
		}

		panel1.add(comboProvincias);

		comboBuscar = new ComboBox();
		comboBuscar.setSize(200, 35);
		comboBuscar.setLocation(92, 7);
		comboBuscar.setEnabled(true);

		String[] buscar = { "", "Nombre", "Apellido", "Apodo", "Empresa", "Aficion" };
		for (int i = 0; i < buscar.length; i++) {
			comboBuscar.addItem(buscar[i]);
		}

		panel1.add(comboBuscar);

		label6 = new Label();
		label6.setText(Utils.TEXTO_TELEFONO);
		label6.setBounds(981, 417, 100, 30);
		panel1.add(label6);

		textFieldTelefono = new TextField();
		textFieldTelefono.setBounds(1170, 417, 107, 30);
		panel1.add(textFieldTelefono);

		label7 = new Label();
		label7.setText(Utils.TEXTO_CORREO);
		label7.setBounds(981, 524, 100, 30);
		panel1.add(label7);

		textFieldCorreo = new TextField();
		textFieldCorreo.setBounds(1081, 524, 200, 30);
		panel1.add(textFieldCorreo);

		label8 = new Label();
		label8.setText(Utils.TEXTO_NOTAS);
		label8.setBounds(981, 629, 100, 30);
		panel1.add(label8);

		textFieldNotas = new TextField();
		textFieldNotas.setBounds(1081, 629, 200, 30);
		panel1.add(textFieldNotas);

		btnAceptar = new Button(Utils.TEXTO_ACCEPTAR);
		btnAceptar.setBounds(1040, 812, 117, 29);
		btnAceptar.setEnabled(false);
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

		label9 = new Label();
		label9.setText(Utils.TEXTO_AFICION);
		label9.setBounds(974, 671, 107, 30);
		panel1.add(label9);

		// Lista de aficiones
		jlistAficiones = new JList<String>();
		jlistAficiones.setBounds(1081, 677, 200, 123);
		jlistAficiones.setForeground(Utils.COLOR_OUTLINE);
		jlistAficiones.setBackground(Utils.COLOR_BACKGROUND);
		jlistAficiones.setFont(Utils.FONT_GENERAL_UI);
		jlistAficiones.setBorder(new LineBorder(Utils.COLOR_OUTLINE));
		jlistAficiones.setVisibleRowCount(1);
		jlistAficiones.setSelectionInterval(1, 3);
		jlistAficiones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		llenarAficiones();
		jlistAficiones.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(jlistAficiones);
		scrollPane.setBounds(1081, 677, 200, 123);
		scrollPane.setBackground(Utils.COLOR_BACKGROUND);
		panel1.add(scrollPane);


		btnBuscar = new Button(Utils.TEXTO_BUSCAR);
		btnBuscar.setBounds(796, 6, 117, 36);
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
		textFieldBuscar.setBounds(318, 8, 441, 34);
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
		btnModificar.setBounds(1194, 812, 117, 29);
		btnModificar.setEnabled(true);
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

		// boton añadir mas telefonos
		btnMasTelefonos = new Button("Añadir");
		btnMasTelefonos.setBounds(1296, 418, 128, 29);
		btnMasTelefonos.setVisible(false);
		btnMasTelefonos.setForeground(Utils.COLOR_OUTLINE);
		btnMasTelefonos.addActionListener((ActionListener) this);

		MouseAdapter flup3 = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnMasTelefonos.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnMasTelefonos.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnMasTelefonos.addMouseListener(flup3);
		panel1.add(btnMasTelefonos);

		btnMasEliminar = new Button("Eliminar");
		btnMasEliminar.setBounds(1296, 459, 128, 29);
		btnMasEliminar.setVisible(false);
		btnMasEliminar.setForeground(Utils.COLOR_OUTLINE);
		btnMasEliminar.addActionListener((ActionListener) this);

		MouseAdapter flup4 = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnMasEliminar.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnMasEliminar.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnMasEliminar.addMouseListener(flup4);
		panel1.add(btnMasEliminar);

		// Lista telefonos en el formulario
		modeloTelefono = new DefaultTableModel() {

			// no deja editar las celdas
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modeloTelefono.addColumn("TIPO");
		modeloTelefono.addColumn("TELEFONO");

		tableTelefono = new JTable(modeloTelefono);

		tableTelefono.setBounds(1081, 459, 196, 52);
		scrollPaneTelefono = new JScrollPane(tableTelefono, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTelefono.setPreferredSize(new Dimension(600, 200));
		tableTelefono.setOpaque(false);
		tableTelefono.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTelefono.setRowSelectionAllowed(false);
		tableTelefono.setColumnSelectionAllowed(false);
		tableTelefono.setCellSelectionEnabled(false);
		tableTelefono.setBackground(Utils.COLOR_BACKGROUND);
		tableTelefono.setFont(Utils.FONT_GENERAL_UI);
		tableTelefono.setForeground(Color.white);
		tableTelefono.setRowHeight(30);
		tableTelefono.setSelectionBackground(Utils.COLOR_INTERACTIVE);
		tableTelefono.setTableHeader(null);

		scrollPaneTelefono.setBounds(1081, 459, 196, 52);

		scrollPaneTelefono.setOpaque(false);
		scrollPaneTelefono.getViewport().setOpaque(false);

		tableTelefono.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

				if (tableTelefono.getSelectedRow() > -1) {
					btnMasEliminar.setVisible(true);
					btnMasEliminar.setEnabled(true);
				}
			}
		});
		scrollPaneTelefono.setPreferredSize(new Dimension(600, 200));
		panel1.add(scrollPaneTelefono);

		// Lista CORREO en el formulario
		jlistCorreos = new JList<String>();
		panel1.add(jlistCorreos);
		jlistCorreos.setForeground(Utils.COLOR_OUTLINE);
		jlistCorreos.setOpaque(false);
		jlistCorreos.setBackground(Utils.COLOR_BACKGROUND);
		jlistCorreos.setFont(Utils.FONT_GENERAL_UI);
		jlistCorreos.setBorder(new LineBorder(Utils.COLOR_OUTLINE));
		jlistCorreos.setVisibleRowCount(1);
		jlistCorreos.setSelectionInterval(1, 3);
		jlistCorreos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jlistCorreos.setEnabled(false);

		jlistCorreos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (jlistCorreos.getSelectedIndex() > -1) {
					btnMasEliminar2.setVisible(true);
					btnMasEliminar2.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPaneCorreo = new JScrollPane(jlistCorreos);
		scrollPaneCorreo.setBounds(1081, 564, 196, 52);
		scrollPaneCorreo.setForeground(Utils.COLOR_OUTLINE);
		scrollPaneCorreo.setOpaque(false);
		scrollPaneCorreo.setBackground(Utils.COLOR_BACKGROUND);
		scrollPaneCorreo.setBorder(new LineBorder(Utils.COLOR_OUTLINE));
		scrollPaneCorreo.getViewport().setBackground(Utils.COLOR_BACKGROUND);

		panel1.add(scrollPaneCorreo);

		// boton añadir mas telefonos
		btnMasCorreo = new Button("Añadir");
		btnMasCorreo.setBounds(1293, 525, 128, 29);
		btnMasCorreo.setVisible(false);
		btnMasCorreo.setForeground(Utils.COLOR_OUTLINE);
		btnMasCorreo.addActionListener((ActionListener) this);

		MouseAdapter flup5 = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnMasCorreo.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnMasCorreo.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnMasCorreo.addMouseListener(flup5);
		panel1.add(btnMasCorreo);

		btnMasEliminar2 = new Button("Eliminar");
		btnMasEliminar2.setBounds(1296, 565, 128, 29);
		btnMasEliminar2.setVisible(false);
		btnMasEliminar2.setForeground(Utils.COLOR_OUTLINE);
		btnMasEliminar2.addActionListener((ActionListener) this);

		MouseAdapter flup6 = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				btnMasEliminar2.setForeground(Utils.COLOR_INTERACTIVE);

			}

			@Override
			public void mouseExited(MouseEvent m) {
				btnMasEliminar2.setForeground(Utils.COLOR_OUTLINE);

			}
		};
		btnMasEliminar2.addMouseListener(flup6);
		panel1.add(btnMasEliminar2);

		comboTelefono = new ComboBox();
		comboTelefono.setBounds(1081, 417, 88, 30);
		comboTelefono.setEnabled(true);

		String[] buscarTelefono = { "", "Movil", "Fijo" };
		for (int i = 0; i < buscarTelefono.length; i++) {
			comboTelefono.addItem(buscarTelefono[i]);
		}
		panel1.add(comboTelefono);

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

		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO");
		modelo.addColumn("APODO");
		modelo.addColumn("EMPRESA");
		modelo.addColumn("SEXO");
		modelo.addColumn("DIRECCION");
		modelo.addColumn("NOTAS");
		modelo.addColumn("AFICION");
		modelo.addColumn("CORREO");
		modelo.addColumn("MOVIL");
		modelo.addColumn("FIJIO");

		table = new JTable(modelo);
		llenarTabla(llenarTabla, "");

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
		table.setSelectionBackground(Utils.COLOR_INTERACTIVE);

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
						idModificarContacto = (int) table.getValueAt(fila, 0);
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

	public void stateChanged(ChangeEvent e) {

		if (radio1.isSelected()) {
			btnAceptar.setEnabled(true);
			textFieldApodo.setEnabled(true);
			comboSexo.setEnabled(true);

			textFieldEmpresa.setEnabled(false);
			textFieldEmpresa.setText(null);
			textFieldNombre.setEnabled(false);
			textFieldNombre.setText(null);
			textFieldApellido.setEnabled(false);
			textFieldApellido.setText(null);
			llenarAficiones();
			activarCampos();

		}

		if (radio2.isSelected()) {
			btnAceptar.setEnabled(true);
			llenarAficiones();

			textFieldNombre.setEnabled(true);
			textFieldApellido.setEnabled(true);
			comboSexo.setEnabled(true);

			textFieldEmpresa.setEnabled(false);
			textFieldEmpresa.setText(null);
			textFieldApodo.setEnabled(false);
			textFieldApodo.setText(null);

			activarCampos();

		}

		if (radio3.isSelected()) {
			btnAceptar.setEnabled(true);
			llenarAficiones();

			textFieldEmpresa.setEnabled(true);

			textFieldNombre.setEnabled(false);
			textFieldNombre.setText(null);
			textFieldApellido.setEnabled(false);
			textFieldApellido.setText(null);
			comboSexo.setEnabled(false);
			comboSexo.setSelectedIndex(0);
			textFieldApodo.setEnabled(false);
			textFieldApodo.setText(null);

			activarCampos();
		}

	}

	private void activarCampos() {

		textFieldTelefono.setEnabled(true);
		textFieldCorreo.setEnabled(true);
		textFieldNotas.setEnabled(true);
		textFieldDireccion1.setEnabled(true);
		textFieldDireccion2.setEnabled(true);
		textFieldDireccion3.setEnabled(true);
		textFieldDireccion4.setEnabled(true);
		textFieldDireccion5.setEnabled(true);
		textFieldDireccion6.setEnabled(true);
		jlistAficiones.setEnabled(true);
		comboProvincias.setEnabled(true);
	}

	private void desactivarCampos() {

		textFieldTelefono.setEnabled(false);
		textFieldTelefono.setText(null);
		textFieldCorreo.setEnabled(false);
		textFieldCorreo.setText(null);
		textFieldNotas.setEnabled(false);
		textFieldNotas.setText(null);
		textFieldDireccion1.setEnabled(false);
		textFieldDireccion1.setText(null);
		textFieldDireccion2.setEnabled(false);
		textFieldDireccion2.setText(null);
		textFieldDireccion3.setEnabled(false);
		textFieldDireccion3.setText(null);
		textFieldDireccion4.setEnabled(false);
		textFieldDireccion4.setText(null);
		textFieldDireccion5.setEnabled(false);
		textFieldDireccion5.setText(null);
		textFieldDireccion6.setEnabled(false);
		textFieldDireccion6.setText(null);
		jlistAficiones.setEnabled(false);
		jlistAficiones.setSelectedIndex(0);
		comboProvincias.setEnabled(false);
		comboProvincias.setSelectedIndex(-1);
	}

	private void llenarTabla(String accion, String identificador) throws SQLException {

		 lc = ag.getContacto(accion, identificador);
		 tt = ag.getArrayTelefono();
		 cor = ag.getArrayCorreo();
		 afi = ag.getArrayAficion();

		modelo.setRowCount(0);
		Persona p = null;
		Empresa e = null;
		Apodo a = null;
		Telefono t = null;
		Correo c = null;
		Aficion af = null;
		String movil = "";
		String fijo = "";
		int contadorNuevo = tt.size();
		
		for (int i = 0; i < lc.size(); i++) {
			
		
			if(i < tt.size()) {
				t = (Telefono)tt.get(i);
			}
			if(cor.get(i) != null) {
				c = (Correo) cor.get(i);
			}
			if(afi.get(i) != null) {
				af = (Aficion) afi.get(i);
			}
		
			
			if (lc.get(i).getClass().getName().equals(Persona.class.getName())) {
				p = (Persona) lc.get(i);
				movil = "";
				fijo = "";
				for (int x = 0; x < tt.size(); x++) {
					if (tt.get(x).getId() == p.getId()) {

						movil += tt.get(x).getMovil() == null ? "" : tt.get(x).getMovil() + ",";
						fijo += tt.get(x).getFijo() == null ? "" : tt.get(x).getFijo() + ",";

					}
				}
				if (movil != null && movil.length() > 0) {
					movil = movil.substring(0, movil.length() - 1);
				}
				if (fijo != null && fijo.length() > 0) {
					fijo = fijo.substring(0, fijo.length() - 1);
				}

				modelo.addRow(new Object[] { p.getId(), p.getNombre(), p.getApellido(), null, null, p.getSexo(),
						p.getDireccion(), p.getNotas(), af.getAficion(), c.getCorreo(), movil, fijo

				});
			}
			if (lc.get(i).getClass().getName().equals(Apodo.class.getName())) {
				a = (Apodo) lc.get(i);
				movil = "";
				fijo = "";
				// aqui vemos si el id del contacto concide con el del movil
				for (int x = 0; x < tt.size(); x++) {
					if (tt.get(x).getId() == a.getId()) {

						movil += tt.get(x).getMovil() == null ? "" : tt.get(x).getMovil() + ",";
						fijo += tt.get(x).getFijo() == null ? "" : tt.get(x).getFijo() + ",";

					}
				}
				// quitamos última coma
				if (movil != null && movil.length() > 0) {
					movil = movil.substring(0, movil.length() - 1);
				}
				if (fijo != null && fijo.length() > 0) {
					fijo = fijo.substring(0, fijo.length() - 1);
				}

				modelo.addRow(new Object[] { a.getId(), null, null, a.getApodo(), null, a.getSexo(), a.getDireccion(),
						a.getNotas(), af.getAficion(), c.getCorreo(), movil, fijo });
			}
			if (lc.get(i).getClass().getName().equals(Empresa.class.getName())) {
				e = (Empresa) lc.get(i);
				movil = "";
				fijo = "";

				for (int x = 0; x < tt.size(); x++) {
					if (tt.get(x).getId() == e.getId()) {

						movil += tt.get(x).getMovil() == null ? "" : tt.get(x).getMovil() + ",";
						fijo += tt.get(x).getFijo() == null ? "" : tt.get(x).getFijo() + ",";

					}
				}

				if (movil != null && movil.length() > 0) {
					movil = movil.substring(0, movil.length() - 1);
				}
				if (fijo != null && fijo.length() > 0) {
					fijo = fijo.substring(0, fijo.length() - 1);
				}
				modelo.addRow(new Object[] { e.getId(), null, null, null, e.getEmpresa(), null, e.getDireccion(),
						e.getNotas(), af.getAficion(), c.getCorreo(), movil, fijo });
			}
		}

	}
	
	

	private void llenarTextField() throws SQLException {

		 lc = ag.getContacto("id", "" + idModificarContacto);
		 tt = ag.getArrayTelefono();
		 cor = ag.getArrayCorreo();
		 afi = ag.getArrayAficion();

		Persona p = null;
		Empresa e = null;
		Apodo a = null;
		Telefono t = null;
		Correo c = null;
		Aficion af = null;

		String sexo = "";
		String notas = "";
		String direccionn = "";

		for (int i = 0; i < lc.size(); i++) {
			
			if(i < tt.size()) {
				t = (Telefono) tt.get(i);
			}
			c = (Correo) cor.get(i);
			af = (Aficion) afi.get(i);

			if (lc.get(i).getClass().getName().equals(Persona.class.getName())) {
				p = (Persona) lc.get(i);
				radio2.setSelected(true);
				textFieldNombre.setText(p.getNombre());
				textFieldApellido.setText(p.getApellido());
				sexo = p.getSexo();
				notas = p.getNotas();
				direccionn = p.getDireccion();
			}
			if (lc.get(i).getClass().getName().equals(Apodo.class.getName())) {
				a = (Apodo) lc.get(i);
				radio1.setSelected(true);
				textFieldApodo.setText(a.getApodo());
				sexo = a.getSexo();
				notas = a.getNotas();
				direccionn = a.getDireccion();

			}
			if (lc.get(i).getClass().getName().equals(Empresa.class.getName())) {
				e = (Empresa) lc.get(i);
				radio3.setSelected(true);
				textFieldEmpresa.setText(e.getEmpresa());
				sexo = null;
				notas = e.getNotas();
				direccionn = e.getDireccion();

			}

			for (int x = 0; x < 3; x++) {
				if (comboSexo.getItemAt(x).equals(sexo)) {
					comboSexo.setSelectedIndex(x);
				}
			}
			textFieldNotas.setText(notas);

			// Añadir aficiones a la jlist
			String aficiones = af.getAficion();
			ArrayList<String> aficion = new ArrayList<String>(Arrays.asList(aficiones.split(",")));
			int[] indices = new int[aficion.size()];
			int indice = 0;

			for (int y = 0; y < jlistAficiones.getModel().getSize(); y++) {
				for (int j = 0; j < aficion.size(); j++) {
					if (jlistAficiones.getModel().getElementAt(y).equals(aficion.get(j))) {
						indices[indice] = y;
						indice++;
					}
				}
			}
			jlistAficiones.setSelectedIndices(indices);

			// Añadir direccion
			String provincia = direccionn;
			ArrayList<String> direccion = new ArrayList<String>(Arrays.asList(provincia.split(",")));

			textFieldDireccion1.setText(direccion.get(0).trim());
			textFieldDireccion2.setText(direccion.get(1).trim());
			textFieldDireccion3.setText(direccion.get(2).trim());
			textFieldDireccion4.setText(direccion.get(3).trim());
			textFieldDireccion5.setText(direccion.get(4).trim());
			textFieldDireccion6.setText(direccion.get(5).trim());

			int indiceProvincia = 0;
			boolean salir = false;
			for (int j = 1; j < comboProvincias.getModel().getSize() && !salir; j++) {
				if (comboProvincias.getModel().getElementAt(j).equals(direccion.get(6).trim())) {
					indiceProvincia = j;
					salir = true;
				}
			}
			comboProvincias.setSelectedIndex(indiceProvincia);

			// Añadir telefonos a la jlist
			String tipo="";
			String telefono ="";
			modeloTelefono.setRowCount(0);
			for (Telefono tel : tt) {
				tipo="";
				telefono ="";
				
				if(tel.getMovil() != null) {
					tipo = "Movil";
					telefono = tel.getMovil();
				}
				else if(tel.getFijo() != null) {
					tipo ="Fijo";
					telefono = tel.getFijo();
				}
				modeloTelefono.addRow(new Object[] {
						tipo,
						telefono				
				});

			}

			tableTelefono.setEnabled(true);
			tableTelefono.setRowSelectionAllowed(true);
			tableTelefono.setColumnSelectionAllowed(false);
			tableTelefono.setCellSelectionEnabled(true);
			tableTelefono.setColumnSelectionAllowed(false);

			
			// Añadimos Correos a la jlist
			String campoCorreo = c.getCorreo();
			ArrayList<String> camposCorreo = new ArrayList<String>(Arrays.asList(campoCorreo.split(",")));
			modelCorreo = new DefaultComboBoxModel<String>(camposCorreo.toArray(new String[camposCorreo.size()]));
			jlistCorreos.setEnabled(true);
			jlistCorreos.setModel(modelCorreo);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAceptar) {

			// Guardado de la direccion, distintos textField en uno solo separdos por coma
			String 	direccion = textFieldDireccion1.getText() + ", " + textFieldDireccion2.getText() + ", "
					+ textFieldDireccion3.getText() + ", " + textFieldDireccion4.getText() + ", "
					+ textFieldDireccion5.getText() + ", " + textFieldDireccion6.getText() + ", "
					+ comboProvincias.getSelectedItem();
			
			
			try {
				if (!textFieldApodo.getText().isEmpty()
						|| (!textFieldNombre.getText().isEmpty() && !textFieldApellido.getText().isEmpty())
						|| !textFieldEmpresa.getText().isEmpty()) {

					// AÑADIMOS UN CONTACTO, SIMPLE
					int id = ag.getLastId();
					Contacto c = null;
					if (radio1.isSelected()) {
						Apodo ap = new Apodo(id, direccion, textFieldNotas.getText(), textFieldApodo.getText(),
								comboSexo.getSelectedItem().toString());
						c = ap;

					}

					if (radio3.isSelected()) {
						Empresa em = new Empresa(id, direccion, textFieldNotas.getText(), textFieldEmpresa.getText());
						c = em;
					}

					if (radio2.isSelected()) {
						Persona ps = new Persona(id, direccion, textFieldNotas.getText(), textFieldNombre.getText(),
								textFieldApellido.getText(), comboSexo.getSelectedItem().toString());
						c = ps;
					}

					ag.anadirContacto(c);

					// añadimos las aficiones selccionadas de una lista a un contacto en la tabla
					// AFICIONCONTACTO
					int[] indices = jlistAficiones.getSelectedIndices();
					for (int i = 0; i < indices.length; i++) {
						ag.anadirAficionContacto(id, jlistAficiones.getSelectedValuesList().get(i));

					}

					// AÑADIMOS UN TELEFONO
					Telefono t = null;
					if (!textFieldTelefono.getText().isEmpty()) {

						if (comboTelefono.getSelectedIndex() == 1) {
							t = new Telefono(textFieldTelefono.getText(), null, id);
						} else if (comboTelefono.getSelectedIndex() == 2) {
							t = new Telefono(null, textFieldTelefono.getText(), id);
						}

						ag.anadirTelefono(t);
					}

					// AÑADIMOS CORREO
					Correo cc = new Correo(textFieldCorreo.getText(), id);
					ag.anadirCorreo(cc);

					// DEJAMOS LOS CAMPOS EN VACIO
					textFieldApodo.setText(null);
					textFieldEmpresa.setText(null);
					textFieldNombre.setText(null);
					textFieldApellido.setText(null);
					comboSexo.setSelectedIndex(0);
					textFieldTelefono.setText(null);
					textFieldCorreo.setText(null);
					textFieldNotas.setText(null);
					textFieldDireccion1.setText(null);
					textFieldDireccion2.setText(null);
					textFieldDireccion3.setText(null);
					textFieldDireccion4.setText(null);
					textFieldDireccion5.setText(null);
					textFieldDireccion6.setText(null);
					comboProvincias.setSelectedIndex(0);

					// refrescamos la tabla
					llenarTabla(llenarTabla, "");
				} else {
					JOptionPane.showMessageDialog(this, "Fallo al añadir el contacto", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == menuItemRemove) {
			// Ponemos 0 porque es el numero de la columna que queremos (el id)
			int fila = table.getSelectedRow();
			idBorrar = table.getValueAt(fila, 0).toString();
			try {
				// Elimina todo sobre el contacto con esa id
				ag.borrarContacto(Integer.parseInt(idBorrar));
				ag.borrarCorreo(Integer.parseInt(idBorrar));
				ag.borrarTelefono(Integer.parseInt(idBorrar));
				ag.borrarContactoAficion(Integer.parseInt(idBorrar));

				JOptionPane.showMessageDialog(this, "Usuario Eliminado.");
				// Refrescamos la tabla
				llenarTabla(llenarTabla, "");

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Fallo al eliminar", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}

		}

		if (e.getSource() == btnBuscar) {

			if (comboBuscar.getSelectedItem().equals("Nombre")) {

				try {
					if (!textFieldBuscar.getText().isEmpty()) {
						llenarTabla("nombre", textFieldBuscar.getText());

					} else {
						llenarTabla(llenarTabla, "");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comboBuscar.getSelectedItem().equals("Apodo")) {

				try {
					if (!textFieldBuscar.getText().isEmpty()) {
						llenarTabla("apodo", textFieldBuscar.getText());

					} else {
						llenarTabla(llenarTabla, "");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comboBuscar.getSelectedItem().equals("Apellido")) {

				try {
					if (!textFieldBuscar.getText().isEmpty()) {
						llenarTabla("apellido", textFieldBuscar.getText());

					} else {
						llenarTabla(llenarTabla, "");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comboBuscar.getSelectedItem().equals("Empresa")) {

				try {
					if (!textFieldBuscar.getText().isEmpty()) {
						llenarTabla("empresa", textFieldBuscar.getText());

					} else {
						llenarTabla(llenarTabla, "");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (comboBuscar.getSelectedItem().equals("Aficion")) {

				try {
					if (!textFieldBuscar.getText().isEmpty()) {
						llenarTabla("aficion", textFieldBuscar.getText());

					} else {
						llenarTabla(llenarTabla, "");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			textFieldBuscar.setText("");
		}

		if (e.getSource() == checkEditar) {

			if (checkEditar.isSelected()) {
				btnMasTelefonos.setVisible(true);
				btnMasTelefonos.setEnabled(true);
				btnAceptar.setEnabled(false);
				menuItemRemove.setEnabled(true);

				btnMasCorreo.setVisible(true);
				btnMasCorreo.setEnabled(true);

				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
				table.setCellSelectionEnabled(true);
				table.setColumnSelectionAllowed(false);

				btnModificar.setEnabled(true);

				activarCampos();
				textFieldApodo.setEnabled(true);
				textFieldNombre.setEnabled(true);
				textFieldApellido.setEnabled(true);
				comboSexo.setEnabled(true);
				textFieldEmpresa.setEnabled(true);

			}
			if (!checkEditar.isSelected()) {
				btnMasTelefonos.setVisible(false);
				btnMasCorreo.setVisible(false);
				menuItemRemove.setEnabled(false);
				btnAceptar.setEnabled(true);
				table.setRowSelectionAllowed(false);
				table.setColumnSelectionAllowed(false);
				table.setCellSelectionEnabled(false);

				btnModificar.setEnabled(false);
				btnMasEliminar.setVisible(false);
				btnMasEliminar2.setVisible(false);
				ListModel<String> model = new DefaultComboBoxModel();
				DefaultTableModel model2 = new DefaultTableModel();
				tableTelefono.setEnabled(false);
				tableTelefono.setModel(model2);

				jlistCorreos.setEnabled(false);
				jlistCorreos.setModel(model);

				desactivarCampos();
				textFieldApodo.setEnabled(false);
				textFieldApodo.setText(null);
				textFieldNombre.setEnabled(false);
				textFieldNombre.setText(null);
				textFieldApellido.setEnabled(false);
				textFieldApellido.setText(null);
				comboSexo.setEnabled(false);
				comboSexo.setSelectedIndex(0);
				textFieldEmpresa.setEnabled(false);
				textFieldEmpresa.setText(null);
			}
		}

		if (e.getSource() == btnModificar) {
			String direccion = textFieldDireccion1.getText() + ", " + textFieldDireccion2.getText() + ", "
					+ textFieldDireccion3.getText() + ", " + textFieldDireccion4.getText() + ", "
					+ textFieldDireccion5.getText() + ", " + textFieldDireccion6.getText() + ", "
					+ comboProvincias.getSelectedItem();

			Contacto c = null;
			if (radio1.isSelected()) {
				Apodo ap = new Apodo(idModificarContacto, direccion, textFieldNotas.getText(), textFieldApodo.getText(),
						comboSexo.getSelectedItem().toString());
				c = ap;
			}
			if (radio3.isSelected()) {
				Empresa em = new Empresa(idModificarContacto, direccion, textFieldNotas.getText(),
						textFieldEmpresa.getText());
				c = em;
			}
			if (radio2.isSelected()) {
				Persona ps = new Persona(idModificarContacto, direccion, textFieldNotas.getText(),
						textFieldNombre.getText(), textFieldApellido.getText(), comboSexo.getSelectedItem().toString());
				c = ps;
			}

			try {
				// modificamos Contacto
				ag.modificarContacto(c);

				// Aficiones, primer borramos todas las que tiene y luego añadimos las nuevas
				ag.borrarAficionContacto(idModificarContacto);
				int[] indices = jlistAficiones.getSelectedIndices();
				for (int i = 0; i < indices.length; i++) {
					ag.anadirAficionContacto(idModificarContacto, jlistAficiones.getSelectedValuesList().get(i));

				}

				// Telefonos
				// COGEMOS DE LA JLIST CADA UNO Y LO AÑADIMOS
			
				ag.borrarTelefono(idModificarContacto);
				Telefono t = null;
				
				
				for (int i = 0; i < tableTelefono.getRowCount(); i++) {

					if(tableTelefono.getValueAt(i, 0).equals("Fijo")) {
						t = new Telefono(null, (String) tableTelefono.getValueAt(i, 1), idModificarContacto);
					}
					if(modeloTelefono.getValueAt(i, 0).equals("Movil")) {
						t = new Telefono((String) tableTelefono.getValueAt(i, 1), null, idModificarContacto);
					}
					

					ag.anadirTelefono(t);
				}

				// Correo
				ag.borrarCorreo(idModificarContacto);
				for (int i = 0; i < jlistCorreos.getModel().getSize(); i++) {
					Correo cc = new Correo(jlistCorreos.getModel().getElementAt(i), idModificarContacto);
					ag.anadirCorreo(cc);
				}

				llenarTabla(llenarTabla, "");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == btnMasTelefonos) {
			
			String tipo = "";
			if(comboTelefono.getSelectedItem().equals("Movil")) {
				tipo = "Movil";
			}
			else if(comboTelefono.getSelectedItem().equals("Fijo")) {
				tipo = "Fijo";
			}
			String telefono = textFieldTelefono.getText();
			modeloTelefono.addRow(new Object[] {
					tipo,
					telefono
			});
			
		}
		if (e.getSource() == btnMasEliminar) {

			int selectedItem = tableTelefono.getSelectedRow();
			modeloTelefono.removeRow(selectedItem);

			
		}

		if (e.getSource() == btnMasCorreo) {
			if (!textFieldCorreo.getText().isEmpty()) {
				Correo cc = new Correo(textFieldCorreo.getText(), idModificarContacto);
				modelCorreo.addElement(cc.getCorreo());
				textFieldCorreo.setText("");
			}
		}
		if (e.getSource() == btnMasEliminar2) {
			String selectedItem = (String) jlistCorreos.getSelectedValue();

			for (int i = 0; i < jlistCorreos.getModel().getSize(); i++) {
				if (modelCorreo.getElementAt(i).equals(selectedItem)) {
					modelCorreo.removeElementAt(i);
				}
			}
		}
	}
	public void llenarAficiones() {
		ArrayList<String> campos = new ArrayList<String>();
		try {
			campos = ag.getAficion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				campos.toArray(new String[campos.size()]));
		jlistAficiones.setModel(model);
	}
}
