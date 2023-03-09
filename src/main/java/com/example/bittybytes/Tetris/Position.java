package com.example.bittybytes.Tetris;

public class Position {

    private int xPos;
    private int yPos;


    public Position(int x, int y){
        setPos(x, y);
    }
    public void setPos(int x, int y){
        xPos = x;
        yPos = y;
    }
    public int x(){
        return xPos;
    }
    public int y(){
        return yPos;
    }
    public int[] getPos(){
        int[] toReturn = new int[2];
        toReturn[0] = xPos;
        toReturn[1] = yPos;
        return toReturn;
    }
}
