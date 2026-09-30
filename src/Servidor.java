import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado ");
            System.out.println("Esperando cliente...");

            Socket cliente = servidor.accept();

            System.out.println("·Cliente conectado correctamente");

           BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );
            String mensaje = entrada.readLine();

            System.out.println("Mensaje recibido " +  mensaje);

            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            salida.println("MATEO FRANCHESCO");

            cliente.close();
            servidor.close();


        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}