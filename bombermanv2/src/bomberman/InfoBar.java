package bomberman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoBar implements Initializable {
    private long lastTime;
    private long time;
    private int score;
    @FXML
    Label lbTime;
    @FXML
    Label lbScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
    }
    public void update(){
        long newTime = System.currentTimeMillis();
        time = time + lastTime - newTime;
        lastTime = newTime;
        if(time < 0){
            Game.getInstance().setEndGame(true);
            lbTime.setText("TIMEOUT");
        }
        lbTime.setText(time / 1000 + "");

    }

    public void addScore(int addScore){
        score += addScore;
        lbScore.setText(score + "");
    }

    public void reset(){
        time = 200000;
        lastTime = System.currentTimeMillis();
    }
}
