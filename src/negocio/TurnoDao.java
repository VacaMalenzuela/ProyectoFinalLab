package negocio;

import java.sql.Connection;

import dominio.Especialidad;
import dominio.Horarios;
import dominio.Localidad;
import dominio.Medico;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;
import dominio.Turno;
import dominio.Usuario;

public class TurnoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public int agregarTurno(Turno turno)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query2 = "Insert into turnos (dniMedico, Fecha, Hora, dniPaciente, IdEstado) values ('"+turno.getMedico.getDni()+"','"+ turno.getFecha()+"','"+ turno.getHora()+"','"+ turno.getPaciente.getDni()+"','"+turno.getEstado.getId()"');";
	
			filas=st.executeUpdate(query2);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	
	}

}
