package comunication;

import player.Player;

import java.io.IOException;

public class  Comunication {

    static Client cliente=new Client();

    public Comunication() throws IOException {
        cliente.init();//
    }

    public void  send( Player player) throws IOException {
    cliente.sendStatus(player);

    }
}
