package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Nacionalidad;
import dominio.Provincia;
public class ProvinciaDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	
	public ArrayList<Provincia> obtenerProvincias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Id, Provincia FROM Provincias");
			
			while(rs.next()){
				
				Provincia objNac = new Provincia();
				objNac.setId(rs.getInt("Id"));
				objNac.setProvincia(rs.getString("Provincia"));
				
				lista.add(objNac);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	public Provincia obtenerProvinciaPorId(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Provincia prov = new Provincia();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("Select Id, Provincia from Provincias where Id = ?");
			miSentencia.setInt(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			prov.setId(resultado.getInt(1));
			prov.setProvincia(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
		}
		return prov;
	}
}
