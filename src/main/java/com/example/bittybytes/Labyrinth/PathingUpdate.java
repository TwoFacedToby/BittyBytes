package com.example.bittybytes.Labyrinth;

import com.example.bittybytes.SortingAlgorithms.SortingHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PathingUpdate {
    private Timeline frame;

    LabyrinthHandler labyrinthHandler;
    boolean running = false;
    public PathingUpdate(LabyrinthHandler labyrinthHandler){
        this.labyrinthHandler = labyrinthHandler;
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
        labyrinthHandler.update();
    }

    public boolean isRunning() {
        return running;
    }
}
