package player;

import comunication.Comunication;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import move.MovePlayer;

import java.io.IOException;

public class Player {
    private Label name;//nome do player
    private Label nameEnemy;//nome do player inimigo
    private Label vitoria;//label vitoria do player
    private Label vitoriaEnemy;//label vitoria do player inimigo
    private Circle sensor=new Circle();//indicador de aproximação da recompensa
    private static Comunication comunication;//classe para comunicação com o servidor (usar socket)
    private MovePlayer move;//classe para covimento do player
    private ImageView avatar;//avatar do player inimigo
    private ImageView avatarEnemy;//avatar do player inimigo
    private Label scoreAtual;//pontuação do player
    private Label scoreAtualEnemy;//pontuação do player inimigo

    public Label getVitoria() {
        return vitoria;
    }

    public void setVitoria(Label vitoria) {
        this.vitoria = vitoria;
    }

    public Label getVitoriaEnemy() {
        return vitoriaEnemy;
    }

    public void setVitoriaEnemy(Label vitoriaEnemy) {
        this.vitoriaEnemy = vitoriaEnemy;
    }

    public Label getNameEnemy() {
        return nameEnemy;
    }

    public void setNameEnemy(Label nameEnemy) {
        this.nameEnemy = nameEnemy;
    }

    public Label getScoreAtualEnemy() {
        return scoreAtualEnemy;
    }

    public void setScoreAtualEnemy(Label scoreAtualEnemy) {
        this.scoreAtualEnemy = scoreAtualEnemy;
    }

    public Comunication getComunication() {return comunication; }

    public void setComunication(Comunication comunication) {
        this.comunication = comunication;
    }

    public Label getScoreAtual() {
        return scoreAtual;
    }

    public void setScoreAtual(Label scoreAtual) {
        this.scoreAtual = scoreAtual;
    }

    public MovePlayer getMove() {
        return move;
    }

    public void setMove(MovePlayer move) {
        this.move = move;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Circle getSensor() {
        return sensor;
    }

    public void setSensor(Circle sensor) {
        this.sensor = sensor;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public ImageView getAvatarEnemy() {
        return avatarEnemy;
    }

    public void setAvatarEnemy(ImageView avatarEnemy) {
        this.avatarEnemy = avatarEnemy;
    }

    public Player() throws IOException {
    }
    //setar posição do sensor e comunicação com o servidor (usar socket)
    public void config(){
        sensor.setCenterX(this.getAvatar().getLayoutX()+25);
        sensor.setCenterY(this.getAvatar().getLayoutY()+30);
        sensor.setRadius(33.0f);
        sensor.setFill(Color.TRANSPARENT);
        sensor.setOpacity(0.6);
        try {
            comunication=new Comunication();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
