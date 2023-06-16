package dominio;

public class Usuario {
private int Id; 
private String Nombre; 
private String Clave; 
private TipoUser Tipo; 
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getClave() {
	return Clave;
}
public void setClave(String clave) {
	Clave = clave;
}
public TipoUser getTipo() {
	return Tipo;
}
public void setTipo(TipoUser tipo) {
	Tipo = tipo;
}

}




