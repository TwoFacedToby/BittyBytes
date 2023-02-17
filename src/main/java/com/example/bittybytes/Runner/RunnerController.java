package com.example.bittybytes.Runner;

import com.example.bittybytes.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RunnerController {



    @FXML
    VBox player;
    @FXML
    AnchorPane above;
    @FXML
    Label dead;
    @FXML
    Label scoreText;
    @FXML
    Label highScoreText;
    private double minJump = 378;
    private double maxJump = 225;
    private Obstacles obstacles = new Obstacles();


    public void initialize(){
        player.setLayoutX(150);
        player.setLayoutY(378);
    }

    public void initBoard(){

    }

    public void setHeight(double position){
        //position = 0 => 380
        //position = 1 => 225
        //225 + (1-position)*(380-225)*
        player.setLayoutY(maxJump + (1-position) * (378 - 225));
    }
    public void placeObstacle(){
        VBox newObstacle = obstacles.getObstacle();
        above.getChildren().add(newObstacle);
        SceneManager.get().getRunnerHandler().update.addObstacle(newObstacle);
    }
    public void removeObstacle(VBox b){
        above.getChildren().remove(b);
    }
    public void dead (boolean isDead){
        if(isDead) dead.setOpacity(1);
        else dead.setOpacity(0);
    }
    public void setScoreText(String text){
        scoreText.setText(text);
    }
    public void setHighScoreText(String text){
        highScoreText.setText(text);
    }
    public VBox getPlayer(){
        return player;
    }

}
