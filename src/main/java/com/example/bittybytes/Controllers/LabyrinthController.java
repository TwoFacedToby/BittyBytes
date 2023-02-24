package com.example.bittybytes.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LabyrinthController {


    @FXML
    VBox window;


    ArrayList<VBox> pixels = new ArrayList<>();

    public void initBoard(){
        drawScreen();
    }
    private void drawScreen(){
        window.getChildren().clear();
        FlowPane box = new FlowPane();
        box.setAlignment(Pos.CENTER);
        pixels.clear();
        pixels = new ArrayList<>();
        window.getChildren().add(box);
        int n = 0, m = 0;
        for(n = 0; n < (window.getHeight()/10)-1; n++){
            for(m = 0;m < (window.getWidth()/10)-1; m++){
                VBox pixel = new VBox();
                pixel.setPrefSize(10, 10);
                pixel.setStyle("-fx-background-color: #303030; -fx-border-color: #101010");
                box.getChildren().add(pixel);
                pixels.add(pixel);
            }
        }
    }
}
