package Principal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Principal {
	
	private static String personaje = "Jugador,8d20,101,6\n";

	public static void main(String[] args) {
			try(Socket s = new Socket(InetAddress.getLocalHost(),7777);
					DataInputStream is = new DataInputStream(new BufferedInputStream(s.getInputStream()));
					DataOutputStream os = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
					Scanner lector = new Scanner(System.in)){
				
				os.write(personaje.getBytes());
				os.flush();
				
				String msgBienvenida;

				msgBienvenida = is.readLine();
				System.out.println(msgBienvenida); // Empieza el juego. Iniciativas:
				msgBienvenida = is.readLine();
				System.out.println(msgBienvenida); // DM:S
				msgBienvenida = is.readLine();
				System.out.println(msgBienvenida); // Jugador:
				msgBienvenida = is.readLine();
				System.out.println(msgBienvenida); // Empieza x

				String mensajeRecibido = "";
				String movimiento = "";

				mensajeRecibido = is.readLine();
				
				while(!mensajeRecibido.startsWith("El juego ha acabado.")) {
					System.out.println(mensajeRecibido); // Tu personaje se halla en
					mensajeRecibido = is.readLine();
					System.out.println(mensajeRecibido); // Las estadisticas de tu pj son
					mensajeRecibido = is.readLine();
					System.out.println(mensajeRecibido); // El pj de tu rival se halla en
					mensajeRecibido = is.readLine();
					System.out.println(mensajeRecibido); // Las estadisticas del pj de tu rival son
					mensajeRecibido = is.readLine();
					System.out.println(mensajeRecibido); // Puedes mover a o saltar turno
					mensajeRecibido = is.readLine();
					System.out.println(mensajeRecibido); // Salto linea gratis
					
					movimiento = lector.nextLine() + "\n";
					os.write(movimiento.getBytes());
					os.flush();

					mensajeRecibido = is.readLine();
				}
				
				System.out.println(mensajeRecibido);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
	}
}
