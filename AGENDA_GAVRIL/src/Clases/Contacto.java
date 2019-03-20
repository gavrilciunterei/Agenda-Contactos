package Clases;

public abstract class Contacto {

	private int id;
	private String direccion;
	private String notas;
	
	public static int LEN_APODO = 30;
	public static int LEN_NOMBRE = 30;
	public static int LEN_APELLIDO = 30;
	public static int LEN_EMPRESA = 10;
	public static int LEN_SEXO = 1;
	public static int LEN_DIRECCION = 30;
	public static int LEN_NOTAS = 255;


	public Contacto(int id, String direccion, String notas) {
		this.id = id;
		this.direccion = direccion;
		this.notas = notas;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getNotas() {
		return notas;
	}


	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	

	
}
