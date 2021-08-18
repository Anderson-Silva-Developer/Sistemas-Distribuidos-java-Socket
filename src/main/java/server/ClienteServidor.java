package server;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteServidor {
    String id;
    Socket socket;
    Distance distance=new Distance();

    public void init(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //função que escutar as mensagem
                    listenToEvent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public ClienteServidor(Socket socket){
        this.socket = socket;
        //iniciar thred para escutar eventos de entrada de mensagem
        init();
    }
    //tratar mensagem recebida
    public void Send(String id,String mensagem) throws IOException {
        ClienteServidor cliente = Server.clientesConectados.get(id);
        if(Server.clientesConectados.size()==1){
            //o jogo não está liberado!!
         SendStatus(id,"off#");
        }
        else {
            JSONObject objectStatus = new JSONObject(mensagem);
            Upenemy("Moveenemy#",cliente,objectStatus);//atualizar o movimento para inimigo
            int positionX = (int) objectStatus.get("positionX");
            int positionY = (int) objectStatus.get("positionY");
            int[] positionAtual = {positionX, positionY};
            //calcular distancia da recompensa
            int resultPositive = distance.calDistace(Server.rewardPositive, positionAtual);
            int resultNegative = distance.calDistace(Server.rewardNegative, positionAtual);
            //Atualizar o player de acordo com o seu movimento
            UpdatePlayer(cliente, resultPositive, resultNegative,objectStatus);

            cliente.white("" + mensagem);
        }

//        }
    }
    public void SendStatus(String id,String mensagem) throws IOException {
        ClienteServidor cliente = Server.clientesConectados.get(id);
        cliente.white(""+mensagem);

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
                //adicionar o cliente no array de clientes conectados
                //this.id = representa o id do cliente que vem como o nome do player
                //this = representa adicionar o proprio cliente
                Server.clientesConectados.put(this.id, this);
                continue;
            }
                // mensagem irá vim no seguinte formato -> idDestino:mensagem
                String status[] = texto.split("#");
                Send(status[0], status[1]);//tratar mensagem
//                System.out.println("DE: " + this.id);
//                System.out.println("PARA: " + status[0]);
//                System.out.println("MENSAGEM: " + status[1]);

    }
    }
    //atualizar o status do player
    public  void UpdateStatus(ClienteServidor cliente,String msm ,int reward){
        try {
            JSONObject objectStatus=new JSONObject();
            objectStatus.put("position",msm);
            objectStatus.put("reward",reward);
            cliente.white("status#"+objectStatus);

        }catch (Exception e){
            System.out.println("UpdateStatus");

        }

    }
    public  void UpdateReward(ClienteServidor cliente,int result,String rewardType, JSONObject objectStatus){
       //result é igual a distancia da recompensa
        if(result==0){
            int reward=rewardType.equals("soma")?1:-1;
            UpdateStatus(cliente,"0",reward);//atualizar pontos
            int newScore= (int) objectStatus.get("score");
            newScore+=reward;
            objectStatus.put("score",newScore==0?5:newScore);
            Upenemy("Upenemy#",cliente,objectStatus);//atualizar status para inimigo
            //gerar recompensas depois de encontradas
            if(reward==1){ Server.rewardPositive=Server.r.geraRecompensa();}
            else {Server.rewardNegative=Server.r.geraRecompensa(); }
            //GAMEOVER se o score atual estiver igual a zero enviar mesgade de Gameover para todos
            if(newScore==0){
                try {
                    SendStatus(cliente.id,"GameOver#"); //aviso proprio de gameover
                    Server.clientesConectados.forEach((id_, client_) -> {
                        if (!cliente.id.equals(id_)) {
                            try {
                                SendStatus(id_,"GameOverEnemy#");//aviso para inimigo de gameover
                            } catch (IOException e) {
                                System.out.println("ERROO  " + e.getMessage());
                            }
                        }
                    });

                    //

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            ///



        }

        //result=1,2 enviar mensagem com status da cor do sensor
        if(result==1){
            UpdateStatus(cliente,"1",0);
        }
        if(result==2){
            UpdateStatus(cliente,"2",0);
        }
        if(result!=0 && result!=1 && result!=2){
            UpdateStatus(cliente, "3", 0);
        }


    }
    ///atualização para o inimigo
    public void Upenemy(String idmessage,ClienteServidor client, JSONObject objectStatus) {
       if( Server.clientesConectados.size()>1){
        Server.clientesConectados.forEach((id_, client_) -> {
            if (!client.id.equals(id_)) {
                try {
                    client.SendStatus(id_, idmessage + objectStatus);
                } catch (IOException e) {
                    System.out.println("ERROO  " + e.getMessage());
                }
            }
        });
    }
    }
    public  void UpdatePlayer(ClienteServidor cliente,int resultP,int resultN, JSONObject objectStatus){
        try {
            int proximo=resultP<resultN?resultP:resultN;//buscar a menor distancia das recompensas
            String type=resultP<resultN?"soma":"sub";//definir se soma ou subtrai caso ganhe recompensa
            UpdateReward(cliente,proximo,type,objectStatus);//atualizar recompensas ou game over

        }catch (Exception e){
            System.out.println("Erro  "+e.getMessage());
        }


    }

}
