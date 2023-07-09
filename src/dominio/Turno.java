package dominio;

public class Turno {

	private int Id; 
	private String fecha;
	private String hora;
	private String observacion;
	
	private Paciente paciente;
	private EstadoTurno estado;
	private Medico medico;
	
	
	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Turno() {}

	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public EstadoTurno getEstado() {
		return estado;
	}
	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}
	public String getFechaTurno() {
        return this.getFecha()+ " - " + this.getHora();
    }
	
	
	
}
