package dominio;

public class Turno {

	private int Id; 
	private Medico medico;
	private String fecha;
	private int hora;
	private Paciente paciente;
	private EstadoTurno estado;
	
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
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
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
	
	
}
