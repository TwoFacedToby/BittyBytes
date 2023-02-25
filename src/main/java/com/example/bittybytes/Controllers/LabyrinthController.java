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
    Button reset;
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
    int[] sizes;
    int unEven = 0;

    public void initBoard(){
        drawScreen();

        clickType = "start";
    }
    public void initialize(){
        sizes = new int[4];
        sizes[0] = 5;
        sizes[1] = 10;
        sizes[2] = 20;
        sizes[3] = 40;
        run.setOnAction(e ->{ SceneManager.get().getMazeHandler().run(startX, startY); System.out.println("Run Pressed");});
        instant.setOnAction(e -> makeInstant(true));
        visual.setOnAction(e -> makeInstant(false));
        reset.setOnAction(e -> SceneManager.get().getMazeHandler().reset());
        minus.setOnAction(e -> changeSize(false));
        plus.setOnAction(e -> changeSize(true));
    }
    private void changeSize(boolean more){
        double temp = pixelSize;
        if(more){//Going higher
            for(int i = 0; i < sizes.length-1; i++){
                if(sizes[i] == pixelSize){
                    pixelSize = sizes[i+1];
                    break;
                }
            }
        }
        else{//Going lower
            for(int i = 1; i < sizes.length; i++){
                if(sizes[i] == pixelSize){
                    pixelSize = sizes[i-1];
                    break;
                }
            }
        }
        if(pixelSize != temp){
            if(unEven == 0) unEven=1;
            else unEven = 0;
            size.setText(((int)pixelSize)+"");
            drawScreen();
            SceneManager.get().getMazeHandler().reset();
        }
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
        for(n = 0; n < (window.getHeight()/pixelSize)-(1); n++){
            for(m = 0;m < (window.getWidth()/pixelSize)-(1); m++){
                Button pixel = new Button();
                pixel.setPrefSize(pixelSize, pixelSize);
                pixel.setMaxSize(pixelSize, pixelSize);
                pixel.setStyle("-fx-background-color: #303030; -fx-font-size: 1");
                box.getChildren().add(pixel);
                pixels.add(pixel);
            }
        }
        width = m;
        height = n;
    }
    public void pressed(int x, int y){
        Button b = pixels.get(xyToIndex(x, y));
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
            instant.setStyle(instant.getStyle() + "; -fx-text-fill: #eeeeee");
            visual.setStyle(visual.getStyle() + "; -fx-text-fill: #808080");
        }
        else{
            instant.setStyle(instant.getStyle() + "; -fx-text-fill: #808080");
            visual.setStyle(visual.getStyle() + "; -fx-text-fill: #eeeeee");
        }
    }
    public void drawAt(int x, int y, String type){
        Button pixel = pixels.get(xyToIndex(x, y));
        switch (type){
            case "wall":
            case "edge":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #346734");
                break;
            case "path":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #d7cece"); //Empty Path
                break;
            case "visitedPath":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #ef2424"); //Visited Path
                break;
            case "junction":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #dc3e3e");
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
