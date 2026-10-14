package Ejercicio2;

import java.io.*;
import java.net.*;

public class ClienteRobusto {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 4444;
        try (
            Socket socket = new Socket(host, puerto);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedReader teclado = new BufferedReader(
                new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado. Escribe mensajes (FIN para salir):");
            String texto;
            while ((texto = teclado.readLine()) != null) {
                salida.println(texto);
                if (texto.equalsIgnoreCase("FIN")) break;

                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    System.err.println("El servidor cerró la conexión.");
                    break;
                }
                System.out.println("Respuesta recibida: " + respuesta);
            }
            System.out.println("Cliente desconectado.");
        } catch (UnknownHostException e) {
            System.err.println("[ERROR] Dirección IP/Host no válida.");
        } catch (ConnectException e) {
            System.err.println("[ERROR] Servicio no disponible. Servidor apagado o puerto incorrecto.");
        } catch (SocketException e) {
            System.err.println("[ERROR RED] Error en socket durante transmisión.");
        } catch (IOException e) {
            System.err.println("[ERROR E/S] General: " + e.getMessage());
        }
    }
}