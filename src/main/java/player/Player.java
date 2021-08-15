package player;

import comunication.Cominication;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import move.MovePlayer;

public class Player {
    private Label name;
    private int[] position={0,0};
    private int score=5;
    private Circle sensor=new Circle();
    private Cominication comunication;
    private MovePlayer move;
    private ImageView avatar;
    private Label scoreAtual;

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

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
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

    public Player() {
    }
    public void config(){
        scoreAtual.setText(""+getScore());
        sensor.setCenterX(this.getAvatar().getLayoutX()+25);
        sensor.setCenterY(this.getAvatar().getLayoutY()+30);
        sensor.setRadius(33.0f);
        sensor.setFill(Color.TRANSPARENT);
        sensor.setOpacity(0.3);
    }

}
