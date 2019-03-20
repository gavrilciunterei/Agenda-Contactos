package Clases;

public class Telefono {

	private String movil;
	private String fijo;
	private int id;
	
	public static int LEN_TELEFONO = 9;

	public Telefono(String movil, String fijo, int id) {
		super();
		this.movil = movil;
		this.fijo = fijo;
		this.id = id;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Telefono [movil=" + movil + ", fijo=" + fijo + ", id=" + id + "]";
	}
	

}
