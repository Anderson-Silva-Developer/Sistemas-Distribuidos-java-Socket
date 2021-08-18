package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import move.MovePlayer;
import player.Player;

import java.io.IOException;
import java.util.Random;

public class GameController {
    //elementos da janela javafx
    @FXML
    private Pane gridGeral;
    @FXML
    private Label vitoria01;
    @FXML
    private Label vitoria02;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    int opcaoAvatar=1;
    @FXML
    private Label scoreplay01;
    @FXML
    private Label scoreplay02;
    @FXML
    private Pane arena;
    @FXML
    private ImageView play01;
    @FXML
    private ImageView play02;
    @FXML
    private ImageView avatar;
    @FXML
    private ImageView avatarenemy;
    @FXML
    private TextField controls;
    @FXML
    private Pane login;
    @FXML
    private TextField name;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    ///
    Random r = new Random();
    //opções de player para o jogo
    public  static ImageView player01;
    public  static ImageView player02;
    //
    public static Player player; //player principal

    public GameController() throws IOException {
    }

    @FXML
    public  void  initialize(){
        try {
            player=new Player();
            iniGame();
            player01=this.play01;
            player02=this.play02;
        } catch (Exception e) {
            System.out.println("Erro initialize "+e.getMessage());
        }


    }
    @FXML
    public void play() throws IOException {
        //play para inicio do jogo
        //tonar elementos da janela visiveis
        gridGeral.setVisible(true);
        play01.setOpacity(1);
        play02.setOpacity(1);
        login.setVisible(false);
        arena.setVisible(true);

        //
        //gerar posição do avatar do  player
        avatar.setLayoutX(r.nextInt(14)*50);
        avatar.setLayoutY(r.nextInt(11)*50);
        //

        if(name.getText().equals("")){
            name.setText("player");
        }

        //setar valores para o player

        player.setAvatar(avatar);
        player.setName(opcaoAvatar==1?player1Name:player2Name);
        player.setNameEnemy(opcaoAvatar==2?player1Name:player2Name);
        player.getName().setText(name.getText()+r.nextInt(10)*10);
        player.setScoreAtual( opcaoAvatar==1?scoreplay01:scoreplay02);
        player.setScoreAtualEnemy(opcaoAvatar==2?scoreplay01:scoreplay02);
        player.setVitoria(opcaoAvatar==1?vitoria01:vitoria02);
        player.setVitoriaEnemy(opcaoAvatar==2?vitoria01:vitoria02);
        player.setAvatarEnemy(avatarenemy);
        player.config();
        player.setMove(new MovePlayer(controls, player));
        player.getMove().move();
        arena.getChildren().add(player.getSensor());
        player.getComunication().send(player);

    }
    @FXML
    public  void iniGame(){

        //selecionar imagem do avatar
        try {
            btn1.onMouseClickedProperty().setValue(mouseEvent -> {
                avatar.setImage(play01.getImage());
                avatar.setLayoutX(0);
                avatar.setLayoutY(0);
                avatar.setId("player01");
                opcaoAvatar=1;
            });
            btn2.onMouseClickedProperty().setValue(mouseEvent -> {
                avatar.setImage(play02.getImage());
                avatar.setLayoutX(0);
                avatar.setLayoutY(0);
                avatar.setId("player02");
                opcaoAvatar=2;

            });


        }catch (Exception e){
            System.out.println("Erro no login!!"+e.getMessage());
        }



    }

}