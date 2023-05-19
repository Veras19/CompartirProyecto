
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {
	private Connection conexion;

	public BDController() {
		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

		
	
	public boolean existeEquipo(String nomEquipo) {
		boolean existe=false;
		String sql = "SELECT * FROM equipos WHERE Nombre = '"+ nomEquipo +"'";
		
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			if(rs.next()) {
				existe=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeEquipo" + e);
		}
		
		
		
		return existe;
	}
	
	public boolean existeTemporada(String nomTemporada) {
		boolean existe=false;
		String sql = "SELECT * FROM partidos WHERE temporada = '"+ nomTemporada +"'";
		
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			if(rs.next()) {
				existe=true;
			}
			
			miStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeTemporada" + e);
		}
		
		
		
		return existe;
	}
	
	
	public ArrayList<Partido> enfrentamientosEquipo(String equipo1, String equipo2, String temporada){
		ArrayList<Partido> partidos =new ArrayList<Partido>();
		String sql="SELECT*FROM partidos WHERE ((equipo_local ='" +equipo1+"' AND equipo_visitante ='" + equipo2 +"') OR (equipo_local = '" +equipo2+ "' AND equipo_visitante = '" +equipo1+ "')) AND temporada = '" +temporada+"'";                   
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			while(rs.next()) {
				Partido partido = new Partido(rs.getInt("codigo"), rs.getString("equipo_local"), rs.getString("equipo_visitante"), rs.getInt("puntos_local"), rs.getInt("puntos_visitante"), rs.getString("temporada"));
				partidos.add(partido);
			}
			miStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en enfrentamientosEquipo" + e);
		}
		
		
		
		
		return partidos;
	}
	
	public ArrayList<Equipo> EQUIPOS(){
		ArrayList<Equipo> equipos =new ArrayList<Equipo>();
		String sql="SELECT*FROM equipos";                 
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			while(rs.next()) {
				Equipo equipo = new Equipo(rs.getString("Nombre"), rs.getString("Ciudad"), rs.getString("Conferencia"), rs.getString("Division"));
				equipos.add(equipo);
			}
			miStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en EQUIPOS" + e);
		}
		return equipos;
	}
	
	public ArrayList<Jugador> JugadoresCaracteristicas(){
		ArrayList<Jugador> jugadores =new ArrayList<Jugador>();
		String sql="SELECT*FROM jugadores";                 
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			while(rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"),rs.getString("Nombre"), rs.getString("Procedencia"), rs.getString("Altura"), rs.getInt("Peso"), rs.getString("Posicion"), rs.getString("Nombre_equipo"));
				jugadores.add(jugador);
			}
			miStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en JugadoresCaracteristicas" + e);
		}
		return jugadores;
	}
	
	
	public ArrayList<Partido> partidos(){
		ArrayList<Partido> partidos =new ArrayList<Partido>();
		String sql="SELECT*FROM partidos";                   
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			while(rs.next()) {
				Partido partido = new Partido(rs.getInt("codigo"), rs.getString("equipo_local"), rs.getString("equipo_visitante"), rs.getInt("puntos_local"), rs.getInt("puntos_visitante"), rs.getString("temporada"));
				partidos.add(partido);
			}
			miStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en partidos" + e);
		}
		
		
		
		
		return partidos;
	}
	
	public int temporadasJugador(Jugador jugador) {
		int temporadas=0;
		String sql = "SELECT temporada FROM estadisticas WHERE jugador = '"+jugador.getCodigo()+ "'";
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			if(rs.next()) {
				temporadas++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en temporadasJugador" + e);
		}
		return temporadas;
	}
	
	public double eficiencia (Jugador jugador) {
		double eficiencia = 0;
		String sql = "SELECT * FROM estadisticas WHERE jugador = '"+jugador.getCodigo()+ "'";
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			if(rs.next()) {
				eficiencia = rs.getInt(("Puntos_por_partido") + rs.getInt("Asistencias_por_partido") + rs.getInt("Tapones_por_partido") + rs.getInt("Rebotes_por_partido")/4);               
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en temporadasJugador" + e);
		}
		
		
		
		
		
		return eficiencia=0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

