package com.example.bittybytes.Labyrinth;

import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MazeField {
    private String type = "path";
    private ArrayList<Integer> visited = null;

    public void setType(String s){
        type = s;
    }
    public String getType(){
        return type;
    }

    public ArrayList<Integer> getVisited() {
        return visited;
    }
    public void addVisited(int route){
        visited.add(route);
    }

    public void setVisited(ArrayList<Integer> visited) {
        this.visited = visited;
    }
}
