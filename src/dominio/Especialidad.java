package dominio;

public class Especialidad extends Persona {
	private int id;
	private String tipo;
	
	public Especialidad (int id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
}
