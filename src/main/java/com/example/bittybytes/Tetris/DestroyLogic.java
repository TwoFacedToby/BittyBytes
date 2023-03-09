package com.example.bittybytes.Tetris;

public class DestroyLogic {

    private int[][] screen;
    private int height;
    private int width;
    private int points;
    boolean fullLines = false;

    public void setScreen(int[][] screen) {
        this.screen = screen;
        width = screen.length;
        height = screen[0].length;
        points = 0;
    }
    public int[][] getScreen(){
        return screen;
    }
    public void onPlaced(){
        showFullLines();
        clearFull();
        moveDownElements();
    }
    private void clearFull(){
        int currentBroken = 0;
        for(int i = height-1; i > 0; i--){
            if(screen[0][i] == -1){ //it only has to check the first, because if any one element is white, then the whole row is.
                for(int j = 0; j < width; j++){
                    screen[j][i] = 0; //Makes them null (background color)
                }
            }
            currentBroken++;
        }
        switch (currentBroken){
            case 1:
                points += 10;
                break;
            case 2:
                points += 25;
                break;
            case 3:
                points += 50;
                break;
            case 4:
                points += 100;
                break;
            default:
                points += 0;
                break;
        }
    }
    public void showFullLines(){
        for(int i = height-1; i > 0; i--){
            boolean full = true;
            for(int j = 0; j < width; j++){
                if(screen[j][i] == 0){//If true, then there is an empty spot, and therefore cannot clear.
                    full = false;
                }
            }
            if(full){ //If it is full, then line should be cleared, we do this by setting them to -1, which makes them white also.
                for(int j = 0; j < width; j++){
                    screen[j][i] = -1;
                }
                fullLines = true;
            }
        }
    }
    private void moveDownElements(){
        int changes = 1;
        while(changes != 0){
            changes = 0;
            for(int i = height-1; i > 0; i--){
                boolean empty = true;
                for(int j = 0; j < width; j++){
                    if(screen[j][i] != 0){//If true, then there are something in this row.
                        empty = false;
                    }
                }
                if(empty){ //If it is empty, then there are nothing in this row, and rows above should be pulled down.
                    for(int j = 0; j < width; j++){
                        if(screen[j][i-1] != 0){
                            screen[j][i] = screen[j][i-1];
                            screen[j][i-1] = 0;
                            changes++;
                        }
                    }
                }
            }
        }
    }
    public int getScore(){
        return points;
    }

}
