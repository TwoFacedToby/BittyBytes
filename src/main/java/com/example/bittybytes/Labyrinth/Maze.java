package com.example.bittybytes.Labyrinth;

import com.example.bittybytes.SceneManager;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.ceilDiv;

public class Maze {
    private MazeField[][] mazeFields;
    private int width;
    private int height;
    private Random rnd = new Random();
    private ArrayList<Integer> oldJunctions = new ArrayList<>();
    private ArrayList<Integer> ends = new ArrayList<>();
    private ArrayList<String> directions = new ArrayList<>();
    private int nextX = 0;
    private int nextY = 0;
    private int nextLength = 0;
    private String last;

    public Maze(int width, int height){
        this.width = width;
        this.height = height;
        makeMaze(width, height);
    }
    private void makeMaze(int x, int y){
        mazeFields = new MazeField[x][];
        for(int i = 0; i < x; i++){
            mazeFields[i] = new MazeField[y];
            for(int j = 0; j < y; j++){
                mazeFields[i][j] = new MazeField();
            }
        }
        fill();
    }
    public MazeField[][] get(){
        return mazeFields;
    }
    public void changeField(String type, int x, int y){
        mazeFields[x][y].setType(type);
        SceneManager.get().labyrinthController.drawAt(x, y, type);
    }
    private void fill(){
        for(int i = 0; i < mazeFields.length; i++){ //Make everything wall
            for(int j = 0; j < mazeFields[i].length; j++){
                mazeFields[i][j].setType("wall");
            }
        }
        for(int i = 0; i < mazeFields.length; i++){ //Make edges
            mazeFields[i][0].setType("edge");
            mazeFields[i][height-1].setType("edge");
        }
        for(int j = 0; j < mazeFields[0].length; j++){
            mazeFields[0][j].setType("edge");
            mazeFields[width-1][j].setType("edge");
        }
    }
    public void startPath(int x, int y){
        oldJunctions.clear();
        ends.clear();
        changeField("start", 1,1);
        nextX = 1;
        nextY = 1;
        pathMaker();
    }

    public boolean pathMaker(){
        int x = nextX;
        int y = nextY;
        System.out.println("Trying at [ " + x + ", " + y +"]");
        directions = getSurroundingWalls(x, y);
        if(directions.size() >= 1){
            String move = last;
            if(rnd.nextInt(0, 3) == 0 && directions.contains(last)){
                move = last;
            }
            else {
                move = directions.get(rnd.nextInt(0, directions.size()));
                last = move;
            }
            oldJunctions.add(x);
            oldJunctions.add(y);
            switch (move){
                case "e":
                    if(mazeFields[x+1][y].getType().equals("wall")) changeField("path", x+1, y);
                    if(mazeFields[x+2][y].getType().equals("wall")) changeField("path", x+2, y);

                    nextX = x+2;
                    nextY = y;
                    break;
                case "w":
                    if(mazeFields[x-1][y].getType().equals("wall")) changeField("path", x-1, y);
                    if(mazeFields[x-2][y].getType().equals("wall")) changeField("path", x-2, y);
                    nextX = x-2;
                    nextY = y;
                    break;
                case "s":
                    if(mazeFields[x][y+1].getType().equals("wall")) changeField("path", x, y+1);
                    if(mazeFields[x][y+2].getType().equals("wall")) changeField("path", x, y+2);
                    nextX = x;
                    nextY = y+2;
                    break;
                case "n":
                    if(mazeFields[x][y-1].getType().equals("wall")) changeField("path", x, y-1);
                    if(mazeFields[x][y-2].getType().equals("wall")) changeField("path", x, y-2);
                    nextX = x;
                    nextY = y-2;
                    break;
            }
        }
        else{
            //means this path can longer go forward, it should then find a new spot.
            if(ends.size()>1){
                boolean contains = false;
                for(int i = 1; i < ends.size(); i+= 2){
                    if(ends.get(i) == y && ends.get(i-1) == x){
                        contains = true;
                    }
                }
                if(!contains) {
                    ends.add(x);
                    ends.add(y);
                }
            }
            if(oldJunctions.size()>1){
                for(int i = 1; i < oldJunctions.size(); i+= 2){
                    if(getSurroundingWalls(oldJunctions.get(i-1), oldJunctions.get(i)).size() > 0){
                        nextX = oldJunctions.get(i-1);
                        nextY = oldJunctions.get(i);
                        break;
                    }
                    else{ //removes the old junction, because it already has no more options
                        oldJunctions.remove(i);
                        oldJunctions.remove(i-1);
                        i-=2;
                    }
                }
            }
            else return false;
        }
        return true;
    }



    private ArrayList<String> getSurroundingWalls(int x, int y){
        if(directions.size()>0) directions.clear();
        if(y > 1){
            if(mazeFields[x][y-2].getType().equals("wall")){
                directions.add("n");
            }
        }
        if(y < height-2){
            if(mazeFields[x][y+2].getType().equals("wall")){
                directions.add("s");
            }
        }
        if(x > 1){
            if(mazeFields[x-2][y].getType().equals("wall")){
                directions.add("w");
            }
        }
        if(x < width-2){
            if(mazeFields[x+2][y].getType().equals("wall")){
                directions.add("e");
            }
        }
        return directions;
    }











}
