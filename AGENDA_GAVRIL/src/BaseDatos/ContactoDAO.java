package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import Clases.Aficion;
import Clases.Apodo;
import Clases.Contacto;
import Clases.Correo;
import Clases.Empresa;
import Clases.Persona;
import Clases.Telefono;

public class ContactoDAO {

	private static Connection conn;
	private ArrayList<Telefono> tlf;
	private ArrayList<Correo> cor;
	private ArrayList<Aficion> afi;
	private String accion = "", accion2 = "", accion3 = "", accion4 = "", aficion = "", correo = "", movil = "",
			nombre = "", apellido = "", empresa = "", apodo = "", sexo = "", direccion = "", notas = "", fijo="";
	private int id = 0;
	private PreparedStatement psContacto = null, psAficion = null, psCorreo = null, psTelefono = null;
	ResultSet rsContacto = null, rsAficion = null;


	public ContactoDAO() throws SQLException {

		conn = new Conexion().get();
		createTableContactos();
		createTableTelefono();
		createTableCorreo();
		createTableAficion();
		createTableAficionContacto();

	}

	private void createTableContactos() throws SQLException {

		String sql = String.format(
				"CREATE TABLE IF NOT EXISTS CONTACTO (" + "ID INTEGER PRIMARY KEY NOT NULL," + "NOMBRE VARCHAR(%d),"
						+ "APELLIDO VARCHAR(%d)," + "APODO VARCHAR(%d)," + "EMPRESA VARCHAR(%d)," + "SEXO VARCHAR(%d),"
						+ "DIRECCION VARCHAR(%d)," + "NOTAS VARCHAR(%d))",
				Contacto.LEN_NOMBRE, Contacto.LEN_APELLIDO, Contacto.LEN_APODO, Contacto.LEN_EMPRESA, Contacto.LEN_SEXO,
				Contacto.LEN_DIRECCION, Contacto.LEN_NOTAS);

		conn.createStatement().executeUpdate(sql);
		
	}

	private void createTableTelefono() throws SQLException {

		String sql = String.format(
				"CREATE TABLE IF NOT EXISTS TELEFONO (" + "MOVIL VARCHAR(%d)," + "FIJO VARCHAR(%d),"+ "ID INTEGER NOT NULL)",
				Telefono.LEN_TELEFONO,
				Telefono.LEN_TELEFONO
				);

		conn.createStatement().executeUpdate(sql);

	}

	private void createTableCorreo() throws SQLException {

		String sql = String.format(
				"CREATE TABLE IF NOT EXISTS CORREO (" + "CORREO VARCHAR(%d) NOT NULL," + "ID INTEGER NOT NULL)",
				Correo.LEN_CORREO);

		conn.createStatement().executeUpdate(sql);

	}

	private void createTableAficionContacto() throws SQLException {

		String sql = "CREATE TABLE IF NOT EXISTS AFICIONCONTACTO (" + "AFICION VARCHAR NOT NULL,"
				+ "ID INTEGER NOT NULL" + ");";

		conn.createStatement().executeUpdate(sql);

	}

	private void createTableAficion() throws SQLException {

		String sql = String.format("CREATE TABLE IF NOT EXISTS AFICION (" + "AFICION VARCHAR(%d) PRIMARY KEY NOT NULL)",
				Aficion.LEN_AFICION);

		conn.createStatement().executeUpdate(sql);

	}

