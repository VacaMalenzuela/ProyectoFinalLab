package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
