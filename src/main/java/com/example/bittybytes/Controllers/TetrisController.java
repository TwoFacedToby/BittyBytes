package com.example.bittybytes.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TetrisController {

    @FXML
    VBox window;
    @FXML
    HBox next1;
    @FXML
    HBox next2;
    @FXML
    HBox next3;
    @FXML
    Label scoreText;
    @FXML
    Label highScoreText;
    @FXML
    Label dead;

    ArrayList<VBox> pixels = new ArrayList<>();
    int width;
    int height;
    HBox[] nexts = new HBox[3];
    public void initBoard(){
        drawScreen();
        showDead(false);
        nexts[0] = next1;
        nexts[1] = next2;
        nexts[2] = next3;
    }

    public void showDead(boolean show){
        if(show){
            dead.setOpacity(1);
        }
        else{
            dead.setOpacity(0);
        }
    }
    //0 = 2x2
    //1 = T
    //2 = 1x4
    //3 = Hinge Right
    //4 = Hinge Left
    public void drawNext(int [] next){
        for(int i = 0; i < next.length; i++){

            VBox[][] area;
            switch (next[i]){
                case 0://2x2
                    area = createSquare(2, 2, i, 20);
                    area[0][0].setStyle("-fx-background-color: #146486");
                    area[1][0].setStyle("-fx-background-color: #146486");
                    area[0][1].setStyle("-fx-background-color: #146486");
                    area[1][1].setStyle("-fx-background-color: #146486");
                    break;
                case 1://Small T
                    area = createSquare(3, 2, i, 20);
                    area[1][0].setStyle("-fx-background-color: #cbb20c");
                    area[0][1].setStyle("-fx-background-color: #cbb20c");
                    area[1][1].setStyle("-fx-background-color: #cbb20c");
                    area[2][1].setStyle("-fx-background-color: #cbb20c");
                    break;
                case 2://Long Boi  4x1
                    area = createSquare(1, 4, i, 15);
                    area[0][0].setStyle("-fx-background-color: #d37f28");
                    area[0][1].setStyle("-fx-background-color: #d37f28");
                    area[0][2].setStyle("-fx-background-color: #d37f28");
                    area[0][3].setStyle("-fx-background-color: #d37f28");
                    break;
                case 3://Hinge Right
                    area = createSquare(2, 3, i, 20);
                    area[0][0].setStyle("-fx-background-color: #861414");
                    area[0][1].setStyle("-fx-background-color: #861414");
                    area[0][2].setStyle("-fx-background-color: #861414");
                    area[1][0].setStyle("-fx-background-color: #861414");
                    break;
                case 4://Hinge Left
                    area = createSquare(2, 3, i, 20);
                    area[1][0].setStyle("-fx-background-color: #238614");
                    area[1][1].setStyle("-fx-background-color: #238614");
                    area[1][2].setStyle("-fx-background-color: #238614");
                    area[0][0].setStyle("-fx-background-color: #238614");
                    break;
            }
        }
    }
 /*
            "-fx-background-color: #146486"); //2x2

            "-fx-background-color: #238614"); //Hinge left
               "-fx-background-color: #861414"); //Hinge right
                "-fx-background-color: #cbb20c"); //Small T
                "-fx-background-color: #d37f28"); //4x1

  */

    private VBox[][] createSquare(int x, int y, int spot, int size){
        nexts[spot].getChildren().clear();
        VBox[][] toReturn = new VBox[x][];
        for(int i = 0; i < x; i++){
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            toReturn[i] = new VBox[y];
            for(int j = 0; j < y; j++){
                VBox pixel = new VBox();
                pixel.setPrefSize(size, size);
                pixel.setMaxSize(size, size);
                box.getChildren().add(pixel);
                toReturn[i][j] = pixel;
            }
            nexts[spot].getChildren().add(box);
        }
        return toReturn;
    }



    private void drawScreen(){
        window.getChildren().clear();
        FlowPane box = new FlowPane();
        pixels.clear();
        pixels = new ArrayList<>();
        window.getChildren().add(box);
        int n = 0, m = 0;
        for(n = 0; n < window.getHeight()/40-1; n++){
            for(m = 0;m <  window.getWidth()/40-1; m++){
                VBox pixel = new VBox();
                pixel.setPrefSize(40, 40);
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
    public void drawAt(int color, int x, int y){
        VBox pixel =  pixels.get(xyToIndex(x, y));
        switch (color){
            case 1:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #146486"); //2x2
                break;
            case 2:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #238614"); //Hinge left
                break;
            case 3:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #861414"); //Hinge right
                break;
            case 4:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #cbb20c"); //Small T
                break;
            case 5:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #d37f28"); //4x1
                break;
            case -1:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #eeeeee");
                break;
            case 0:
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #303030");
                break;
            default:
                System.out.println("Dont know color at [ " + x + " , " + y + " ] replacing with background color");
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #303030");
                break;
        }
    }

    public int getWidth() {
        return width;
    }
    public void setScoreText(String score){
        scoreText.setText(score);
    }
    public int getHeight() {
        return height;
    }
}
