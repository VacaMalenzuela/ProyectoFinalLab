package dominio;

import java.util.ArrayList;

public class Nacionalidad {
	
	private int Id; 
	private String Nacionalidad; 
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	@Override
	public String toString() {
		return "Nacionalidad [Id=" + Id + ", Nacionalidad=" + Nacionalidad + "]";
	}
	
	

}
