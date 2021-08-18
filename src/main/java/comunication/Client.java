package comunication;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.example.game.GameController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
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
            System.out.println("Erro onectar cliente/servidor");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ConexãoNegada");
            alert.setHeaderText("Servidor com funções inativas no momento!!");
            alert.show();

        }

    }

    public void init() throws IOException {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        escutar();

                    } catch (Exception e) {
                        System.out.println("Erro init");
                    }
                }
            }).start();

        }
        ///esperar por mensagem do servidor
        public void escutar() throws IOException {
            Scanner entrada = new Scanner(this.socket.getInputStream());

            while(entrada.hasNextLine()){
                String mensagem = entrada.nextLine();
                //atualizar o status do player
                if(mensagem.contains("status#")){
                    //filtrar conteudo
                    String status[] =mensagem.split("#");
                    JSONObject objectStatus=new JSONObject(status[1]);
                    String position = (String) objectStatus.get("position");
                    int reward= (int) objectStatus.get("reward");
                    //
//                     0 -> o player encontrou uma recompensa
//                     1 ->o player está a dois passos da recompensa
//                     2->o player está a um passos da recompensa
//                     3->o player está a mais de dois passos da recompensa
                    if(position.equals("0")){
                        rewardPlayer(reward);
                    }else{
                    if(position.equals("1")){GameController.player.getMove().getSensor().setFill(Color.YELLOW);};
                    if(position.equals("2")){GameController.player.getMove().getSensor().setFill(Color.WHEAT);};
                    if(position.equals("3")){GameController.player.getMove().getSensor().setFill(Color.TRANSPARENT);};
                    }

                }
                //chamar função para informar apenas um player
                if(mensagem.contains("off#")){
                   OffPlayer();
                }
                //Update do inimigo
                if(mensagem.contains("Upenemy#")){
                    String Upenemy[] =mensagem.split("#");
                    JSONObject objectUpenemy=new JSONObject(Upenemy[1]);
                    int newScore= (int) objectUpenemy.get("score");
                    UpdatePlayerenemy(newScore);

                }
                //Update movimento do inimigo
                if(mensagem.contains("Moveenemy#")){
                    String Upenemy[] =mensagem.split("#");
                    JSONObject objectUpenemy=new JSONObject(Upenemy[1]);
                    int positionX = (int) objectUpenemy.get("positionX");
                    int positionY = (int) objectUpenemy.get("positionY");
                    String colorAvatar= (String) objectUpenemy.get("color");
                    String nameEnemy= (String) objectUpenemy.get("name");
                    UpdatePlayerenemyMove(positionX,positionY,colorAvatar,nameEnemy);

                }
                //Game over você perdeu
                if(mensagem.contains("GameOver#")){
                    GameOver();
                }
                ////Game over do inimigo ele  perdeu
                if(mensagem.contains("GameOverEnemy#")){
                    GameOverEnemy();
                }

            }
        }

        //enviar mensagem com status para o servidor
        public void sendtoServ(Player player) throws IOException {
            String meuid =player.getName().getText();
            String mensagem=meuid+"#";
            JSONObject objectStatus=new JSONObject();
            objectStatus.put("positionX",player.getAvatar().getLayoutX());
            objectStatus.put("positionY",player.getAvatar().getLayoutY());
            objectStatus.put("score",Integer.parseInt(player.getScoreAtual().getText()));
            objectStatus.put("color",player.getAvatar().getId());
            objectStatus.put("name",player.getName().getText());
            mensagem+=objectStatus;
            saida.println("meuid#"+meuid);
            saida.println(mensagem);

    }
    //atualizar score
    public  void rewardPlayer(int reward){
        try {
            Platform.runLater(()-> {
                try {
                    GameController.player.getMove().getSensor().setFill(reward==1?Color.GREEN:Color.RED);
                    int count=(Integer.parseInt(GameController.player.getScoreAtual().getText())+reward);
                    GameController.player.getScoreAtual().setText(""+count);

                } catch (Exception e) {
                    System.out.println("Erro rewardPlayer");
                }
            });

        } catch (Exception e) {
            System.out.println("Erro rewardPlayer");
        }
    }
    //informe de apenas um player no jogo
    public  void OffPlayer(){
        try {
            Platform.runLater(()-> {
                try {
//                    GameController.off=true;
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game");
                    alert.setHeaderText("Esperando segundo player!!");
                    alert.show();

                } catch (Exception e) {
                    System.out.println("Erro OffPlayer");
                }
            });

        } catch (Exception e) {
            System.out.println("offPlayer");
        }
    }
    //atualizar score do inimigo
    public  void UpdatePlayerenemy(int newScore){
        try {
            Platform.runLater(()-> {
                try {
                    GameController.player.getScoreAtualEnemy().setText(""+newScore);

                } catch (Exception e) {
                    System.out.println("Erro  UpdatePlayerenemy");
                }
            });

        } catch (Exception e) {
            System.out.println("UpdatePlayerenemy");
        }
    }
    ///atualizar movimento e posição do inimigo
    public  void UpdatePlayerenemyMove(int X,int Y,String color,String nameEnemy){
        try {
            Platform.runLater(()-> {
                try {
                    GameController.player.getAvatarEnemy().setLayoutX(X);
                    GameController.player.getAvatarEnemy().setLayoutY(Y);
                    GameController.player.getAvatarEnemy().setImage(
                            GameController.player.getAvatar().getId().equals("player02")?
                            GameController.player01.getImage():GameController.player02.getImage()
                    );
                    GameController.player.getAvatarEnemy().setOpacity(1);
                    GameController.player.getNameEnemy().setText(nameEnemy);

                } catch (Exception e) {
                    System.out.println("UpdatePlayerenemyMove");
                }
            });

        } catch (Exception e) {
            System.out.println("UpdatePlayerenemyMove");
        }
    }
    ///Game over seus pontos setados para 5 inimigo venceu
    public void GameOver(){
        try {
            Platform.runLater(()-> {
                try {
                    int vitorias=Integer.parseInt(GameController.player.getVitoriaEnemy().getText());
                    vitorias+=1;
                    GameController.player.getVitoriaEnemy().setText(""+vitorias);
                    GameController.player.getScoreAtual().setText(""+5);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("Você Perdeu!!");
                    alert.show();


                } catch (Exception e) {
                    System.out.println("GameOver");
                }
            });

        } catch (Exception e) {
            System.out.println("GameOver");
        }

    }
    //Game over do inimigo suas vitoria+1
    public void GameOverEnemy(){
        try {
            Platform.runLater(()-> {
                try {
                    int vitorias=Integer.parseInt(GameController.player.getVitoria().getText());
                    vitorias+=1;
                    GameController.player.getVitoria().setText(""+vitorias);



                } catch (Exception e) {
                    System.out.println("GameOverEnemy");
                }
            });

        } catch (Exception e) {
            System.out.println("GameOverEnemy");
        }

    }

        public static void main(String args[]) throws IOException {
            new Client().init();
        }
    }

