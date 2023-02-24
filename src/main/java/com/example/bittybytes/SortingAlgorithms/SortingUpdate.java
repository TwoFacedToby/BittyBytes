package com.example.bittybytes.SortingAlgorithms;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SortingUpdate {
    private Timeline frame;

    SortingHandler sortingHandler;
    boolean running = false;
    public SortingUpdate(SortingHandler sortingHandler){
        this.sortingHandler = sortingHandler;
    }

    public void setUpdateSpeed(double millis){
        running = true;
        frame = new Timeline(
                new KeyFrame(Duration.ZERO, event -> update()),
                new KeyFrame(Duration.millis(millis), e -> update())
        );
        frame.setAutoReverse(true);
        frame.setCycleCount(Timeline.INDEFINITE);
        frame.play();
    }
    public void stopUpdate(){
        if(running) {
            frame.stop();
            running = false;
        }
    }
    private void update(){
        sortingHandler.update();
    }

    public boolean isRunning() {
        return running;
    }
}
