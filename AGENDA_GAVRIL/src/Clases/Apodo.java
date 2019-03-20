package Clases;

public class Apodo extends Contacto {

	private String apodo;
	private String sexo;
	
	public Apodo(int id, String direccion, String notas, String apodo,  String sexo) {
		super(id, direccion, notas);
		this.apodo = apodo;
		this.sexo = sexo;
	}


	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Apodo [apodo=" + apodo + ", sexo=" + sexo + ", getId()=" + getId() + ", getDireccion()="
				+ getDireccion() + ", getNotas()=" + getNotas() + "]";
	}


	
}
