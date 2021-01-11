package InterfazYPrincipal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import CapaDatos.DCasilla;
import CapaDatos.DDado;
import CapaDatos.DJugador;
import CapaDatos.DPersonaje;
import CapaNegocio.NJuego;
import CapaNegocio.NJugador;
import CapaNegocio.NPersonaje;
import CapaNegocio.NTablero;

public class Principal {

	public static void main(String[] args) {
		NTablero tablero = NTablero.generarTablero(6, 6);

		Scanner lector = new Scanner(System.in);
		
		try(ServerSocket ss = new ServerSocket(7777);){
			while(true) {
				try(Socket sc = ss.accept();
						DataInputStream is = new DataInputStream(new BufferedInputStream(sc.getInputStream()));
						DataOutputStream os = new DataOutputStream(new BufferedOutputStream(sc.getOutputStream()));){
					
					System.out.println("Conectado.");
					
					tablero.limpiarTablero(); // Quitamos todos los personajes del tablero
					
					// Inicializamos los datos del DM (Dungeon Master)
					DJugador masterDatos = new DJugador("Master");
					DPersonaje bichoDatos = new DPersonaje(new DDado(100,2), 800, 6);
					masterDatos.addPj(bichoDatos);
					
					NJugador master = new NJugador(masterDatos);
					NPersonaje bicho = new NPersonaje(bichoDatos);
					
					
					tablero.ponerPersonaje(bichoDatos, 0, 0);
					
					String pjEntrante;
					
					// Inicializamos los datos del jugador entrante
					pjEntrante = is.readLine();
					String[] param = pjEntrante.split(",");
					String[] datosDado = param[1].split("d");

					DJugador jugadorDatos = new DJugador(param[0]);
					
					DPersonaje bichoJDatos = new DPersonaje(new DDado(Integer.parseInt(datosDado[0]),Integer.parseInt(datosDado[1])), Integer.parseInt(param[2]), Integer.parseInt(param[3]));
					jugadorDatos.addPj(bichoJDatos);
					
					NJugador jugador = new NJugador(jugadorDatos);
					NPersonaje bichoJ = new NPersonaje(bichoJDatos);
					
					tablero.ponerPersonaje(bichoJDatos, 5, 5);
					
					// Describimos al personaje entrante
					System.out.println("Personaje entrante: " + bichoJDatos.toString());
					
					// Empezamos
					NJuego juego = new NJuego(tablero, master, jugador);

					// Mostramos las iniciativas por pantalla. La iniciativa se determina con un dado de 20 caras. El que saque más alto empieza.
					int iniMaster = master.getDatos().getIniciativa();
					int iniJugador = jugador.getDatos().getIniciativa();
					
					System.out.println("Empezando el juego. Iniciativas:");
					System.out.println(master.getDatos().getNombre() + ": " + iniMaster);
					System.out.println(jugador.getDatos().getNombre() + ": " + iniJugador);
					
					String mensajeBienvenida = "Empezando el juego. Iniciativas:\n" + master.getDatos().getNombre()+ ": " + iniMaster + "\n" + jugador.getDatos().getNombre() + ": " + iniJugador + "\n";
					
					if(iniMaster > iniJugador) {
						System.out.println("Empieza el DM");
						mensajeBienvenida += "Empieza el DM\n";
					}
					else {
						System.out.println("Empieza el jugador");
						mensajeBienvenida += "Empieza el jugador\n";
					}
					
					os.write(mensajeBienvenida.getBytes());
					os.flush();
					
					int i = 0;
					String mensaje;
					String movimiento;
					DCasilla casPJ;

					// Bucle principal. Ambas opciones son equivalentes en su función y como tal probablemente puedan compactarse en una sola.
					while(!juego.isAcabado()) {
						// Turno del DM
						if(juego.getTurno() == 0) {
							// Descripcion de la situacion
							casPJ = tablero.getTableroDatos().getCasillaJugador(bichoDatos);
							System.out.println("Tu personaje se halla en " +
									casPJ.toString());
							System.out.println("Las estadísticas de tu personaje son " + bichoDatos.toString());
							System.out.println("El personaje de tu rival se halla en " +
									tablero.getTableroDatos().getCasillaJugador(bichoJDatos).toString());
							System.out.println("Las estadísticas del personaje de tu rival son " + bichoJDatos.toString());
							System.out.print("Puedes mover a ");
							for(DCasilla casilla : tablero.getAdyacentes(casPJ.getPosX(), casPJ.getPosY())) {
								System.out.print(casilla.toString() + "(" + i + "), ");
								i++;
							}
							i=0;
							System.out.println("o pasar turno (p)");
							System.out.println();
							// Leemos movimiento
							movimiento = lector.nextLine();
							// Ejecutamos movimiento o pasamos turno
							if(tryParseInt(movimiento)) {
								juego.hacerUnMovimiento(Integer.parseInt(movimiento));
							}
							else {
								juego.pasarTurno();
							}
						}
						// Turno del jugador
						else {
							casPJ = tablero.getTableroDatos().getCasillaJugador(bichoJDatos);
							mensaje = "";
							mensaje += "Tu personaje se halla en " +
									casPJ.toString() + "\n";
							mensaje += "Las estadísticas de tu personaje son " + bichoJDatos.toString() + "\n";
							mensaje += "El personaje de tu rival se halla en " + tablero.getTableroDatos().getCasillaJugador(bichoDatos).toString() + "\n";
							mensaje += "Las estadísticas del personaje de tu rival son " + bichoDatos.toString() + "\n";
							mensaje += "Puedes mover a ";

							for(DCasilla casilla : tablero.getAdyacentes(casPJ.getPosX(), casPJ.getPosY())) {
								mensaje += casilla.toString() + "(" + i + "), ";
								i++;
							}
							i=0;
							
							mensaje += "o pasar turno (p)\n\n";
							
							os.write(mensaje.getBytes());
							os.flush();
							
							movimiento = is.readLine();

							if(tryParseInt(movimiento)) {
								juego.hacerUnMovimiento(Integer.parseInt(movimiento));
							}
							else {
								juego.pasarTurno();
							}
						}
					}
					
					// Acaba el juego y declaramos ganador.
					mensaje = "El juego ha acabado. Ha ganado " + (juego.getTurno()==0 ? master.getDatos().getNombre() : jugador.getDatos().getNombre()) + ".\n";
					os.write(mensaje.getBytes());
					os.flush();
					System.out.println(mensaje);
				}
				catch(IOException ee) {
					ee.printStackTrace();
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		lector.close();
	}
	
	private static boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
}
