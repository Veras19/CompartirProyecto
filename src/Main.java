
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sn = new Scanner (System.in);
		Scanner sc = new Scanner (System.in);
		BDController conexion = new BDController();
		int opc=0;
		
		do {
			System.out.println("1. EJERCICIO 1");
			System.out.println("2. EJERCICIO 2");
			System.out.println("3. EJERCICIO 3");
			System.out.println("4. EJERCICIO 4");
			System.out.println("5. EJERCICIO 5");
			
			System.out.println("----------------------------------------------------------------");	
			System.out.println("INTRODUCE LA OPCION DEL MENU");
			opc = sn.nextInt();
		
			switch(opc) {
			case 1:
				String equipo1 = "";
				String equipo2 = "";
				String temporada = "";
				System.out.println("Nombre equipo 1");
				equipo1=sc.nextLine();
				System.out.println("Nombre equipo 2");
				equipo2=sc.nextLine();
				System.out.println("Temporada");
				temporada=sc.nextLine();
				if(conexion.existeEquipo(equipo1)==false) {
					System.out.println("El equipo no existe");
				}
				if(conexion.existeEquipo(equipo2)==false) {
					System.out.println("El equipo no existe");
				}
				if(conexion.existeTemporada(temporada)==false) {
					System.out.println("La temporada no existe");
				}
				if(conexion.existeEquipo(equipo1)==true && conexion.existeEquipo(equipo2)==true && conexion.existeTemporada(temporada)==true) {
					ArrayList<Partido> partidos = conexion.enfrentamientosEquipo(equipo1, equipo2, temporada);
					for(Partido pt: partidos) {
						System.out.println("Temporada " + pt.getTemporada());
						for(Partido p: partidos) {
							System.out.println(p.getEquipo_local() + " " + p.getPuntos_local()+ ":" + p.getPuntos_visitante() + " " + p.getEquipo_visitante());
						}
					}
				}
				break;
			case 2:
				ArrayList<Equipo> equipos = conexion.EQUIPOS();
				String ruta = "East";
				String ruta2 = "West";
				File fichero = new File(ruta);
				File fichero2 = new File(ruta2);
				if(!fichero.exists()) {
					try {
						fichero.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				String linea="";
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false));
					for(Equipo e: equipos) {
						if(e.getCiudad().equalsIgnoreCase(ruta)) {
						linea=e.getNombre() + " " + e.getCiudad() + " " + e.getConferencia() + " " + e.getDivision();
						bw.write(linea);
						bw.newLine();
					}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String linea2="";
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(ruta2, false));
					for(Equipo e: equipos) {
						if(e.getCiudad().equalsIgnoreCase(ruta2)) {
						linea2=e.getNombre() + " " + e.getCiudad() + " " + e.getConferencia() + " " + e.getDivision();
						bw.write(linea2);
						bw.newLine();
					}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				Jugador jugador = new Jugador();
				System.out.println("Introduce el codigo del jugador");
				int cod=sn.nextInt();
				int temporadas=conexion.temporadasJugador(jugador);
				double result= conexion.eficiencia(jugador)/temporadas;
				System.out.println(result);
				
				break;
			case 4:
				break;
			case 5:
				ArrayList<Jugador> jugadores = conexion.JugadoresCaracteristicas();
				ArrayList<String> vocales = new ArrayList<String>();
				vocales.add("a");
				vocales.add("A");
				vocales.add("e");
				vocales.add("E");
				vocales.add("i");
				vocales.add("I");
				vocales.add("o");
				vocales.add("O");
				vocales.add("u");
				vocales.add("U");
				for(String v: vocales) {
					for(Jugador j: jugadores) {
						if(j.getNombre().substring(5, 6)==v && Integer.parseInt(j.getAltura().substring(0, 1))>7 && Integer.parseInt(j.getAltura().substring(2, 3))>0) {
							j.toString();
						}
					}
					
					
				}
				break;
			
				}
			}while(opc!=6);
		
	}

}
