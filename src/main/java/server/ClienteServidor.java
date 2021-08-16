package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteServidor {
    String id;
    Socket socket;

    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listenToEvent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public ClienteServidor(Socket socket) {
        this.socket = socket;
        init();
    }

    public void Send(String idDestino, String mensagem) throws IOException {
        // if(Server.clientesConectados.size()>1) {
        ClienteServidor cliente = Server.clientesConectados.get(idDestino);
        cliente.white(mensagem);
        // }
    }

    public void white(String mensagem) throws IOException {
        PrintStream saida = new PrintStream(this.socket.getOutputStream());
        saida.println(mensagem);
    }

    public void listenToEvent() throws IOException {

        Scanner entrada = new Scanner(this.socket.getInputStream());
        while (entrada.hasNextLine()) {
            String texto = entrada.nextLine();
            // id irá vim no seguinte formato -> meuid:'aqui_ira_ficar_o_ID'
            if (texto.contains("meuid#")) {
                this.id = texto.substring("meuid#".length());
                Server.clientesConectados.put(this.id, this);
                continue;
            }
            // mensagem irá vim no seguinte formato -> idDestino:mensagem
            String status[] = texto.split("#");
            Send(status[0], status[1]);
            System.out.println("DE: " + this.id);
            System.out.println("PARA: " + status[0]);
            System.out.println("MENSAGEM: " + status[1]);

        }
    }

}
