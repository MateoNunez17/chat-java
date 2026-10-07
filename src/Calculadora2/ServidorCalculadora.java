package Calculadora2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculadora {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando servidor en el puerto 5000...");

            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Esperando conexión...");
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            while (true) {
                String numero1Str = entrada.readLine();

                if (numero1Str == null || numero1Str.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha finalizado la sesión.");
                    break;
                }

                String numero2Str = entrada.readLine();
                String operacion = entrada.readLine();

                double num1 = Double.parseDouble(numero1Str);
                double num2 = Double.parseDouble(numero2Str);

                double resultado = 0;
                String mensaje;

                switch (operacion) {
                    case "+":
                        resultado = num1 + num2;
                        mensaje = "Resultado: " + resultado;
                        break;

                    case "-":
                        resultado = num1 - num2;
                        mensaje = "Resultado: " + resultado;
                        break;

                    case "*":
                        resultado = num1 * num2;
                        mensaje = "Resultado: " + resultado;
                        break;

                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                            mensaje = "Resultado: " + resultado;
                        } else {
                            mensaje = "Error: No se puede dividir entre 0";
                        }
                        break;

                    default:
                        mensaje = "Operación no válida";
                }

                System.out.println("Operación realizada: " + num1 + " " + operacion + " " + num2);

                salida.println(mensaje);
            }

            socket.close();
            serverSocket.close();

            System.out.println("Servidor cerrado.");

        } catch (Exception e) {
            System.out.println("Error en el servidor");
            e.printStackTrace();
        }
    }
}