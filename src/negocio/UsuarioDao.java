package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dominio.Especialidad;
import dominio.Paciente;
import dominio.TipoUser;
import dominio.Usuario;

public class UsuarioDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public Usuario validoUsuarioLogueado(String Nombre, String Clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario us = new Usuario();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, Nombre, Clave, idTipoUsuario FROM USUARIOS WHERE Nombre = ? AND Clave= ? ;");
			miSentencia.setString(1, Nombre); 
			miSentencia.setString(2, Clave);//Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			us.setId(resultado.getInt(1));
			us.setNombre(resultado.getString(2));
			us.setClave(resultado.getString(3));
				TipoUserDao tipoDao = new TipoUserDao(); 
				TipoUser nac = tipoDao.obtenerPorId(resultado.getInt(4));
			us.setTipo(nac);
				
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return us;
	}
	
	//
	
	public int AgregarUsuario(Usuario usu)
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
			String query = "Insert into Usuarios (Nombre, Clave, idTipoUsuario) values ('"+usu.getNombre()+"', '"+usu.getClave()+"', 2);";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	
	}
	
	public Usuario obtenerUltimoUsuario()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario  usu = new Usuario();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("Select Id, Nombre, clave, idTipoUsuario from Usuarios order by Id Desc");
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			usu.setId(resultado.getInt(1));
			usu.setNombre(resultado.getString(2));
			usu.setClave(resultado.getString(3));
			
			TipoUser tipoUsuarios = new TipoUser (); 
			TipoUserDao tipousuarioNeg = new TipoUserDao();
			tipoUsuarios = tipousuarioNeg.obtenerPorId(resultado.getInt(4));
			usu.setTipo(tipoUsuarios);
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return  usu;
	}
	
	public Usuario obtenerUsuarioPorId(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario  usu = new Usuario();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, Nombre, Clave, idTipoUsuario FROM USUARIOS where Id = ?;");
			miSentencia.setInt(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			 usu.setId(resultado.getInt(1));
			 usu.setNombre(resultado.getString(2));
			 usu.setClave(resultado.getString(3));
			 
			 TipoUserDao tuserDao = new TipoUserDao();
			 TipoUser tus = tuserDao.obtenerPorId(resultado.getInt(4));
			 usu.setTipo(tus);
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return  usu;
	}

}
