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
    @FXML
    Button start;
    @FXML
    Button end;
    @FXML
    Button coin;
    @FXML
    Button solve;


    ArrayList<Button> pixels = new ArrayList<>();
    int width;
    int height;
    String clickType = "start";
    int startX = 1;
    int startY = 1;
    Button startB;
    Button endB;
    String oldStyleStart;
    String oldStyleEnd;
    int endX = 1;
    int endY = 1;
    double pixelSize = 10;
    int[] sizes;
    int unEven = 0;
    ArrayList<int[]> coins = new ArrayList<>();
    int currentStartOrEnd = 0;
    private boolean mazeFinished;

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
        run.setOnAction(e -> run());
        instant.setOnAction(e -> makeInstant(true));
        visual.setOnAction(e -> makeInstant(false));
        reset.setOnAction(e -> SceneManager.get().getMazeHandler().reset());
        minus.setOnAction(e -> changeSize(false));
        plus.setOnAction(e -> changeSize(true));
        start.setOnAction(e -> startOrEnd(0));
        end.setOnAction(e -> startOrEnd(1));
        coin.setOnAction(e -> startOrEnd(2));
        solve.setOnAction(e -> solvePressed());
    }
    private void solvePressed(){
        System.out.println("Solve Pressed");
        if(startX != endX || startY != endY){
            SceneManager.get().getMazeHandler().solve();
        }
    }
    private void run(){
        SceneManager.get().getMazeHandler().run(startX, startY);
        System.out.println("Run Pressed");
    }
    private void startOrEnd(int type){
        currentStartOrEnd = type;
        if(type == 0){
            start.setStyle(start.getStyle() + "; -fx-text-fill: #eeeeee");
            end.setStyle(end.getStyle() + "; -fx-text-fill: #909090");
            coin.setStyle(coin.getStyle() + "; -fx-text-fill: #909090");
        }
        else if(type == 1){
            end.setStyle(end.getStyle() + "; -fx-text-fill: #eeeeee");
            start.setStyle(start.getStyle() + "; -fx-text-fill: #909090");
            coin.setStyle(coin.getStyle() + "; -fx-text-fill: #909090");
        }else if(type == 2){
            end.setStyle(end.getStyle() + "; -fx-text-fill: #909090");
            start.setStyle(start.getStyle() + "; -fx-text-fill: #909090");
            coin.setStyle(coin.getStyle() + "; -fx-text-fill: #eeeeee");
        }
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


        if(x% 2 == 0) x++;
        if(y% 2 == 0) y++;

        if(x < 1) x+=2;
        if(x > width-2) x-=2;
        if(y < 1) y+=2;
        if(y > height-2) y-=2;
        Button b = pixels.get(xyToIndex(x, y));
        if(currentStartOrEnd == 0 && !mazeFinished){
            if(startB != null) startB.setStyle(oldStyleStart);
            startX = x;
            startY = y;
            oldStyleStart = b.getStyle();
            startB = b;
            drawAt(x, y, "start");
        }
        else if(currentStartOrEnd == 1 && mazeFinished){
            if(endB != null) endB.setStyle(oldStyleEnd);
            endX = x;
            endY = y;
            oldStyleEnd = b.getStyle();
            endB = b;
            drawAt(x, y, "end");
            SceneManager.get().getMazeHandler().setEnd(x, y);
        }else if(currentStartOrEnd == 2 && mazeFinished){
            int[] pos = new int[2];
            pos[0] = x;
            pos[1] = y;
            if(coins.size() > 0){
                boolean contains = false;
                for(int i = 0; i < coins.size(); i++){
                    if(pos[0] == coins.get(i)[0]){
                        if(pos[1] == coins.get(i)[1]){
                            contains = true;
                            coins.remove(i);
                        }
                    }
                }
                if(contains){
                    drawAt(x, y, "path");
                }
                else{
                    drawAt(x, y, "coin");
                    coins.add(pos);
                }
            }
            else{
                drawAt(x, y, "coin");
                coins.add(pos);
            }
            System.out.println("Coins: " + coins.size());


        }
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
            case "visited":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #ee2e50"); //Visited Path
                break;
            case "complete":
                pixel.setStyle(pixel.getStyle() + "; -fx-background-color: #df18f5"); //Visited Path
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
    public void setMazeFinished(boolean mazeFinished) {
        this.mazeFinished = mazeFinished;
    }
}
