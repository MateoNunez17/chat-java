package Calculadora2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteCalculadora {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Conectando al servidor...");
            Socket socket = new Socket("localhost", 5000);

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            while (true) {
                System.out.print("Introduce el primer número (o 'salir' para terminar): ");
                String numero1 = sc.nextLine();

                if (numero1.equalsIgnoreCase("salir")) {
                    salida.println("salir");
                    break;
                }

                System.out.print("Introduce el segundo número: ");
                String numero2 = sc.nextLine();

                System.out.print("Operación (+, -, *, /): ");
                String operacion = sc.nextLine();

                salida.println(numero1);
                salida.println(numero2);
                salida.println(operacion);

                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            socket.close();
            sc.close();
            System.out.println("Cliente cerrado.");

        } catch (Exception e) {
            System.out.println("No se pudo conectar con el servidor");
            e.printStackTrace();
        }
    }
}