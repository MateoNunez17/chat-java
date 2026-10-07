package Ejercicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Servidor {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        ServerSocket servidor = null;
        Socket cliente = null;

        try {
            servidor = new ServerSocket(5000);
            System.out.println("servidor inciado, espernaod cliente");

            cliente = servidor.accept();
            System.out.println("cliente conectado" + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(),true);


            while (true){

                String mensajeRecibido = entrada.readLine();

                if (mensajeRecibido == null || mensajeRecibido.equalsIgnoreCase("salir")){
                    System.out.println("cliente termino la comunicacion");
                    break;
                }

                System.out.println("cliente:" + mensajeRecibido);

                System.out.println("Servidor: ");
                String respuesta = sc.nextLine();
                salida.println(respuesta);

                if(respuesta.equalsIgnoreCase("salir")){
                    System.out.println("cerrndo el servidor");
                    break;
                }

            }

        } catch (SocketException e){
            System.err.println("Error de Socket: El cliente cerró la conexión abruptamente.");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error en el servidor:");
            e.printStackTrace();
        }finally {
            try {
                if (cliente != null && !cliente.isClosed()) cliente.close();
                if (servidor != null && !servidor.isClosed()) servidor.close();
                sc.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
