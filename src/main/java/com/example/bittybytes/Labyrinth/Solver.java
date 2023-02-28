package com.example.bittybytes.Labyrinth;

import com.example.bittybytes.SceneManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Solver {

    private int currentX;
    private int currentY;

    private MazeField[][] maze;
    private ArrayList<MazeField> visited = new ArrayList<>();
    private ArrayList<MazeField> junctions= new ArrayList<>();
    private ArrayList<ArrayList<MazeField>> route= new ArrayList<>();
    ArrayList<MazeField> completedPath = null;
    Random rnd = new Random();
    boolean ended;
    int endPos = 0;



    public Solver(MazeField[][] maze){
        completedPath = null;
        endPos = 0;
        ended = false;
        visited.clear();
        junctions.clear();
        route.clear();
        this.maze = maze;
        for(int x = 0; x < maze.length; x++){
            for(int y = 0; y < maze[x].length; y++){
                if(maze[x][y].getType().equals("start")){
                    currentX = x;
                    currentY = y;
                }
            }
        }
    }
    private boolean end(int pos){
        if(completedPath == null){
            completedPath = new ArrayList<>();
            for(int i = 0; i < route.size(); i++){
                for(int j = 0; j < route.get(i).size(); j++){
                    completedPath.add(route.get(i).get(j));
                }
            }
            endPos = -1;
            return true;
        }
        else{
            if(pos < completedPath.size()){
                int x = completedPath.get(pos).getX();
                int y = completedPath.get(pos).getY();
                maze[x][y].setType("complete");
                SceneManager.get().labyrinthController.drawAt(x, y, "complete");
                return true;
            }
            else return false;
        }
    }
    public boolean solveStep(){
        if(ended){
            if(end(endPos)){
                endPos++;
                return true;
            }
            else return false;
        }
        visited.add(maze[currentX][currentY]);
        ArrayList<String> togo = checkAround(currentX, currentY);
        if(togo.size() >= 2){ //If it is a junction, then remove old versions of this junction and add it again as the newest in the list
            junctions.remove(maze[currentX][currentY]);
            junctions.add(maze[currentX][currentY]);
            addRouteStep();
        }
        else junctions.remove(maze[currentX][currentY]); //Else just remove it

        if(togo.size() >= 1){ //Move a random direction that it can go.
            int direction = rnd.nextInt(0, togo.size());
            switch (togo.get(direction)){
                case "w":

                    changeSpot(currentX-1, currentY, "visited");
                    if(maze[currentX-2][currentY].getType().equals("end")) {
                        ended = true;
                    }
                    changeSpot(currentX-2, currentY, "visited");
                    currentX -= 2;
                    break;
                case "e":
                    changeSpot(currentX+1, currentY, "visited");
                    if(maze[currentX+2][currentY].getType().equals("end")) {
                        ended = true;
                    }
                    changeSpot(currentX+2, currentY, "visited");
                    currentX += 2;
                    break;
                case "n":
                    changeSpot(currentX, currentY-1, "visited");
                    if(maze[currentX][currentY-2].getType().equals("end")) {
                        ended = true;
                    }
                    changeSpot(currentX, currentY-2, "visited");
                    currentY -=2;
                    break;
                case "s":
                    changeSpot(currentX, currentY+1, "visited");
                    if(maze[currentX][currentY+2].getType().equals("end")) {
                        ended = true;
                    }
                    changeSpot(currentX, currentY+2, "visited");
                    currentY +=2;
                    break;
            }
        }
        else{ //Cannot move further find last junction and continue from there.
            if(junctions.size() > 0){
                MazeField lastJunction = junctions.get(junctions.size()-1);
                currentX = lastJunction.getX();
                currentY = lastJunction.getY();
                removeLastRouteBit();
            }
            else{ //no more directions to go, must therefore be finished.
                System.out.println("No more directions to go, could not find end");
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> checkAround(int x, int y){
        ArrayList<String> directions = new ArrayList<>();

        int junctions = 0;
        if(x > 1){
            if(canMoveTo(x-2, y) && canMoveTo(x-1, y)) directions.add("w");
        }
        if(x < maze.length-2){
            if(canMoveTo(x+2, y)&& canMoveTo(x+1, y)) directions.add("e");
        }
        if(y > 1){
            if(canMoveTo(x, y-2)&& canMoveTo(x, y-1)) directions.add("n");
        }
        if(y < maze[0].length-2){
            if(canMoveTo(x, y+2)&& canMoveTo(x, y+1)) directions.add("s");
        }

        return directions;
    }

    private boolean canMoveTo(int x, int y){
        if(maze[x][y].getType() == "path" || maze[x][y].getType() == "end" || maze[x][y].getType() == "coin") return true;
        else return false;
    }

    private void changeSpot(int x, int y, String type){
        maze[x][y].setType(type);
        SceneManager.get().labyrinthController.drawAt(x, y, type);
        addToRoute(maze[x][y]);
    }
    private void addToRoute(MazeField field){
        if(route.size() < 1) route.add(new ArrayList<>());
        route.get(route.size()-1).add(field);
    }
    private void addRouteStep(){
        route.add(new ArrayList<>());
    }
    private void removeLastRouteBit(){
        route.remove(route.size()-1);
    }



}
