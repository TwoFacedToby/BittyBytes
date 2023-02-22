package com.example.bittybytes.SortingAlgorithms;

import com.example.bittybytes.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class SortingHandler {

    private double speed = 1;
    private double operations = 0;
    private int step = 0;
    private int arraySize = 0;
    private String type;
    private Update updater = new Update(this);
    private Algorithms algorithms = new Algorithms();
    private ArrayList<Integer> list = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> all = new ArrayList<>();
    public void initiate(){

    }








    public void pressed(String algorithm){
        switch (algorithm){
            case "Bubble":
                type = "bubble";
                changeAlgorithmOnScreen("Bubble Sort");
                break;
            case "Selection":
                type = "selection";
                changeAlgorithmOnScreen("Selection Sort");
                break;
            case "Insertion":
                type = "insertion";
                changeAlgorithmOnScreen("Insertion Sort");
                break;
            case "Merge":
                type = "merge";
                changeAlgorithmOnScreen("Merge Sort");
                break;
            case "Quick":
                type = "quick";
                changeAlgorithmOnScreen("Quicksort");
                break;
            case "Counting":
                type = "count";
                changeAlgorithmOnScreen("Counting Sort");
                break;
            case "Radix":
                type = "radix";
                changeAlgorithmOnScreen("Radix Sort");
                break;
            case "Bucket":
                type = "bucket";
                changeAlgorithmOnScreen("Bucket Sort");
                break;
            case "Heap":
                type = "heap";
                changeAlgorithmOnScreen("Heap Sort");
                break;
            case "Shell":
                type = "shell";
                changeAlgorithmOnScreen("Shell Sort");
                break;
            default:
                System.out.println(algorithm + " sort is not part of this application");
                break;
        }
        clear();
    }
    private void changeAlgorithmOnScreen(String text){
        SceneManager.get().sortingController.setAlgorithm(text);
    }
    public void setSpeed(double speed){
        this.speed = speed;
        if(updater.isRunning()){
            updater.stopUpdate();
            updater.setUpdateSpeed(speed);
        }
        else {
            updater.setUpdateSpeed(speed);
            updater.stopUpdate();
        }

    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
        fillList();
        randomize();
        clear();
        SceneManager.get().sortingController.draw(list);
    }

    public void run(){
        fillList();
        randomize();
        clear();
        SceneManager.get().sortingController.draw(list);
        if(type == null || type.equals("")) return;
        step = 0;
        all = algorithms.getSorted(list, type);
        updater.setUpdateSpeed(speed);
    }
    public void update(){
        if(step < all.size()){
            SceneManager.get().sortingController.draw(all.get(step));
            SceneManager.get().sortingController.setOperations(algorithms.getOperations()+"");
            step++;
        }
        else{
            stopUpdate();
        }
    }
    public void stopUpdate(){
        updater.stopUpdate();
    }
    private void clear(){
        updater.stopUpdate();
        SceneManager.get().sortingController.clear();
        algorithms.setOperations(0);
        SceneManager.get().sortingController.setOperations(0+"");
    }
    private void fillList(){
        list.clear();
        for(int i = 1; i <= arraySize; i++){
            list.add(i);
        }
    }
    private void randomize(){
        algorithms.randomize(list, 3);
    }
    public void input(KeyEvent input){
        if(input.getCode()== KeyCode.ESCAPE)
        {
            clear();
            SceneManager.get().switchScene("menu");
            return;
        }
    }

}
