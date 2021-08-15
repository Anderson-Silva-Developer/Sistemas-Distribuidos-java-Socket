package move;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import player.Player;

public class MovePlayer {
    private TextField controls;
    private ImageView avatar;
    private  Player player;
    private Circle sensor=new Circle();

    public MovePlayer(TextField controls, Player player) {
        this.player=player;
        this.sensor=player.getSensor();
        this.controls = controls;
        this.avatar = player.getAvatar();

    }

    public  void move(){
        controls.onKeyPressedProperty().setValue(keyEvent -> {

                    if(keyEvent.getCode()== KeyCode.UP && avatar.getLayoutY()>0.0){
                        avatar.setLayoutY(avatar.getLayoutY()-50.00);
                        UpdateSensor();

                    }
                    if(keyEvent.getCode()==KeyCode.DOWN  && avatar.getLayoutY()<500.0){
                        avatar.setLayoutY(avatar.getLayoutY()+50.00);
                        UpdateSensor();
                    }
                    if(keyEvent.getCode()==KeyCode.RIGHT && avatar.getLayoutX()<650.0){
                        avatar.setLayoutX(avatar.getLayoutX()+50.0);
                        UpdateSensor();

                    }
                    if(keyEvent.getCode()==KeyCode.LEFT && avatar.getLayoutX()>25.0){
                        avatar.setLayoutX(avatar.getLayoutX()-50.0);
                        UpdateSensor();
                    }

                }

        );

    }

    private void UpdateScore(int score) {
        player.setScore(player.getScore()+score);
        player.getScoreAtual().setText(""+player.getScore());
    }

    public void UpdateSensor(){
        sensor.setCenterX(this.avatar.getLayoutX()+25);
        sensor.setCenterY(this.avatar.getLayoutY()+30);
        if(avatar.getLayoutX()==500){
            sensor.setFill(Color.GREEN);
            UpdateScore(1);
        }else { sensor.setFill(Color.TRANSPARENT);}

    }
}
