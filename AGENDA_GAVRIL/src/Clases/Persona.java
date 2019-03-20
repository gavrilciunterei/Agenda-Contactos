package Clases;

public class Persona extends Contacto {

	private String nombre;
	private String apellido;
	private String sexo;
	

	public Persona(int id, String direccion, String notas, String nombre, String apellido, String sexo) {
		super(id, direccion, notas);
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
	}
	


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + "]";
	}

	
}
