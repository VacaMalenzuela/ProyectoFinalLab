package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Especialidad;
import dominio.Horarios;
import dominio.Localidad;
import dominio.Medico;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;
import dominio.Usuario;



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
	
	
	
	
	public ArrayList<Medico> obtenerMedicos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Medico> lista = new ArrayList<Medico>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT Dni, Nombre,Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono, idEspecialidad, idUsuario FROM MEDICOS WHERE Estado = 1");
			
			while(rs.next()){
				
				Medico medico = new Medico();
				medico.setDni(rs.getString("Dni"));
				medico.setNombre(rs.getString("Nombre"));
				medico.setApellido(rs.getString("Apellido"));
				medico.setSexo(rs.getString("Sexo"));
				medico.setFechaNacimiento(rs.getString("FechaNacimiento"));
				medico.setDireccion(rs.getString("Direccion"));
				medico.setCorreoElectronico(rs.getString("CorreoElectronico"));
				medico.setTelefono(rs.getString("Telefono"));
				
				NacionalidadDao nacDao = new NacionalidadDao(); 
				Nacionalidad nac= nacDao.obtenerNacionalidadPorId(rs.getInt("idNacionalidad"));
				medico.setNacionalidad(nac);
				
				LocalidadDao locDao = new LocalidadDao(); 
				Localidad loc= locDao.obtenerLocalidadPorId(rs.getInt("idLocalidad"));
				medico.setLocalidad(loc);
				
				ProvinciaDao provDao = new ProvinciaDao(); 
				Provincia prov= provDao.obtenerProvinciaPorId(rs.getInt("idProvincia"));
				medico.setProvincia(prov);
				
				EspecialidadDao espeDao = new EspecialidadDao(); 
				Especialidad espe = espeDao.obtenerEspecialidadPorId(rs.getInt("idEspecialidad"));
				medico.setEspecialidad(espe);
				
				UsuarioDao usuDao = new UsuarioDao(); 
				Usuario usu = usuDao.obtenerUsuarioPorId(rs.getInt("idUsuario"));
				
				medico.setUsuario(usu);
				lista.add(medico);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	public int EliminarMedico (String Dni) {
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
			String query = "UPDATE MEDICOS SET ESTADO = 0 WHERE Dni= '"+Dni+"';";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	

}
