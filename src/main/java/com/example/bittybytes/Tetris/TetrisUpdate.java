package com.example.bittybytes.Tetris;

import com.example.bittybytes.Runner.CalculateJumpHeight;
import com.example.bittybytes.Runner.isBoxesHitting;
import com.example.bittybytes.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class TetrisUpdate {
    private Timeline frame;
    TetrisHandler handler;

    public TetrisUpdate(TetrisHandler handler){
        this.handler = handler;
    }

    public void startUpdate(){
        setUpdateSpeed(200); //16.6667 is 60 frames pr second
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
    }
    private void update(){
        handler.update();
    }

}
