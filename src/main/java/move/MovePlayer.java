package move;

import com.example.game.GameController;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import player.Player;

import java.io.IOException;


public class MovePlayer {
    private TextField controls;
    private ImageView avatar;
    private static Player player;
    private Circle sensor=new Circle();

    public Circle getSensor() {
        return sensor;
    }

    public void setSensor(Circle sensor) {
        this.sensor = sensor;
    }

    public MovePlayer(TextField controls, Player player) {
        this.player=player;
        this.sensor=player.getSensor();
        this.controls = controls;
        this.avatar = player.getAvatar();

    }

    public  void move() {

        try {
            controls.onKeyPressedProperty().setValue(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP && avatar.getLayoutY() > 0.0) {
                    avatar.setLayoutY(avatar.getLayoutY() - 50.00);
                    UpdateSensor();

                }
                if (keyEvent.getCode() == KeyCode.DOWN && avatar.getLayoutY() < 500.0) {
                    avatar.setLayoutY(avatar.getLayoutY() + 50.00);
                    UpdateSensor();
                }
                if (keyEvent.getCode() == KeyCode.RIGHT && avatar.getLayoutX() < 650.0) {
                    avatar.setLayoutX(avatar.getLayoutX() + 50.0);
                    UpdateSensor();

                }
                if (keyEvent.getCode() == KeyCode.LEFT && avatar.getLayoutX() > 25.0) {
                    avatar.setLayoutX(avatar.getLayoutX() - 50.0);
                    UpdateSensor();
                }


                    }

            );
        }catch (Exception e){
            System.out.println("Erro move");

        }


    }
   //atualizar o sensor do player e enviar mensagem para o servidor com o atual status
    public void UpdateSensor() {
        sensor.setCenterX(this.avatar.getLayoutX()+25);
        sensor.setCenterY(this.avatar.getLayoutY()+30);
        try {
            this.player.getComunication().send(this.player);
        } catch (IOException e) {
            System.out.println("Err UpdateSensor");
        }


   }
}
