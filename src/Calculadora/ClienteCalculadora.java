package Calculadora;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteCalculadora {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conectado al servidor");


            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            salida.println("5");
            salida.println("3");


            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            String respuesta = entrada.readLine();
            System.out.println("El resultado es: " + respuesta);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}