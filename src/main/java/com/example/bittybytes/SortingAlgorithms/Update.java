package com.example.bittybytes.SortingAlgorithms;

import com.example.bittybytes.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Update {
    private Timeline frame;

    SortingHandler sortingHandler;

    public Update(SortingHandler sortingHandler){
        this.sortingHandler = sortingHandler;
    }

    public void setUpdateSpeed(double millis){
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
        sortingHandler.update();
    }

}
