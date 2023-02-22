package com.example.bittybytes.Controllers;

import com.example.bittybytes.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class MenuController {
    @FXML
    private Label welcomeText;
    @FXML
    private FlowPane flowPaneButtons;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){

    }

    public void initButtons(){
        flowPaneButtons.getChildren().clear();
        ArrayList<String> scenes = SceneManager.get().getSceneNames();
        for(int i = 0; i < scenes.size(); i++){
            if(!scenes.get(i).equals("menu")){
                Button b = new Button();
                b.setOnAction(e -> buttonPressed(b));
                b.setText(scenes.get(i));
                b.setStyle("-fx-text-fill: #cccccc; -fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #404040");
                b.setWrapText(true);
                b.setTextAlignment(TextAlignment.CENTER);
                b.setPrefSize(80, 80);
                b.setOnMouseEntered(e -> onMouse(b,true));
                b.setOnMouseExited(e -> onMouse(b,false));
                flowPaneButtons.getChildren().add(b);

            }
        }
    }
    private void buttonPressed(Button b){
        SceneManager.get().switchScene(b.getText());
    }
    private void onMouse(Button b, boolean isOver){
        if(isOver){
            b.setStyle(b.getStyle() + "; -fx-border-color: #60da60");
        }
        else{
            b.setStyle(b.getStyle() + "; -fx-border-color: #cccccc");
        }

    }
}