package adivinar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAdivina {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Conectando al servidor...");
            Socket socket = new Socket("localhost", 5000);
            System.out.println("¡Conectado! Juego iniciado. Intenta adivinar el número (1 a 20).");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            while (true) {
                System.out.print("Introduce tu número: ");
                String intento = sc.nextLine();

                salida.println(intento);

                String pista = entrada.readLine();

                if (pista == null) {
                    System.out.println("Conexión perdida con el servidor.");
                    break;
                }

                System.out.println("Pista del servidor: " + pista);

                if (pista.equals("CORRECTO")) {
                    System.out.println("Has ganado!");
                    break; 
                }
            }

            sc.close();
            socket.close();
            System.out.println("Cliente finalizado.");

        } catch (Exception e) {
            System.out.println("Error en el cliente.");
            e.printStackTrace();
        }
    }
}