package Clases;

public class Empresa extends Contacto {

	private String empresa;

	public Empresa(int id, String direccion, String notas, String empresa) {
		super(id, direccion, notas);
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	
}
