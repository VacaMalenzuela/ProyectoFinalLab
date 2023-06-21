package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Horarios;
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;

public class HorariosDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	public ArrayList<Horarios> obtenerHorarios() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Horarios> lista = new ArrayList<Horarios>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Id, dia, horarioInicio, horarioFin FROM HORARIOS");
			
			while(rs.next()){
				
				Horarios horas = new Horarios();
				horas.setId(rs.getInt("Id"));
				horas.setDia(rs.getString("dia"));
				horas.setHorarioInicio(rs.getInt("horarioInicio"));
				horas.setHorarioFin(rs.getInt("horarioFin"));

				
				lista.add(horas);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	
	public Horarios obtenerHorarioPorId(String id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Horarios horario = new Horarios();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, dia, horarioInicio, horarioFin FROM HORARIOS where Id = ?");
			miSentencia.setString(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			horario.setId(resultado.getInt(1));
			horario.setDia(resultado.getString(2));
			horario.setHorarioInicio(resultado.getInt(3));
			horario.setHorarioFin(resultado.getInt(4));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return horario;
	}
}
