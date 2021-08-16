package comunication;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import player.Player;
import org.json.JSONObject;

public class Client {
    static Socket socket;
    static PrintStream saida;

    public Client() {
        try {
            int porta = 9999;
            String host = "localhost";
            this.socket = new Socket(host,porta);
            saida = new PrintStream(socket.getOutputStream());

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void init() throws IOException {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        escutar();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        public void escutar() throws IOException {
            Scanner entrada = new Scanner(this.socket.getInputStream());
            while(entrada.hasNextLine()){
                String mensagem = entrada.nextLine();
                System.out.println("Nova mensagem: "+mensagem);
            }
        }

        public void sendStatus( Player player) throws IOException {
            String meuid =player.getName().getText();
            String mensagem=meuid+"#";
            JSONObject objectStatus=new JSONObject();
            objectStatus.put("position",player.getPosition());
            objectStatus.put("score",player.getScore());
            mensagem+=objectStatus;
            saida.println("meuid#"+meuid);
            saida.println(mensagem);
        }

        public static void main(String args[]) throws IOException {
            new Client().init();
        }
    }

