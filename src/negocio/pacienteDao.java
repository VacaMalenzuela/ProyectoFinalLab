package negocio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;

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
			String query = "Insert into Pacientes (Dni,Nombre,Apellido,Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico,Telefono, Estado) values ('"+pac.getDni()+"','"+pac.getNombre()+"','"+ pac.getApellido()+"','"+ pac.getSexo()+"',"+ pac.getNacionalidad().getId()+",'"+ pac.getFechaNacimiento()+"','"+ pac.getDireccion()+"',"+ pac.getLocalidad().getId()+","+ pac.getProvincia().getId()+",'"+ pac.getCorreoElectronico()+"','"+ pac.getTelefono()+"',1);";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	
	}
	
	
	
	public ArrayList<Paciente> obtenerPacientes() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Paciente> lista = new ArrayList<Paciente>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Dni, Nombre,Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono FROM PACIENTES WHERE Estado = 1");
			
			while(rs.next()){
				
				Paciente paciente = new Paciente();
				paciente.setDni(rs.getString("Dni"));
				paciente.setNombre(rs.getString("Nombre"));
				paciente.setApellido(rs.getString("Apellido"));
				paciente.setSexo(rs.getString("Sexo"));
				paciente.setFechaNacimiento(rs.getString("FechaNacimiento"));
				paciente.setDireccion(rs.getString("Direccion"));
				paciente.setCorreoElectronico(rs.getString("CorreoElectronico"));
				paciente.setTelefono(rs.getString("Telefono"));
				
				NacionalidadDao nacDao = new NacionalidadDao(); 
				Nacionalidad nac= nacDao.obtenerNacionalidadPorId(rs.getInt("idNacionalidad"));
				paciente.setNacionalidad(nac);
				
				LocalidadDao locDao = new LocalidadDao(); 
				Localidad loc= locDao.obtenerLocalidadPorId(rs.getInt("idLocalidad"));
				paciente.setLocalidad(loc);
				
				ProvinciaDao provDao = new ProvinciaDao(); 
				Provincia prov= provDao.obtenerProvinciaPorId(rs.getInt("idProvincia"));
				paciente.setProvincia(prov);
				
				lista.add(paciente);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	
	
	public Paciente obtenerPacientePorDni(String id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Paciente pac = new Paciente();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Dni, Nombre,Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono FROM PACIENTES where Estado = 1 AND Dni = ? ");
			miSentencia.setString(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			pac.setDni(resultado.getString(1));
			pac.setNombre(resultado.getString(2));
			pac.setApellido(resultado.getString(3));
			pac.setSexo(resultado.getString(4));
				NacionalidadDao nacDao = new NacionalidadDao(); 
				Nacionalidad nac = nacDao.obtenerNacionalidadPorId(resultado.getInt(5));
				pac.setNacionalidad(nac);
			pac.setFechaNacimiento(resultado.getString(6));
			pac.setDireccion(resultado.getString(7));
				LocalidadDao locDao = new LocalidadDao();
				Localidad loc = locDao.obtenerLocalidadPorId(resultado.getInt(8));
				pac.setLocalidad(loc);
				ProvinciaDao provDao = new ProvinciaDao(); 
				Provincia prov = provDao.obtenerProvinciaPorId(resultado.getInt(9));
				pac.setProvincia(prov);
			pac.setCorreoElectronico(resultado.getString(10));
			pac.setTelefono(resultado.getString(11));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return pac;
	}
	
	public int ActualizarPaciente (Paciente pac) {
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
			String query = "UPDATE PACIENTES SET NOMBRE = '"+pac.getNombre()+"', APELLIDO = '"+pac.getApellido()+"', SEXO = '"+pac.getSexo()+"', idNacionalidad= "+pac.getNacionalidad().getId()+", FECHANACIMIENTO = '"+pac.getFechaNacimiento()+"', DIRECCION = '"+pac.getDireccion()+"', IdLocalidad ="+pac.getLocalidad().getId()+", idProvincia = "+pac.getProvincia().getId()+", CorreoElectronico = '"+pac.getCorreoElectronico()+"', Telefono = '"+pac.getTelefono()+"' WHERE DNI = '"+pac.getDni()+"';";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public int EliminarPaciente (String Dni) { 
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
			String query = "UPDATE PACIENTES SET ESTADO = 0 WHERE DNI="+Dni+";";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
}
