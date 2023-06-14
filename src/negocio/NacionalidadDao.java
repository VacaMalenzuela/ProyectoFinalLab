package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Nacionalidad;

public class NacionalidadDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public ArrayList<Nacionalidad> obtenerNacionalidades() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Id, Nacionalidad FROM nacionalidades");
			
			while(rs.next()){
				
				Nacionalidad objNac = new Nacionalidad();
				objNac.setId(rs.getInt("Id"));
				objNac.setNacionalidad(rs.getString("Nacionalidad"));
				
				lista.add(objNac);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	
	public Nacionalidad obtenerNacionalidadPorId(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Nacionalidad nac = new Nacionalidad();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("Select Id, Nacionalidad from Nacionalidades where Id = ?");
			miSentencia.setInt(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			nac.setId(resultado.getInt(1));
			nac.setNacionalidad(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return nac;
	}
	
	
}
