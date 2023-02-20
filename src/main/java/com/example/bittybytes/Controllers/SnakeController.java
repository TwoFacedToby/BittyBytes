package com.example.bittybytes.Controllers;

import com.example.bittybytes.SceneManager;
import com.example.bittybytes.Snake.SnakeHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class SnakeController {

    @FXML
    VBox window;
    @FXML
    Button sizeB1;
    @FXML
    Button sizeB2;
    @FXML
    Button sizeB3;
    @FXML
    VBox sizeBackground1;
    @FXML
    VBox sizeBackground2;
    @FXML
    VBox sizeBackground3;
    @FXML
    Label scoreText;
    @FXML
    Label highScoreText;
    @FXML
    Label dead;


    ArrayList<VBox> pixels = new ArrayList<>();
    int width;
    int height;

    int screenSize = 3;
    int[] screenSizes = new int[3];

    public void initialize(){
        screenSizes[0] = 404;
        screenSizes[1] = 604;
        screenSizes[2] = 1103;
        screenSize = 3;
        sizeB1.setOnAction(e -> setWindowSize(1));
        sizeB2.setOnAction(e -> setWindowSize(2));
        sizeB3.setOnAction(e -> setWindowSize(3));
        dead.setOpacity(0);
    }
    public void setDead(double opacity){
        dead.setOpacity(opacity);
    }

    public void initBoard(){

        sizeButtons(screenSize);
        drawScreen();
    }
    private void sizeButtons(int preset){
        switch (preset){
            case 1:
                sizeBackground1.setStyle(sizeBackground1.getStyle() + "; -fx-border-color: #309030");
                sizeBackground2.setStyle(sizeBackground2.getStyle() + "; -fx-border-color: #eeeeee");
                sizeBackground3.setStyle(sizeBackground3.getStyle() + "; -fx-border-color: #eeeeee");
                window.setPrefSize(screenSizes[0], screenSizes[1]);
                break;
            case 2:
                sizeBackground1.setStyle(sizeBackground1.getStyle() + "; -fx-border-color: #eeeeee");
                sizeBackground2.setStyle(sizeBackground2.getStyle() + "; -fx-border-color: #309030");
                sizeBackground3.setStyle(sizeBackground3.getStyle() + "; -fx-border-color: #eeeeee");
                window.setPrefSize(screenSizes[1], screenSizes[1]);
                break;
            case 3:
                sizeBackground1.setStyle(sizeBackground1.getStyle() + "; -fx-border-color: #eeeeee");
                sizeBackground2.setStyle(sizeBackground2.getStyle() + "; -fx-border-color: #eeeeee");
                sizeBackground3.setStyle(sizeBackground3.getStyle() + "; -fx-border-color: #309030");
                window.setPrefSize(screenSizes[2], screenSizes[1]);
                break;
        }
        screenSize = preset;
    }
    private void setWindowSize(int preset){
        sizeButtons(preset);
        drawScreen();
        SceneManager.get().getSnakeHandler().stopUpdate();
        SceneManager.get().getSnakeHandler().initiate();
        SceneManager.get().getSnakeHandler().setSpeed(300/preset);
    }
    public void setScoreText(String text){
        scoreText.setText(text);
    }
    public void setHighScoreText(String text){
        highScoreText.setText(text);
    }
    private void drawScreen(){
        window.getChildren().clear();
        FlowPane box = new FlowPane();
        pixels.clear();
        pixels = new ArrayList<>();
        window.getChildren().add(box);
        int n = 0, m = 0;
        for(n = 0; n < screenSizes[1]/20; n++){
            for(m = 0;m < screenSizes[screenSize-1]/20; m++){
                VBox pixel = new VBox();
                pixel.setPrefSize(20, 20);
                pixel.setStyle("-fx-background-color: #303030; -fx-border-color: #101010");
                box.getChildren().add(pixel);
                pixels.add(pixel);
            }
        }
        width = m;
        height = n;
    }
    private int xyToIndex(int x, int y){
        return x + y*width;
    }
    public void drawPixelAt(int x, int y, String type){
        VBox pixel = pixels.get(xyToIndex(x, y));
        switch (type){
            case "snake":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #808080");
                break;
            case "food":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #309030");
                break;
            case "empty":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #303030");
                break;
        }
    }

    public int[] getSize(){
        int[] size = new int[2];
        size[0] = width;
        size[1] = height;
        return size;
    }
    public void clear(){
        for(int i = 0; i < pixels.size(); i++){
            pixels.get(i).setStyle(pixels.get(i).getStyle() + "; -fx-background-color: #303030");
        }
    }


}
