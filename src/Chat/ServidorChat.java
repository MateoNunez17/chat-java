package Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorChat {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Iniciando servidor de chat en el puerto 5000...");
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Esperando a que se conecte el cliente...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String mensajeRecibido = entrada.readLine();

                if (mensajeRecibido == null || mensajeRecibido.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha finalizado la conversación.");
                    break;
                }

                System.out.println("Cliente dice: " + mensajeRecibido);

                System.out.print("Servidor: ");
                String mensajeEnviado = sc.nextLine();
                salida.println(mensajeEnviado);

                if (mensajeEnviado.equalsIgnoreCase("salir")) {
                    System.out.println("Has finalizado la conversación.");
                    break;
                }
            }

            sc.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Error en el servidor de chat.");
            e.printStackTrace();
        }
    }
}