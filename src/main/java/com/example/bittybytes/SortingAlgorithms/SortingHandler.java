package com.example.bittybytes.SortingAlgorithms;

import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SortingHandler {

    private double speed = 0;
    private double operations = 0;
    private int arraySize = 0;
    private Update updater = new Update(this);
    public void initiate(){
        System.out.println("Check");
    }








    public void pressed(String algorithm){
        switch (algorithm){
            case "Bubble":
                changeAlgorithmOnScreen("Bubble Sort");
                break;
            case "Selection":
            case "Insertion":
            case "Merge":
            case "Quick":
            case "Counting":
            case "Radix":
            case "Bucket":
            case "Heap":
            case "Shell":
            default:
                System.out.println(algorithm + " sort is not yet coded");
                break;
        }
    }
    private void changeAlgorithmOnScreen(String text){
        SceneManager.get().sortingController.setAlgorithm(text);
    }
    public void setSpeed(double speed){
        this.speed = speed;
        updater.stopUpdate();
        updater.setUpdateSpeed(speed);
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
        clear();
    }

    public void run(){
        updater.setUpdateSpeed(speed);
    }
    public void update(){

    }
    private void clear(){
        updater.stopUpdate();
        SceneManager.get().sortingController.clear();
    }
    public void input(KeyEvent input){
        if(input.getCode()== KeyCode.ESCAPE)
        {
            SceneManager.get().switchScene("menu");
            return;
        }
        if(input.getCode()== KeyCode.ENTER)
        {
            run();
            return;
        }
    }
}