	public boolean anadirContacto(Contacto c) throws SQLException {

		String sql = "INSERT INTO CONTACTO (ID, NOMBRE, APELLIDO, APODO, EMPRESA, SEXO, DIRECCION, NOTAS)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		Persona p = null;
		Empresa e = null;
		Apodo a = null;
		
		if (c.getClass().getName().equals(Persona.class.getName())) {
			p = (Persona) c;
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setString(3, p.getApellido());
			ps.setString(4, null);
			ps.setString(5, null);
			ps.setString(6, p.getSexo());
			ps.setString(7, p.getDireccion());
			ps.setString(8, p.getNotas());
		} else if (c.getClass().getName().equals(Empresa.class.getName())) {
			e = (Empresa) c;
			ps.setInt(1, e.getId());
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, null);
			ps.setString(5, e.getEmpresa());
			ps.setString(6, null);
			ps.setString(7, e.getDireccion());
			ps.setString(8, e.getNotas());
		} else if (c.getClass().getName().equals(Apodo.class.getName())) {
			a = (Apodo) c;
			ps.setInt(1, a.getId());
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, a.getApodo());
			ps.setString(5, null);
			ps.setString(6, a.getSexo());
			ps.setString(7, a.getDireccion());
			ps.setString(8, a.getNotas());
		}

		int contador = ps.executeUpdate();

		if (contador > 0) {
			return true;
		}
		return false;
	}

	public boolean anadirTelefono(Telefono t) throws SQLException {

		String sql = "INSERT INTO TELEFONO (MOVIL, FIJO, ID)" + "VALUES (?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getMovil());
		ps.setString(2, t.getFijo());
		ps.setInt(3, t.getId());

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}
		return false;

	}

	public boolean anadirCorreo(Correo cc) throws SQLException {

		String sql = "INSERT INTO CORREO (CORREO, ID)" + "VALUES (?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cc.getCorreo());
		ps.setInt(2, cc.getId());

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}
		return false;

	}

	public boolean anadirAficionContacto(int id, String aficion) throws SQLException {

		String sql = "INSERT INTO AFICIONCONTACTO (AFICION, ID)" + "VALUES (?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aficion);
		ps.setInt(2, id);

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}
		return false;

	}

	public boolean anadirAficion(Aficion af) throws SQLException {

		String sql = "INSERT INTO AFICION (AFICION)" + "VALUES (?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, af.getAficion());

			int contador = ps.executeUpdate();
			if (contador > 0) {
				return true;
			}
		
		return false;

	}

	public boolean borrarAficion(String af) throws SQLException {

		String sql = "DELETE FROM AFICION WHERE AFICION = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, af);

		if (comprobarExistenciaAficion(af)) {
			if (!comprobarModificacionAficion(af)) {
				int contador = ps.executeUpdate();
				if (contador > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarAficion(String af, String newAf) throws SQLException {

		String sql = "UPDATE AFICION SET AFICION = ? WHERE AFICION = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, newAf);
		ps.setString(2, af);

	
				int contador = ps.executeUpdate();
				if (contador > 0) {
					return true;
				}
				
		return false;
	}

	public boolean comprobarExistenciaAficion(String af) throws SQLException {

		String sql = "SELECT * FROM AFICION WHERE AFICION = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, af);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		}

		return false;
	}

	public boolean comprobarModificacionAficion(String af) throws SQLException {

		String sql = "SELECT * FROM AFICIONCONTACTO WHERE AFICION = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, af);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		}

		return false;
	}

	public int getLastId() throws SQLException {

		String sql = "SELECT MAX(ID) FROM CONTACTO";
		ResultSet rs = conn.createStatement().executeQuery(sql);

		return rs.getInt(1) + 1;
	}

	public boolean borrarContacto(int id) throws SQLException {

		String sql = "DELETE FROM CONTACTO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		if (comprobarExistenciaContacto(id)) { // mirar mejor por si se puede eliminar
			int contador = ps.executeUpdate();
			if (contador > 0) {
				borrarContactoAficion(id);
				borrarTelefono(id);
				borrarCorreo(id);
				return true;
			}
		}
		return false;
	}

	public boolean borrarContactoAficion(int id) throws SQLException {

		String sql = "DELETE FROM AFICIONCONTACTO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}
		return false;
	}

	public boolean borrarTelefono(int id) throws SQLException {

		String sql = "DELETE FROM TELEFONO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}

		return false;
	}

	public boolean borrarCorreo(int id) throws SQLException {

		String sql = "DELETE FROM CORREO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;
		}

		return false;
	}

	private boolean comprobarExistenciaContacto(int id) throws SQLException {

		String sql = "SELECT * FROM CONTACTO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Aficion> getAficionTabla(String tipo, String comando) throws SQLException {
		ArrayList<Aficion> aficion = new ArrayList<>();
		
		if (tipo.equals("normal")) {
			String llenarTablaNormal = "SELECT * FROM AFICION";
			rsAficion = conn.createStatement().executeQuery(llenarTablaNormal);
		}
		if (tipo.equals("aficion")) {
			accion = "SELECT * FROM AFICION WHERE AFICION = ?";
			psAficion = conn.prepareStatement(accion);
			psAficion.setString(1, comando);
			rsAficion = psAficion.executeQuery();
		}
		
		while(rsAficion.next()) {
			aficion.add(new Aficion(rsAficion.getString("AFICION")));
			
		}
		
		return aficion;
	}
	
	public ArrayList<Contacto> getContacto(String tipo, String comando) throws SQLException {
		ArrayList<Contacto> contacto = new ArrayList<>();
		tlf = new ArrayList<Telefono>();
		cor = new ArrayList<Correo>();
		afi = new ArrayList<Aficion>();
		
		Persona p = null;
		Empresa e = null;
		Apodo a = null;
		Contacto c = null;

		
		if (tipo.equals("normal")) {
			String llenarTablaNormal = "SELECT * FROM CONTACTO";
			rsContacto = conn.createStatement().executeQuery(llenarTablaNormal);
		}

		if (tipo.equals("nombre")) {
			accion = "SELECT * FROM CONTACTO WHERE NOMBRE = ?";
			psContacto = conn.prepareStatement(accion);
			psContacto.setString(1, comando);
			rsContacto = psContacto.executeQuery();
		}
		if (tipo.equals("apodo")) {
			accion = "SELECT * FROM CONTACTO WHERE APODO = ?";
			psContacto = conn.prepareStatement(accion);
			psContacto.setString(1, comando);
			rsContacto = psContacto.executeQuery();
		}
		if (tipo.equals("apellido")) {
			accion = "SELECT * FROM CONTACTO WHERE APELLIDO = ?";
			psContacto = conn.prepareStatement(accion);
			psContacto.setString(1, comando);
			rsContacto = psContacto.executeQuery();
		}
		if (tipo.equals("empresa")) {
			accion = "SELECT * FROM CONTACTO WHERE EMPRESA = ?";
			psContacto = conn.prepareStatement(accion);
			psContacto.setString(1, comando);
			rsContacto = psContacto.executeQuery();
		}

		if (tipo.equals("id")) {
			accion = "SELECT * FROM CONTACTO WHERE ID = ?";
			psContacto = conn.prepareStatement(accion);
			psContacto.setInt(1, Integer.parseInt(comando));
			rsContacto = psContacto.executeQuery();
		}
		
		if (rsContacto != null) {
			while(rsContacto.next()) {
				
				accion2 = "SELECT * FROM AFICIONCONTACTO WHERE ID = ?";
				psAficion = conn.prepareStatement(accion2);
				aficion = "";

				accion3 = "SELECT * FROM CORREO WHERE ID = ?";
				psCorreo = conn.prepareStatement(accion3);
				correo = "";

				accion4 = "SELECT * FROM TELEFONO WHERE ID = ?";
				psTelefono = conn.prepareStatement(accion4);
				
				id = rsContacto.getInt("ID");
				nombre = rsContacto.getString("NOMBRE");
				apellido = rsContacto.getString("APELLIDO");
				apodo = rsContacto.getString("APODO");
				empresa = rsContacto.getString("EMPRESA");
				sexo = rsContacto.getString("SEXO");
				direccion = rsContacto.getString("DIRECCION");
				notas = rsContacto.getString("NOTAS");
				
				if(nombre != null) {
					p = new Persona(id, direccion, notas, nombre, apellido, sexo);
					contacto.add(p);
				}
				else if(apodo != null) {
					a = new Apodo(id, direccion, notas, apodo, sexo);
					contacto.add(a);
				}
				else if(empresa != null){
					e = new Empresa(id, direccion, notas, empresa);
					contacto.add(e);
				}
				
				psAficion.setInt(1, id);
				ResultSet rsAficion = psAficion.executeQuery();
	
				psCorreo.setInt(1, id);
				ResultSet rsCorreo = psCorreo.executeQuery();
	
				psTelefono.setInt(1, id);
				ResultSet rsTelefono = psTelefono.executeQuery();
				
				aficion = "";
				correo = "";
				movil = "";
				fijo = "";
				while (rsAficion.next()) {
					aficion += rsAficion.getString(1) + ",";
	
				}
	
				
				while (rsCorreo.next()) {
					correo += rsCorreo.getString(1) + ",";
				}
	
				while (rsTelefono.next()) {	
					setArrayTelefono(new Telefono(rsTelefono.getString(1), rsTelefono.getString(2), id));
				}
	
				// borramos ultima coma
				if (aficion != null && aficion.length() > 0) {
					aficion = aficion.substring(0, aficion.length() - 1);
	
				}
	
				if (correo != null && correo.length() > 0) {
					correo = correo.substring(0, correo.length() - 1);
				}
	
				if (movil != null && movil.length() > 0) {
					movil = movil.substring(0, movil.length() - 1);
				}
				if (fijo != null && fijo.length() > 0) {
					fijo = fijo.substring(0, fijo.length() - 1);
				}
	
	
				setArrayAficion(new Aficion(aficion));
				setArrayCorreo(new Correo(correo, id));
				
			}
		}
		

		if (tipo.equals("aficion")) {
			accion = "SELECT * FROM AFICIONCONTACTO WHERE AFICION = ? ORDER BY ID";
			psAficion = conn.prepareStatement(accion);
			psAficion.setString(1, comando);
			ResultSet rsAficion = psAficion.executeQuery();
			
			while (rsAficion.next()) {
				id = rsAficion.getInt("ID");
				aficion = rsAficion.getString("AFICION");
				
				accion2 = "SELECT * FROM CONTACTO WHERE ID = ?";
				psContacto = conn.prepareStatement(accion2);
				psContacto.setInt(1, id);
				rsContacto = psContacto.executeQuery();
				
				accion3 = "SELECT * FROM CORREO WHERE ID = ?";
				psCorreo = conn.prepareStatement(accion3);
				correo = "";

				accion4 = "SELECT * FROM TELEFONO WHERE ID = ?";
				psTelefono = conn.prepareStatement(accion4);

				nombre = rsContacto.getString("NOMBRE");
				apellido = rsContacto.getString("APELLIDO");
				apodo = rsContacto.getString("APODO");
				empresa = rsContacto.getString("EMPRESA");
				sexo = rsContacto.getString("SEXO");
				direccion = rsContacto.getString("DIRECCION");
				notas = rsContacto.getString("NOTAS");
				correo = "";
				movil = ""; 
				fijo = "";
				
				if(nombre != null) {
					p = new Persona(id, direccion, notas, nombre, apellido, sexo);
					contacto.add(p);
				}
				else if(apodo != null) {
					a = new Apodo(id, direccion, notas, apodo, sexo);
					contacto.add(a);
				}
				else if(empresa != null){
					e = new Empresa(id, direccion, notas, empresa);
					contacto.add(e);
				}
				
				
				psCorreo.setInt(1, id);
				ResultSet rsCorreo = psCorreo.executeQuery();

				psTelefono.setInt(1, id);
				ResultSet rsTelefono = psTelefono.executeQuery();
		

				while (rsCorreo.next()) {
					correo += rsCorreo.getString(1) + ",";

				}

				
				while (rsTelefono.next()) {
				
					setArrayTelefono(new Telefono(rsTelefono.getString(1), rsTelefono.getString(2), id));
				}
				
				// borramos ultima coma
			

				if (correo != null && correo.length() > 0) {
					correo = correo.substring(0, correo.length() - 1);
				}
				
				setArrayAficion(new Aficion(aficion));
				setArrayCorreo(new Correo(correo, id));
				
				
			}
		}
		
		return contacto;
	}
	private void setArrayTelefono(Telefono t) {
		tlf.add(t);
	}
	private void setArrayCorreo(Correo c) {
		cor.add(c);
	}
	private void setArrayAficion(Aficion af) {
		afi.add(af);
	}
	
	public ArrayList<Telefono> getArrayTelefono(){
		return tlf;
	}
	public ArrayList<Correo> getArrayCorreo(){
		return cor;
	}
	
	public ArrayList<Aficion> getArrayAficion(){
		return afi;
	}
	

	// METODO LLENADO ARRAY DE AFICIONES
	public ArrayList<String> getAficion() throws SQLException {
		String sql = "SELECT AFICION FROM AFICION";

		ArrayList<String> ls = new ArrayList<String>();
		ResultSet rs = conn.createStatement().executeQuery(sql);

		while (rs.next()) {
			ls.add(rs.getString("AFICION"));
		}

		return ls;

	}

	public boolean modificarContacto(Contacto c) throws SQLException {

		String sql = "UPDATE CONTACTO SET NOMBRE = ?, APELLIDO = ?, APODO = ?, EMPRESA = ?, SEXO = ?, DIRECCION = ?, NOTAS = ? WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		Persona p = null;
		Empresa e = null;
		Apodo a = null;

		if (c.getClass().getName().equals(Persona.class.getName())) {
			p = (Persona) c;
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setString(3, null);
			ps.setString(4, null);
			ps.setString(5, p.getSexo());
			ps.setString(6, p.getDireccion());
			ps.setString(7, p.getNotas());
			ps.setInt(8, p.getId());

		} else if (c.getClass().getName().equals(Empresa.class.getName())) {
			e = (Empresa) c;
			ps.setString(1, null);
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, e.getEmpresa());
			ps.setString(5, null);
			ps.setString(6, e.getDireccion());
			ps.setString(7, e.getNotas());
			ps.setInt(8, e.getId());

		} else if (c.getClass().getName().equals(Apodo.class.getName())) {
			a = (Apodo) c;
			ps.setString(1, null);
			ps.setString(2, null);
			ps.setString(3, a.getApodo());
			ps.setString(4, null);
			ps.setString(5, a.getSexo());
			ps.setString(6, a.getDireccion());
			ps.setString(7, a.getNotas());
			ps.setInt(8, a.getId());

		}

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;

		}
		return false;

	}

	public boolean borrarAficionContacto(int id) throws SQLException {

		String sql = "DELETE FROM AFICIONCONTACTO WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		int contador = ps.executeUpdate();
		if (contador > 0) {
			return true;

		}
		return false;

	}

}
