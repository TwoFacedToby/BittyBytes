package com.example.bittybytes.Labyrinth;

public class Solver {

    private int currentX;
    private int currentY;
    private MazeField[][] maze;

    public void Solver(MazeField[][] maze, int startX, int startY){
        this.maze = maze;
    }



    private boolean canMoveTo(int x, int y){
        if(maze[x][y].getType() == "path") return true;
        else return false;
    }

    private boolean changeSpt(int x, int y, String type){
        maze[x][y].setType(type);
    }



}
