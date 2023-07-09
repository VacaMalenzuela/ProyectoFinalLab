package dominio;

import java.util.ArrayList;

public class Medico extends Persona {
	
	private Especialidad especialidad;
	private Usuario usuario;
	private ArrayList<Horarios> horarios;
	
	
	
	public ArrayList<Horarios> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<Horarios> horarios) {
		this.horarios = horarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public Medico () {
		super();
	}
	
	
	

}
