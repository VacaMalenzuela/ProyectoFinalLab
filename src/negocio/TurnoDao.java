package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Especialidad;
import dominio.EstadoTurno;
import dominio.Localidad;
import dominio.Medico;
import dominio.Nacionalidad;
import dominio.Paciente;
import dominio.Provincia;
import dominio.Turno;
import dominio.Usuario;


public class TurnoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "clinicamedica";
	
	public int agregarTurno(Turno turno) { 
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
			String query2 = "Insert into turnos (dniMedico, Fecha, Hora, dniPaciente, IdEstado, Observaciones) values ('"+turno.getMedico().getDni()+"','"+ turno.getFecha()+"','"+ turno.getHora()+"','"+ turno.getPaciente().getDni()+"','"+turno.getEstado().getId()+"', '');";
	
			filas=st.executeUpdate(query2);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public EstadoTurno obtenerEstadoDefault () { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EstadoTurno estado = new EstadoTurno();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("select Id, Estado from EstadosTurnos where estado = 'Libre';");
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			estado.setId(resultado.getInt(1));
			estado.setEstado(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return estado;
		
	}
	public ArrayList<EstadoTurno> obtenerEstadosTurnos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<EstadoTurno> lista = new ArrayList<EstadoTurno>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select Id, Estado from EstadosTurnos");
			
			while(rs.next()){
				
				EstadoTurno estTurn = new EstadoTurno();
				estTurn.setId(rs.getInt("Id"));
				estTurn.setEstado(rs.getString("Estado"));
				
				lista.add(estTurn);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	public EstadoTurno obtenerEstadoPorId (String id) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EstadoTurno estado = new EstadoTurno();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("select Id, Estado from EstadosTurnos where Id = "+id+" ;"); 
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			estado.setId(resultado.getInt(1));
			estado.setEstado(resultado.getString(2));
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return estado;
		
	}
	
	public Turno obtenerTurnoPorId (String id) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Turno tur = new Turno();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado FROM TURNOS where Id = "+id+" ;"); 
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			tur.setId(resultado.getInt(1));
			MedicoDao medNeg = new MedicoDao(); 
			Medico med = medNeg.obtenerMedicoPorDni(resultado.getString(2));
			tur.setMedico(med);
			tur.setFecha(resultado.getString(3));
			tur.setHora(resultado.getString(4));
			
			pacienteDao pacDao = new pacienteDao(); 
			Paciente pac = pacDao.obtenerPacientePorDni(resultado.getString(5));
			tur.setPaciente(pac);
			EstadoTurno est = this.obtenerEstadoPorId(resultado.getString(6));
			tur.setEstado(est);
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return tur;
		
	}
	
	public ArrayList<Turno> obtenerTurnos(int filtro) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Turno> lista = new ArrayList<Turno>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = conn.createStatement();
			ResultSet rs;
			if (filtro == 0) { 
				 rs = st.executeQuery("SELECT Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado, Observaciones FROM Turnos;");
			} else { 
				 rs = st.executeQuery("SELECT Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado, Observaciones FROM Turnos WHERE IdEstado= "+filtro+";");
			}
			
			
			while(rs.next()){
				Turno tur = new Turno(); 
				tur.setId(rs.getInt("Id"));
				tur.setFecha(rs.getString("Fecha"));
				tur.setHora(rs.getString("Hora"));
				tur.setObservacion(rs.getString("Observaciones"));
				
				MedicoDao medNegocio = new MedicoDao(); 
				Medico med = medNegocio.obtenerMedicoPorDni(rs.getString("dniMedico"));
				tur.setMedico(med);
				
				pacienteDao pacNegocio = new pacienteDao(); 
				Paciente pac = pacNegocio.obtenerPacientePorDni(rs.getString("dniPaciente"));
				tur.setPaciente(pac);
				
			EstadoTurno estadoTurno = this.obtenerEstadoPorId(rs.getString("IdEstado"));
			tur.setEstado(estadoTurno);
				lista.add(tur);
			}
			conn.close();
			
		}
		
		catch (Exception e) { 
			e.printStackTrace();
		}
		return lista;	
	}
	
	
	public ArrayList<Turno> ObtenerTurnosPorMedico (Medico med,int filtro) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Turno> lista = new ArrayList<Turno>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = conn.createStatement();
			ResultSet rs;
			if (filtro == 0) {
				rs = st.executeQuery("Select Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado, Observaciones from Turnos WHERE dniMedico = "+med.getDni()+" ;");
			} else {
				rs = st.executeQuery("Select Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado, Observaciones from Turnos WHERE dniMedico = "+med.getDni()+" AND IdEstado = "+filtro+"  ;");
			}
			 
			
			while(rs.next()){
				
				Turno tur = new Turno(); 
				tur.setId(rs.getInt("Id"));
				tur.setFecha(rs.getString("Fecha"));
				tur.setHora(rs.getString("Hora"));
				tur.setObservacion(rs.getString("Observaciones"));
				tur.setMedico(med);
				
				pacienteDao pacNegocio = new pacienteDao(); 
				Paciente pac = pacNegocio.obtenerPacientePorDni(rs.getString("dniPaciente"));
				tur.setPaciente(pac);
				
			EstadoTurno estadoTurno = this.obtenerEstadoPorId(rs.getString("IdEstado"));
			tur.setEstado(estadoTurno);
				lista.add(tur);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	
	public int ActualizarTurno (Turno tur) { 
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
			String query = "UPDATE Turnos SET IdEstado = " + tur.getEstado().getId()+" , Observaciones = '"+tur.getObservacion()+"' where Id = "+tur.getId()+";";
			filas=st.executeUpdate(query);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
		
	}
	
	public Turno ObtenerTurnosPorFechaYhora (String Dni, String Fecha, int Hora) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Turno tur = new Turno();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("SELECT Id, dniMedico, Fecha, Hora, dniPaciente, IdEstado FROM TURNOS where dniMedico = '"+Dni+"' AND Fecha = '"+Fecha+"' AND Hora = "+Hora+" ;"); 
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			tur.setId(resultado.getInt(1));
			MedicoDao medNeg = new MedicoDao(); 
			Medico med = medNeg.obtenerMedicoPorDni(resultado.getString(2));
			tur.setMedico(med);
			tur.setFecha(resultado.getString(3));
			tur.setHora(resultado.getString(4));
			
			pacienteDao pacDao = new pacienteDao(); 
			Paciente pac = pacDao.obtenerPacientePorDni(resultado.getString(5));
			tur.setPaciente(pac);
			EstadoTurno est = this.obtenerEstadoPorId(resultado.getString(6));
			tur.setEstado(est);
		    
		    con.close();
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
		return tur;
	}

}
