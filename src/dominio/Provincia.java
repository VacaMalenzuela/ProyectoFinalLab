package dominio;

public class Provincia {

	private int Id; 
	private String Provincia; 
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String prov) {
		Provincia = prov;
	}
	public String toString() {
		return "Provincia [Id=" + Id + ", Provincia=" + Provincia + "]";
	}
}
