package Ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = null;

        try{
            System.out.println("Conectando con el servidor..");

            socket = new Socket("localhost", 9999);
            System.out.println("conectado");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                System.out.println("cliente:");
                String mensajeEnviar = sc.nextLine();
                salida.println(mensajeEnviar);

                if (mensajeEnviar.equalsIgnoreCase("salir")){
                    System.out.println("servidor se desconecto");
                    break;
                }

                String respuesta = entrada.readLine();
                if(respuesta == null){
                    System.out.println("El servidor se ha desconectado.");
                    break;
                }
                System.out.println("Servidor: " + respuesta);

            }

        } catch (ConnectException e) {
            System.err.println("No se pudo conectar al servidor .");
            e.printStackTrace();
        } catch (SocketException e) {
            System.err.println("Se perdió la comunicación con el servidor.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado:");
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
