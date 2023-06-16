package dominio;
import dominio.Nacionalidad;
public class Persona {
	//Atributos
private String Dni;
private String Nombre; 
private String Apellido; 
private String Sexo; 
private Nacionalidad Nacionalidad; 
private String FechaNacimiento; 
private String Direccion; 
private Localidad Localidad; 
private Provincia Provincia; 
private String CorreoElectronico; 
private String Telefono; 

//GETS Y SETS
public String getDni() {
	return Dni;
}
public void setDni(String dni) {
	Dni = dni;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getApellido() {
	return Apellido;
}
public void setApellido(String apellido) {
	Apellido = apellido;
}
public String getSexo() {
	return Sexo;
}
public void setSexo(String sexo) {
	Sexo = sexo;
}
public Nacionalidad getNacionalidad() {
	return Nacionalidad;
}
public void setNacionalidad(Nacionalidad nacionalidad) {
	Nacionalidad = nacionalidad;
}
public String getFechaNacimiento() {
	return FechaNacimiento;
}
public void setFechaNacimiento(String fechaNacimiento) {
	FechaNacimiento = fechaNacimiento;
}
public String getDireccion() {
	return Direccion;
}
public void setDireccion(String direccion) {
	Direccion = direccion;
}
public Localidad getLocalidad() {
	return Localidad;
}
public void setLocalidad(Localidad localidad) {
	Localidad = localidad;
}
public Provincia getProvincia() {
	return Provincia;
}
public void setProvincia(Provincia provincia) {
	Provincia = provincia;
}
public String getCorreoElectronico() {
	return CorreoElectronico;
}
public void setCorreoElectronico(String correoElectronico) {
	CorreoElectronico = correoElectronico;
}
public String getTelefono() {
	return Telefono;
}
public void setTelefono(String telefono) {
	Telefono = telefono;
}

//CONSTRUCTOR 

public Persona () { 
	
}


}
