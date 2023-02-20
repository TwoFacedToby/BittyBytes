package com.example.bittybytes.Runner;

import com.example.bittybytes.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class RunnerUpdate {
    private Timeline frame;
    private double jump = -0.1;;
    private boolean direction = false;
    private double score = 0;
    private double highScore = 0;
    private int timer = 0;
    private double speed = 5;
    private int obstacleTime = 100;
    private ArrayList<VBox> currentObstacles = new ArrayList<>();
    private isBoxesHitting isBoxesHitting = new isBoxesHitting();


    CalculateJumpHeight calculateJH = new CalculateJumpHeight();


    public void startUpdate(){
        setUpdateSpeed(16); //16.6667 is 60 frames pr second
        SceneManager.get().runnerController.dead(false);
        SceneManager.get().runnerController.setHighScoreText((int)Math.floor(highScore)+"");
    }

    private void setUpdateSpeed(double millis){
        frame = new Timeline(
                new KeyFrame(Duration.ZERO, event -> update()),
                new KeyFrame(Duration.millis(millis), e -> update())
        );
        frame.setAutoReverse(true);
        frame.setCycleCount(Timeline.INDEFINITE);
        frame.play();
    }
    public void stopUpdate(){
        frame.stop();
        SceneManager.get().runnerController.dead(true);
    }
    private void update(){
        score+=0.1;
        SceneManager.get().runnerController.setScoreText((int)Math.floor(score)+"");
        //Jump
        if(jump < 200 && jump > -0.1){
                double y = calculateJH.getY(jump);
                if(y < -100){
                    jump = 201;
                    SceneManager.get().runnerController.setHeight(0);
                }
                else {
                    y = 1 - (Math.abs(y) * 0.01);
                    SceneManager.get().runnerController.setHeight(y);

                    if(jump > 100) direction = true;
                    if(direction){//Going down
                        jump -= 10 * (1 - y) + 0.5;
                    }
                    else{//Going up
                        jump += 10 * (1 - y) + 0.5;
                    }
                }
            }
        else{
                SceneManager.get().runnerController.setHeight(0);
                jump = -0.1;
            }

        //Obstacles Moving
        if(currentObstacles != null && currentObstacles.size() > 0){
            for(int i = 0; i < currentObstacles.size(); i++){

                if(currentObstacles.get(i).getLayoutX() < 6){
                    //Remove Obstacle
                    SceneManager.get().runnerController.removeObstacle(currentObstacles.get(i));
                    currentObstacles.remove(i);
                } //Move Obstacle
                else {
                    if(isBoxesHitting.isHitting(currentObstacles.get(i), SceneManager.get().runnerController.getPlayer())){
                        //An obstacle has hit the player
                        stopUpdate();
                    }
                    currentObstacles.get(i).setLayoutX(currentObstacles.get(i).getLayoutX() - speed);
                }
            }
        }

        if(timer < obstacleTime) timer++;
        else{
            SceneManager.get().runnerController.placeObstacle();
            timer = 0;
        }

    }
    public void clear(){
        for(int i = 0; i < currentObstacles.size(); i++){
            SceneManager.get().runnerController.removeObstacle(currentObstacles.get(i));
        }
        currentObstacles.clear();
        jump = -0.1;
        SceneManager.get().runnerController.setHeight(0);
        direction = false;
        //clear current score and update highscore
        highScore = score;
        score = 0;
        SceneManager.get().runnerController.setScoreText((int)Math.floor(score)+"");
        stopUpdate();
        startUpdate();
    }


    public void setJump(double jump){
        this.jump = jump;
        direction = false;
    }
    public double getJump(){
        return jump;
    }
    public void addObstacle(VBox b){
        currentObstacles.add(b);
    }
}
