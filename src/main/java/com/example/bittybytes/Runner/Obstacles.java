package com.example.bittybytes.Runner;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class Obstacles {
    private int amount = 4;
    ArrayList<int[]> shapes = new ArrayList<>();

    public Obstacles(){
        for(int i = 0; i < amount; i++){
            shapes.add(new int[4]);
        }
        //Size: x, y - Position: x, y
        createBox(shapes.get(0), 15, 65, 1086, 387); //Thin tall
        createBox(shapes.get(1), 48, 48, 1053, 404); //Medium Squarre
        createBox(shapes.get(2), 69, 35, 1032, 417); //Low Long
        createBox(shapes.get(3), 69, 37, 1032, 318); //Flying
    }
    private void createBox(int[] a, int width, int height, int x, int y){
        a[0] = width;
        a[1] = height;
        a[2] = x;
        a[3] = y;
    }

    public VBox getObstacle(){
        Random rnd = new Random();
        int[] shape = shapes.get(rnd.nextInt(0, amount));
        VBox b = new VBox();
        b.setPrefSize(shape[0], shape[1]);
        b.setLayoutX(shape[2]);
        b.setLayoutY(shape[3]);
        b.setStyle("-fx-border-color: #eeeeee");
        return b;
    }




}
