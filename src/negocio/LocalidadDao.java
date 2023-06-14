package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Localidad;
import dominio.Provincia;

public class LocalidadDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public ArrayList<Localidad> obtenerLocalidades() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Id, Localidad FROM Localidades");
			
			while(rs.next()){
				
				Localidad objNac = new Localidad();
				objNac.setId(rs.getInt("Id"));
				objNac.setLocalidad(rs.getString("Localidad"));
				
				lista.add(objNac);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	public Localidad obtenerLocalidadPorId(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Localidad loc = new Localidad();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("Select Id, Localidad from Localidades where Id = ?");
			miSentencia.setInt(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			loc.setId(resultado.getInt(1));
			loc.setLocalidad(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return loc;
	}
}
