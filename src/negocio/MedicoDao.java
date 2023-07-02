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
	
	
	
	public Medico obtenerMedicoPorDni(String id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Medico med = new Medico();
		ArrayList<Horarios> lstHorarios = new ArrayList<Horarios>(); 
		lstHorarios = obtenerHorariosPorMedico (id);
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			
			
			PreparedStatement miSentencia = con.prepareStatement("SELECT Dni, Nombre, Apellido, sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono, idEspecialidad, idUsuario FROM MEDICOS WHERE Estado = 1 AND Dni = ? ;");
			miSentencia.setString(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			med.setDni(resultado.getString(1));
			med.setNombre(resultado.getString(2));
			med.setApellido(resultado.getString(3));
			med.setSexo(resultado.getString(4));
				NacionalidadDao nacDao = new NacionalidadDao(); 
				Nacionalidad nac = nacDao.obtenerNacionalidadPorId(resultado.getInt(5));
				med.setNacionalidad(nac);
			med.setFechaNacimiento(resultado.getString(6));
			med.setDireccion(resultado.getString(7));
				LocalidadDao locDao = new LocalidadDao();
				Localidad loc = locDao.obtenerLocalidadPorId(resultado.getInt(8));
				med.setLocalidad(loc);
				ProvinciaDao provDao = new ProvinciaDao(); 
				Provincia prov = provDao.obtenerProvinciaPorId(resultado.getInt(9));
				med.setProvincia(prov);
			med.setCorreoElectronico(resultado.getString(10));
			med.setTelefono(resultado.getString(11));
				EspecialidadDao espeDao = new EspecialidadDao(); 
				Especialidad espe = espeDao.obtenerEspecialidadPorId(resultado.getInt(12));
			med.setEspecialidad(espe);
			med.setHorarios(lstHorarios);
				
				UsuarioDao usuDao = new UsuarioDao(); 
				Usuario usu = usuDao.obtenerUsuarioPorId(resultado.getInt(13));
		   med.setUsuario(usu);
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return med;
	}
	
	
	public ArrayList<Horarios> obtenerHorariosPorMedico (String Dni){ 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Horarios> lista = new ArrayList<Horarios>();
		
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			PreparedStatement miSentencia = conn.prepareStatement("SELECT idHorario FROM medicoXhorario where dnimedico = ? ");
			miSentencia.setString(1, Dni);
			ResultSet rs = miSentencia.executeQuery();
			
			while(rs.next()){
				Horarios hs = new Horarios(); 
				HorariosDao hsDao = new HorariosDao(); 
				hs = hsDao.obtenerHorarioPorId(rs.getString(1).toString());
				
				lista.add(hs);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
		
	}
	
	public void ElimnaHorariosXMedico (String Dni) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query1 = "DELETE FROM medicoXhorario Where dniMedico = '"+Dni+"';";
	
			st.executeUpdate(query1);

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public int ActualizaMedico (Medico med) { 
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
			String query1 = "UPDATE MEDICOS SET Nombre='"+med.getNombre()+"', APELLIDO = '"+med.getApellido()+"', sexo='"+med.getSexo()+"', idNacionalidad = "+med.getNacionalidad().getId()+", fechaNacimiento = '"+med.getFechaNacimiento()+"', Direccion = '"+med.getDireccion()+"', idLocalidad = "+med.getLocalidad().getId()+", idProvincia = "+med.getProvincia().getId()+", CorreoElectronico = '"+med.getCorreoElectronico()+"', Telefono = '"+med.getTelefono()+"', idEspecialidad = "+med.getEspecialidad().getId()+" where Dni = '43383650';";
	
			filas+=st.executeUpdate(query1);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public ArrayList<Medico> FiltrarMedicosPorEspecialidad(String idEspecialidad){ 
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
			
			ResultSet rs = st.executeQuery("SELECT Dni, Nombre,Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono, idEspecialidad, idUsuario FROM MEDICOS WHERE Estado = 1 AND idEspecialidad = "+idEspecialidad+";");
			
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
	
	
	public Medico validaMedicoPorDniYespecialidad(String dni, String idEspecialidad) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Medico med = new Medico();
		ArrayList<Horarios> lstHorarios = new ArrayList<Horarios>(); 
		lstHorarios = obtenerHorariosPorMedico (dni);
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			
			
			PreparedStatement miSentencia = con.prepareStatement("SELECT Dni, Nombre, Apellido, sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono, idEspecialidad, idUsuario FROM MEDICOS WHERE Estado = 1 AND Dni = ? AND idEspecialidad = ? ;");
			miSentencia.setString(1, dni);
			miSentencia.setString(2, idEspecialidad);//Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			med.setDni(resultado.getString(1));
			med.setNombre(resultado.getString(2));
			med.setApellido(resultado.getString(3));
			med.setSexo(resultado.getString(4));
				NacionalidadDao nacDao = new NacionalidadDao(); 
				Nacionalidad nac = nacDao.obtenerNacionalidadPorId(resultado.getInt(5));
				med.setNacionalidad(nac);
			med.setFechaNacimiento(resultado.getString(6));
			med.setDireccion(resultado.getString(7));
				LocalidadDao locDao = new LocalidadDao();
				Localidad loc = locDao.obtenerLocalidadPorId(resultado.getInt(8));
				med.setLocalidad(loc);
				ProvinciaDao provDao = new ProvinciaDao(); 
				Provincia prov = provDao.obtenerProvinciaPorId(resultado.getInt(9));
				med.setProvincia(prov);
			med.setCorreoElectronico(resultado.getString(10));
			med.setTelefono(resultado.getString(11));
				EspecialidadDao espeDao = new EspecialidadDao(); 
				Especialidad espe = espeDao.obtenerEspecialidadPorId(resultado.getInt(12));
			med.setEspecialidad(espe);
			med.setHorarios(lstHorarios);
				
				UsuarioDao usuDao = new UsuarioDao(); 
				Usuario usu = usuDao.obtenerUsuarioPorId(resultado.getInt(13));
		   med.setUsuario(usu);
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return med;
	}
	

	
	

}
