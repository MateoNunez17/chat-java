package adivinar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando servidor...");
            ServerSocket serverSocket = new ServerSocket(5000);

            int numeroSecreto = (int) (Math.random() * 20) + 1;
            System.out.println("Número secreto generado: " + numeroSecreto); 

            System.out.println("Esperando a que se conecte el cliente...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String mensajeRecibido = entrada.readLine();

                if (mensajeRecibido == null) {
                    System.out.println("El cliente se ha desconectado.");
                    break;
                }

                int propuestaJugador = Integer.parseInt(mensajeRecibido);
                System.out.println("El cliente intentó con: " + propuestaJugador);

                if (propuestaJugador < numeroSecreto) {
                    salida.println("MAYOR");
                } else if (propuestaJugador > numeroSecreto) {
                    salida.println("MENOR");
                } else {
                    salida.println("¡CORRECTO!");
                    System.out.println("¡El cliente adivinó el número!");
                    break;
                }
            }

            socket.close();
            serverSocket.close();
            System.out.println("Servidor finalizado.");

        } catch (Exception e) {
            System.out.println("Error en el servidor.");
            e.printStackTrace();
        }
    }
}