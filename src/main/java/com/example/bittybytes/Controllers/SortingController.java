package com.example.bittybytes.Controllers;

import com.example.bittybytes.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class SortingController {


    @FXML
    AnchorPane window;
    @FXML
    Label operations;
    @FXML
    Label operationSpeed;
    @FXML
    Label worst;
    @FXML
    Label average;
    @FXML
    Label best;
    @FXML
    Label algorithm;

    @FXML
    TextField arraySize;
    @FXML
    Slider slider;
    @FXML
    Button bubble;
    @FXML
    Button selection;
    @FXML
    Button insertion;
    @FXML
    Button merge;
    @FXML
    Button quick;
    @FXML
    Button count;
    @FXML
    Button radix;
    @FXML
    Button bucket;
    @FXML
    Button heap;
    @FXML
    Button shell;
    @FXML
    Button run;

    Button current;

    ArrayList<Button> buttons;

    public void initBoard(){
        System.out.println("init board");
        buttons = new ArrayList<>();
        buttons.add(bubble);
        buttons.add(selection);
        buttons.add(insertion);
        buttons.add(merge);
        buttons.add(quick);
        buttons.add(count);
        buttons.add(radix);
        buttons.add(bucket);
        buttons.add(heap);
        buttons.add(shell);
        for(int i = 0; i < buttons.size(); i++){
            Button b = buttons.get(i);
            b.setOnAction(e -> pressed(b));
            b.setOnMouseEntered(e -> mouseOver(true, b));
            b.setOnMouseExited(e -> mouseOver(false, b));
            mouseOver(false, b);
        }
        run.setOnAction(e -> runSort());
        run.setOnMouseEntered(e -> mouseOver(true, run));
        run.setOnMouseExited(e -> mouseOver(false, run));
        mouseOver(false, run);


        ArrayList<Integer> test = new ArrayList<>();

        for(int i = 1; i < 4000; i++){
            test.add(i);
        }
        draw(test);
    }

    private void pressed(Button b){
        setChosen(b);
        SceneManager.get().getSortingHandler().pressed(b.getText());
    }
    private void setChosen(Button b){
        if(current != b){
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).setStyle(buttons.get(i).getStyle() + "; -fx-border-color: #eeeeee");
            }
        }
        current = b;
        mouseOver(false, b);
    }
    public void setAlgorithm(String s){
        algorithm.setText(s);
    }

    private void mouseOver(boolean isOver, Button b){
        if(b == current){
            if(isOver) b.setStyle(b.getStyle() + "; -fx-border-color: #309030");
            else b.setStyle(b.getStyle() + "; -fx-border-color: #309030");
            return;
        }
        if(isOver) b.setStyle(b.getStyle() + "; -fx-border-color: #309030");
        else b.setStyle(b.getStyle() + "; -fx-border-color: #eeeeee");


    }
    @FXML
    public void setSpeed(){
        double value = Math.floor(slider.getValue());
        SceneManager.get().getSortingHandler().setSpeed(value);
        operationSpeed.setText(value+"%");
    }
    @FXML
    public void setArraySize(){
        int size = parseInt(arraySize.getText());
        SceneManager.get().getSortingHandler().setArraySize(size);
    }
    public void setOperations(String s){
        operations.setText(s);
    }
    private void runSort(){
        SceneManager.get().getSortingHandler().run();
    }
    public void clear(){

    }
    public void draw(ArrayList<Integer> toDraw){
        window.getChildren().clear();
        System.out.println(window.getWidth() + "  " + window.getHeight());
        double partHeight = window.getHeight()/(double)toDraw.size();
        double partWidth = window.getWidth()/(double)toDraw.size();
        System.out.println(partWidth + "  " + partHeight);
        for(int i = 0; i < toDraw.size(); i++){
            VBox vBox = new VBox();
            vBox.setStyle("-fx-background-color: #eeeeee");
            vBox.setMinHeight(1);
            vBox.setMaxHeight(1);
            vBox.setMinWidth(1);
            vBox.setMaxWidth(1);
            vBox.setScaleX(partWidth);
            vBox.setScaleY(partHeight*toDraw.get(i));
            vBox.setLayoutY((partHeight*toDraw.get(i)/-2)+window.getHeight());
            vBox.setLayoutX(partWidth*(i)+partWidth*0.5);
            /*
            vBox.setMaxWidth(partWidth);
            vBox.setPrefWidth(partWidth);
            vBox.setMaxHeight(toDraw.get(i)*partHeight);
            vBox.setMinHeight(toDraw.get(i)*partHeight);
            */

            window.getChildren().add(vBox);
        }
    }

}
