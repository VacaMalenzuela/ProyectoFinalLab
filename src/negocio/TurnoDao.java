package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dominio.EstadoTurno;
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;
import dominio.Turno;


public class TurnoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	public int agregarTurno(Turno turno) { 
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
			String query2 = "Insert into turnos (dniMedico, Fecha, Hora, dniPaciente, IdEstado) values ('"+turno.getMedico().getDni()+"','"+ turno.getFecha()+"','"+ turno.getHora()+"','"+ turno.getPaciente().getDni()+"','"+turno.getEstado().getId()+"');";
	
			filas=st.executeUpdate(query2);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public EstadoTurno obtenerEstadoDefault () { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EstadoTurno estado = new EstadoTurno();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("select Id, Estado from EstadosTurnos where estado = 'Libre';");
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			estado.setId(resultado.getInt(1));
			estado.setEstado(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return estado;
		
	}

}
