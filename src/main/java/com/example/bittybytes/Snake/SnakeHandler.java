package com.example.bittybytes.Snake;

import com.example.bittybytes.Controllers.SnakeController;
import com.example.bittybytes.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class SnakeHandler {
    private int[] size;
    private boolean running = true;
    private SnakeController ui = SceneManager.get().snakeController;
    private Timeline frame;
    private int direction = -1;
    private int moveDelay = 0;
    private int highscore = 0;


    private int speed = 100;


    ArrayList<Position> snakePositions = new ArrayList<>();
    Position foodPosition;
    Position headPosition;
    int snakeLength = 3;

    int x = 0;
    int y = 0;

    public void initiate(){
        SceneManager.get().snakeController.setDead(0);
        size = ui.getSize();
        ui.clear();
        direction = -1;
        if(snakeLength-3 > highscore) highscore = snakeLength-3;
        ui.setHighScoreText(highscore+"");
        snakeLength = 3;
        snakePositions.clear();
        headPosition = new Position(Math.round((float)size[0]/2), Math.round((float)size[1]/2));
        snakePositions.add(headPosition);
        ui.drawPixelAt(headPosition.x, headPosition.y, "snake");
        setUpdateSpeed(speed); //In milliseconds
        newFood();
        ui.setScoreText("" + 0);
    }

    private void move(){
        if(!running) return;
        if(moveDelay>0) {
            moveDelay = 0;
            return;
        }
        Position nextPosition;
        switch (direction){
            case -1:
                //Dont do anything yet, player has not moved.
                return;
            case 0: //Right
                nextPosition = new Position(headPosition.x+1, headPosition.y);
                break;
            case 1: //Down
                nextPosition = new Position(headPosition.x, headPosition.y+1);
                break;
            case 2: //Left
                nextPosition = new Position(headPosition.x-1, headPosition.y);
                break;
            case 3: //Up
                nextPosition = new Position(headPosition.x, headPosition.y-1);
                break;
            default:
                System.out.println("Direction out of scope 0-3 [" + direction + "]");
                stopUpdate();
                return;
        }
        //If hits walls
        if(nextPosition.x > size[0]-1 || nextPosition.x < 0|| nextPosition.y > size[1]-1 || nextPosition.y < 0){
            stopUpdate();
            return;
        }
        //If hits itself
        for(int i = 0; i < snakePositions.size(); i++){
            if(snakePositions.get(i).x == nextPosition.x && snakePositions.get(i).y == nextPosition.y){
                stopUpdate();
                return;
            }
        }


        //Creates next bit of Snake
        headPosition = nextPosition;
        ui.drawPixelAt(headPosition.x, headPosition.y, "snake");
        snakePositions.add(headPosition);

        //If hits food
        if(headPosition.x == foodPosition.x && headPosition.y == foodPosition.y){
            snakeLength++;
            ui.setScoreText("" + (snakeLength-3));
            newFood();
        }

        //Removes the end of the tail unless snake has just eaten.
        if(snakeLength < snakePositions.size()){
            ui.drawPixelAt(snakePositions.get(0).x, snakePositions.get(0).y, "empty");
            snakePositions.remove(0);
        }

    }
    private void newFood(){
        Random rnd = new Random();

        boolean spotEmpty = false;
        int tries = 0;
        while(!spotEmpty){
            spotEmpty = true;
            foodPosition = new Position(rnd.nextInt(0, size[0]),rnd.nextInt(0, size[1]));
            for(int i = 0; i < snakePositions.size(); i++){
                if(snakePositions.get(i).x == foodPosition.x && snakePositions.get(i).y == foodPosition.y){
                    spotEmpty = false;
                }
            }
            if(tries > size[0]*size[1]*100){
                stopUpdate();
                System.out.println("Cannot find empty spot to put food after " + tries + " tries.");
            }
            else tries++;
        }
        ui.drawPixelAt(foodPosition.x, foodPosition.y, "food");
    }
    private void setUpdateSpeed(double millis){
        frame = new Timeline(
                new KeyFrame(Duration.ZERO, event -> move()),
                new KeyFrame(Duration.millis(millis), e -> move())
        );
        frame.setAutoReverse(true);
        frame.setCycleCount(Timeline.INDEFINITE);
        frame.play();
        running = true;
    }
    public void stopUpdate(){
        SceneManager.get().snakeController.setDead(1);
        frame.stop();
        running = false;
    }
    public void input(KeyEvent input){
        if(input.getCode()== KeyCode.ESCAPE)
        {
            SceneManager.get().switchScene("menu");
            return;
        }
        if(input.getCode()== KeyCode.D)
        {
            if(direction != 0 && direction != 2){
                moveDelay = 0;
                direction = 0;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.S)
        {
            if(direction != 1 && direction != 3){
                moveDelay = 0;
                direction = 1;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.A)
        {
            if(direction != 2 && direction != 0){
                moveDelay = 0;
                direction = 2;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.W)
        {
            if(direction != 3 && direction != 1){
                moveDelay = 0;
                direction = 3;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.RIGHT)
        {
            if(direction != 0 && direction != 2){
                moveDelay = 0;
                direction = 0;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.DOWN)
        {
            if(direction != 1 && direction != 3){
                moveDelay = 0;
                direction = 1;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.LEFT)
        {
            if(direction != 2 && direction != 0){
                moveDelay = 0;
                direction = 2;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.UP)
        {
            if(direction != 3 && direction != 1){
                moveDelay = 0;
                direction = 3;
                move();
                moveDelay = 1;
            }
            return;
        }
        if(input.getCode()== KeyCode.R)
        {
            initiate();
            return;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
