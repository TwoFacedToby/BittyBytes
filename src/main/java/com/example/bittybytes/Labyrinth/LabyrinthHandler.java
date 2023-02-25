package com.example.bittybytes.Labyrinth;

import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LabyrinthHandler {


    int width;
    int height;
    Maze maze;
    boolean creating;
    boolean instant = false;
    PathingUpdate update = new PathingUpdate(this);
    public void initiate(){
        width = SceneManager.get().labyrinthController.getWidth();
        height = SceneManager.get().labyrinthController.getHeight();
        initFields(width, height);
        maze = new Maze(width, height);
        changeField("wall", 10, 12);
        changeField("wall", 10, 13);
        changeField("wall", 10, 14);
        changeField("wall", 10, 15);
        changeField("wall", 9, 13);
        changeField("wall", 11, 13);
        drawFields();
    }
    public void makeInstant(boolean isInstant){
        instant = isInstant;
    }
    public void run(int x, int y){
        maze.startPath(x ,y);
        creating = true;
        if(instant){
            while(creating){
                creating = maze.pathMaker();
            }
        }
        else{
            if(!update.running) update.setUpdateSpeed(40);
        }
    }
    public void reset(){
        width = SceneManager.get().labyrinthController.getWidth();
        height = SceneManager.get().labyrinthController.getHeight();
        maze = new Maze(width, height);
        if(update.isRunning()) update.stopUpdate();
        drawFields();
    }
    public void initFields(int x, int y){

    }
    public void update(){
        if(creating){
            if(!maze.pathMaker()) creating = false;
        }
        else update.stopUpdate();
    }
    private void drawFields(){
        SceneManager.get().labyrinthController.draw(maze.get());
    }
    public void changeField(String type, int x, int y){
        maze.changeField(type, x, y);
    }
    public void input(KeyEvent input) {
        if(input.getCode()== KeyCode.ESCAPE)
        {
            SceneManager.get().switchScene("menu");
            return;
        }
    }
}
