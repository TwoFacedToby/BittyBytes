package com.example.bittybytes.Runner;

import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RunnerHandler {


    public RunnerUpdate update;
    public void initiate(){
        update = new RunnerUpdate();
        update.startUpdate();
    }

    private void jump(){

        if(update.getJump() == -0.1) update.setJump(0); //Only jumps when jump is finished
    }

    public void input(KeyEvent input) {
        if (input.getCode() == KeyCode.ESCAPE) {
            SceneManager.get().switchScene("menu");
            return;
        }
        if (input.getCode() == KeyCode.W || input.getCode() == KeyCode.SPACE || input.getCode() == KeyCode.UP) {
            jump();
            return;
        }
        if(input.getCode() == KeyCode.R){
            update.clear();
        }
    }
}
