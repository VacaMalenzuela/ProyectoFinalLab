package dominio;

public class Especialidad extends Persona {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String tipo) {
		this.especialidad = tipo;
	}
	private int id;
	private String especialidad;
	
	public Especialidad (int id, String tipo) {
		this.id = id;
		this.especialidad = tipo;
	}
	public Especialidad () {
		
	}
}
