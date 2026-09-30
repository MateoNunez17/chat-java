package Calculadora;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculadora {

    public static void main(String[] args) {

        try {

            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado ");
            System.out.println("Esperando cliente...");

            Socket cliente = servidor.accept();

            System.out.println("Cliente conectado correctamente");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );

            String num1 = entrada.readLine();
            String num2 = entrada.readLine();

            System.out.println("Mensaje recibido: " + num1);
            System.out.println("Mensaje recibido: " + num2);


            int n1 = Integer.parseInt(num1);
            int n2 = Integer.parseInt(num2);

            int resultado = n1 + n2;


            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(),
                    true
            );
            salida.println(resultado);

            cliente.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}