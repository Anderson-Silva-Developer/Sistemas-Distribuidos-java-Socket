package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import move.MovePlayer;
import player.Player;

public class GameController {
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
    private TextField controls;
    @FXML
    private Pane login;
    @FXML
    private TextField name;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    Player player=new Player();
    @FXML
    public  void  initialize(){
         iniGame();

    }
    @FXML
    public void play(){
        play01.setOpacity(1);
        play02.setOpacity(1);
        login.setVisible(false);
        arena.setVisible(true);
        avatar.setLayoutX(0);
        avatar.setLayoutY(0);
        if(name.getText().equals("")){
            name.setText("player");
        }
        player.setAvatar(avatar);
        player.setName(opcaoAvatar==1?player1Name:player2Name);
        player.getName().setText(name.getText());
        player.setScoreAtual( opcaoAvatar==1?scoreplay01:scoreplay02);
        player.config();
        player.setMove(new MovePlayer(controls, player));
        player.getMove().move();
        arena.getChildren().add(player.getSensor());
    }
    @FXML
    public  void iniGame(){
        try {
            btn1.onMouseClickedProperty().setValue(mouseEvent -> {
                avatar.setImage(play01.getImage());
                avatar.setLayoutX(0);
                avatar.setLayoutY(0);
                opcaoAvatar=1;
            });
            btn2.onMouseClickedProperty().setValue(mouseEvent -> {
                avatar.setImage(play02.getImage());
                avatar.setLayoutX(0);
                avatar.setLayoutY(0);
                opcaoAvatar=2;

            });


        }catch (Exception e){
            System.out.println("Erro no login!!"+e.getMessage());
        }



    }

}