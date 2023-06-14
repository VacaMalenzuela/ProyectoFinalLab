package dominio;

public class Localidad {
	private int Id; 
	private String Localidad; 
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String loc) {
		Localidad = loc;
	}
	public String toString() {
		return "Provincia [Id=" + Id + ", Provincia=" + Localidad + "]";
	}
	
	
}
