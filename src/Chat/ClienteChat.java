package Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Conectando al servidor...");
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conectado.");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            while (true) {
                System.out.print("Cliente: ");
                String mensajeEnviado = sc.nextLine();
                salida.println(mensajeEnviado);

                if (mensajeEnviado.equalsIgnoreCase("salir")) {
                    System.out.println("Has finalizado la conversación.");
                    break;
                }

                System.out.println("Esperando respuesta del servidor...");
                String mensajeRecibido = entrada.readLine();

                if (mensajeRecibido == null || mensajeRecibido.equalsIgnoreCase("salir")) {
                    System.out.println("El servidor ha finalizado la conversación.");
                    break;
                }

                System.out.println("Servidor dice: " + mensajeRecibido);
            }

            sc.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error en el cliente de chat.");
            e.printStackTrace();
        }
    }
}