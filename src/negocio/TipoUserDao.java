package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.TipoUser;
import dominio.Usuario;

public class TipoUserDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	public TipoUser obtenerPorId (int Id) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TipoUser tipo = new TipoUser();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, tipo FROM tipoUsuarios where Id = ? ");
			miSentencia.setInt(1, Id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			tipo.setId(resultado.getInt(1));
			tipo.setNombre(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return tipo;
	}

}
