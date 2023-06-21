package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Horarios;
import dominio.Localidad;
import dominio.Medico;



public class MedicoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public int agregarMedico(Medico  medico)
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
			String query1 = "Insert into medicos (Dni,Nombre,Apellido,Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico,Telefono, idEspecialidad, idUsuario,  Estado) values ('"+medico.getDni()+"','"+medico.getNombre()+"','"+ medico.getApellido()+"','"+ medico.getSexo()+"',"+ medico.getNacionalidad().getId()+",'"+ medico.getFechaNacimiento()+"','"+ medico.getDireccion()+"',"+ medico.getLocalidad().getId()+","+ medico.getProvincia().getId()+",'"+ medico.getCorreoElectronico()+"','"+ medico.getTelefono()+"',"+medico.getEspecialidad().getId()+", "+medico.getUsuario().getId()+", 1);";
	
			filas+=st.executeUpdate(query1);

			
			filas += AgregarHorarios (medico);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	
	}
	
	public int AgregarHorarios(Medico medico) { 
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
			ArrayList<Horarios> lstHorarios = new ArrayList<Horarios>();
			lstHorarios = medico.getHorarios();
			for (Horarios hs : lstHorarios) {
				String query = "INSERT INTO MEDICOXHORARIO (dniMedico, idHorario) VALUES ('"+medico.getDni()+"',"+ hs.getId()+"); ";
				st.executeUpdate(query);
			}
				

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	

}
