package dominio;

public class Paciente extends Persona {
	public Paciente () {
		super();
	}
	
	public String getDatoGeneralesPaciente() {
        return this.getApellido() + " " + this.getNombre()+ " - (" + this.getDni() + " )";
    }
}
