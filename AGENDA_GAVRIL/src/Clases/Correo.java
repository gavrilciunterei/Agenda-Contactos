package Clases;

public class Correo {

	
	private String correo;
	private int id;
	
	public static int LEN_CORREO = 200;
	
	public Correo(String correo, int id) {
		this.correo = correo;
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
