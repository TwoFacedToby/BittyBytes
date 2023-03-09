package com.example.bittybytes.Tetris;

import com.example.bittybytes.Controllers.TetrisController;
import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TetrisHandler {

    int[][] screen;
    TetrisController controller;
    int width = 0;
    int height = 0;
    int roundDelay;
    boolean landed = false;
    DestroyLogic destroyLogic = new DestroyLogic();
    NextSpawn next;
    MovementLogic move;
    TetrisUpdate updater;

    public void initiate() {
        controller = SceneManager.get().tetrisController;
        width = controller.getWidth();
        height = controller.getHeight();
        screen = new int[width][];
        for(int i = 0; i < screen.length; i++){
            screen[i] = new int[height];
        }

        destroyLogic.setScreen(screen);
        next = new NextSpawn();
        move = new MovementLogic(this);
        move.next(next.getNext());
        updater = new TetrisUpdate(this);
        updater.startUpdate();
    }
    public void update(){
        screen = destroyLogic.getScreen();
        drawScreen();
        if(roundDelay > 0){
            roundDelay--;
            landed = false;
            return;
        }
        else if(!move.goDown()){ //Landed, spawn new
            if(roundDelay == 0) {
                roundDelay = 2;
                destroyLogic.onPlaced();
                controller.setScoreText(destroyLogic.getScore()+"");
                move.next(next.getNext());
            }
        }
    }
    private void restart(){

    }
    public void setScreen(int [][] newScreen){
        screen = newScreen;
    }
    public int[][] getScreen(){
        return screen;
    }
    public void input(KeyEvent input) {
        if (input.getCode() == KeyCode.ESCAPE) {
            SceneManager.get().switchScene("menu");
            return;
        }
        if (input.getCode() == KeyCode.O) {
            System.out.println(next.getNext());
            return;
        }
        if (input.getCode() == KeyCode.D || input.getCode() == KeyCode.RIGHT) {
            move.moveRight();
            return;
        }
        if (input.getCode() == KeyCode.A || input.getCode() == KeyCode.LEFT) {
            move.moveLeft();
            return;
        }
        if (input.getCode() == KeyCode.W || input.getCode() == KeyCode.UP) {
            move.instantDown();
            return;
        }
        if (input.getCode() == KeyCode.S || input.getCode() == KeyCode.DOWN) {
            //move.goDown();
            return;
        }
        if (input.getCode() == KeyCode.J) {
            move.turnLeft();
            return;
        }
        if (input.getCode() == KeyCode.K) {
            move.turnRight();
            return;
        }
        if (input.getCode() == KeyCode.R) {
            restart();
            return;
        }
    }
    public void drawScreen(){
        for(int x = 0; x < screen.length; x++){
            for(int y = 0; y < screen[0].length; y++){
                controller.drawAt(screen[x][y], x, y);
            }
        }
    }
}
