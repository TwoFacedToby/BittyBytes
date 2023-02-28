package com.example.bittybytes.Labyrinth;

import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LabyrinthHandler {

    double updateSpeed = 20;
    int width;
    int height;
    Maze maze;
    Solver solver;
    boolean creating;
    boolean solving;
    boolean instant = false;
    PathingUpdate update = new PathingUpdate(this);
    public void initiate(){
        width = SceneManager.get().labyrinthController.getWidth();
        height = SceneManager.get().labyrinthController.getHeight();
        maze = new Maze(width, height);
        drawFields();
    }
    public void makeInstant(boolean isInstant){
        instant = isInstant;
    }
    public void run(int x, int y){
        SceneManager.get().labyrinthController.setMazeFinished(false);
        maze.startPath(x ,y);
        creating = true;
        solving = false;
        if(instant){
            while(creating){
                creating = maze.pathMaker();
            }
            SceneManager.get().labyrinthController.setMazeFinished(true);
        }
        else{
            if(!update.running) update.setUpdateSpeed(updateSpeed);
        }
    }
    public void reset(){
        SceneManager.get().labyrinthController.setMazeFinished(false);
        width = SceneManager.get().labyrinthController.getWidth();
        height = SceneManager.get().labyrinthController.getHeight();
        maze = new Maze(width, height);
        if(update.isRunning()) update.stopUpdate();
        drawFields();
    }
    public void update(){
        if(creating){
            if(!maze.pathMaker()) {
                creating = false;
                SceneManager.get().labyrinthController.setMazeFinished(true);
                update.stopUpdate();
            }
        }
        if(solving){
            if(!solver.solveStep()){
                solving = false;
                update.stopUpdate();
            }
        }
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
    public void setEnd(int x, int y){
        maze.changeField("end", x, y);
    }
    public void solve(){
        solver = new Solver(maze.get());
        solving = true;
        if(instant){
            while(solving){
                if(!solver.solveStep()){
                    solving = false;
                }
            }
        }
        else{
            if(!update.running) {
                update.setUpdateSpeed(updateSpeed);
            }
        }
    }
}
