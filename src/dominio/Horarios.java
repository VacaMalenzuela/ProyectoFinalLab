package dominio;

public class Horarios {  
   public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDia() {
		return Dia;
	}
	public void setDia(String dia) {
		Dia = dia;
	}
	public int getHorarioInicio() {
		return HorarioInicio;
	}
	public void setHorarioInicio(int horarioInicio) {
		HorarioInicio = horarioInicio;
	}
	public int getHorarioFin() {
		return HorarioFin;
	}
	public void setHorarioFin(int horarioFin) {
		HorarioFin = horarioFin;
	}
private int Id; 
    private String Dia; 
    private int HorarioInicio; 
    private int HorarioFin; 
    
    
    public String getTurno() {
        return getDia() + " " + getHorarioInicio()+ " - " + getHorarioFin();
    }
    	
    
}
