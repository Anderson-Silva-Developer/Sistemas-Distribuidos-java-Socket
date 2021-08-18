package comunication;

import player.Player;

import java.io.IOException;

public class  Comunication {
   //instaciar cliente socket
    static Client cliente=new Client();

    public Comunication() throws IOException {
        //chamar thred do cliente que escuta a entrada de mensagem usando  socket
        cliente.init();//
    }

    public void  send( Player player) throws IOException {
        try {
            cliente.sendtoServ(player);
        }catch (Exception e){
//            System.out.println("Erro send");

        }


    }
}
