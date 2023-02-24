package com.example.bittybytes.Controllers;

import com.example.bittybytes.Labyrinth.MazeField;
import com.example.bittybytes.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LabyrinthController {


    @FXML
    VBox window;
    @FXML
    Button plus;
    @FXML
    Button minus;
    @FXML
    Label size;
    @FXML
    Button run;
    @FXML
    Button create;
    @FXML
            Button visual;
    @FXML
            Button instant;


    ArrayList<Button> pixels = new ArrayList<>();
    int width;
    int height;
    String clickType = "start";
    int startX = 1;
    int startY = 1;
    double pixelSize = 10;

    public void initBoard(){
        drawScreen();

        clickType = "start";
    }
    public void initialize(){
        run.setOnAction(e ->{ SceneManager.get().getMazeHandler().run(startX, startY); System.out.println("Run Pressed");});
        instant.setOnAction(e -> makeInstant(true));
        visual.setOnAction(e -> makeInstant(false));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void drawScreen(){
        window.getChildren().clear();
        FlowPane box = new FlowPane();
        box.setAlignment(Pos.CENTER);
        pixels.clear();
        pixels = new ArrayList<>();
        window.getChildren().add(box);
        int n = 0, m = 0;
        for(n = 0; n < (window.getHeight()/pixelSize)-1; n++){
            for(m = 0;m < (window.getWidth()/pixelSize)-1; m++){
                Button pixel = new Button();
                pixel.setPrefSize(pixelSize, pixelSize);
                pixel.setMaxSize(pixelSize, pixelSize);
                pixel.setStyle("-fx-background-color: #303030; -fx-border-color: #101010; -fx-font-size: 1");
                box.getChildren().add(pixel);
                pixels.add(pixel);
            }
        }
        width = m;
        height = n;
    }
    public void pressed(int x, int y){
        Button b = pixels.get(xyToIndex(x, y));
        System.out.println("pressed at [" + x + ", " + y +  "]");
    }
    public void draw(MazeField[][] toDraw){
        for(int i = 0; i < toDraw.length; i++){
            for(int j = 0; j < toDraw[i].length; j++){
                Button pixel = pixels.get(xyToIndex(i, j));
                int x = i, y = j;
                pixel.setOnAction(e -> pressed(x, y));
                drawAt(x, y, toDraw[i][j].getType());
            }
        }
    }
    private void makeInstant(boolean isInstant){
        SceneManager.get().getMazeHandler().makeInstant(isInstant);
        if(isInstant){
            instant.setStyle(instant.getStyle() + "; text-emphasis: #eeeeee");
            visual.setStyle(visual.getStyle() + "; text-emphasis: #808080");
        }
        else{
            instant.setStyle(instant.getStyle() + "; text-emphasis: #808080");
            visual.setStyle(visual.getStyle() + "; text-emphasis: #eeeeee");
        }
    }
    public void drawAt(int x, int y, String type){
        Button pixel = pixels.get(xyToIndex(x, y));
        switch (type){
            case "wall":
            case "edge":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #eeeeee");
                break;
            case "path":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #404040"); //Empty Path
                break;
            case "visitedPath":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #638f63"); //Visited Path
                break;
            case "junction":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #606060");
                break;
            case "coin":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #ffdc00");
                break;
            case "start":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #052677");
                break;
            case "end":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #004fff");
                break;
        }
    }
    private int xyToIndex(int x, int y){
        return y*width+x;
    }

}
