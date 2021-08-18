package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static HashMap<String,ClienteServidor> clientesConectados = new HashMap<>();
    public static int[] rewardPositive=new int[2];
    public static int[] rewardNegative=new int[2];
    public  static  Recompensa r=new Recompensa();

    public void Start() throws IOException {
        rewardPositive= r.geraRecompensa();//gerar recompensas positivas
        rewardNegative= r.geraRecompensa();//gerar recompensas negativas
        int porta = 9999;
        ServerSocket server = new ServerSocket(porta);
        System.out.println("Servidor rodando na porta: "+porta);
        while(true){
            Socket socket = server.accept();//o servidor ficará escutando clientes
            ClienteServidor cliente = new ClienteServidor(socket);// Objeto ClienteServidor cada cliente é uma thred
        }
    }
    public static void main(String[] args) throws IOException {
        new Server().Start();
    }







}
