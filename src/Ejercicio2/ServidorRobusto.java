package Ejercicio2;

import java.io.*;
import java.net.*;

public class ServidorRobusto {
    public static void main(String[] args) {
        int puerto = 4444;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);
            while (true) {
                try (Socket cliente = servidor.accept()) {
                    System.out.println("Cliente conectado: " + cliente.getInetAddress());
                    cliente.setSoTimeout(5000); 
                    
                    BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(
                        cliente.getOutputStream(), true); 
                    
                    String linea;
                    while ((linea = entrada.readLine()) != null) {
                        if (linea.equalsIgnoreCase("FIN")) break;
                        salida.println("ECO: " + linea);
                    }
                    System.out.println("Cliente desconectado limpiamente.");
                } catch (SocketTimeoutException e) {
                    System.err.println("[TIMEOUT] Cliente inactivo en 5s.");
                } catch (SocketException e) {
                    System.err.println("[DESCONEXIÓN] Cliente se desconectó de golpe.");
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR CRÍTICO] Servidor: " + e.getMessage());
        }
    }
}