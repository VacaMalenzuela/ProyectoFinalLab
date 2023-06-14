package negocio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Paciente;
public class pacienteDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public int agregarPaciente(Paciente pac)
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
			String query = "Insert into Pacientes (Dni,Nombre,Apellido,Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico,Telefono) values ('"+pac.getDni()+"','"+pac.getNombre()+"','"+ pac.getApellido()+"','"+ pac.getSexo()+"',"+ pac.getNacionalidad().getId()+",'"+ pac.getFechaNacimiento()+"','"+ pac.getDireccion()+"',"+ pac.getLocalidad().getId()+","+ pac.getProvincia().getId()+",'"+ pac.getCorreoElectronico()+"','"+ pac.getTelefono()+"');";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	
	}
}
