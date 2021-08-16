package player;

import comunication.Comunication;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import move.MovePlayer;

import java.io.IOException;


public class Player {
    private Label name;
    private double[] position={0,0};
    private int score=5;
    private Circle sensor=new Circle();
    private static Comunication comunication;
    private MovePlayer move;
    private ImageView avatar;
    private Label scoreAtual;

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

    public double[] getPosition() {
        position[0]=getAvatar().getLayoutX();
        position[1]=getAvatar().getLayoutY();
        return position;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public Player() throws IOException {
    }
    public void config(){
        scoreAtual.setText(""+getScore());
        sensor.setCenterX(this.getAvatar().getLayoutX()+25);
        sensor.setCenterY(this.getAvatar().getLayoutY()+30);
        sensor.setRadius(33.0f);
        sensor.setFill(Color.TRANSPARENT);
        sensor.setOpacity(0.3);
        try {
            comunication=new Comunication();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
