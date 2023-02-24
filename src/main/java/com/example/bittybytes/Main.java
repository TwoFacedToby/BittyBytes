package com.example.bittybytes;

import com.example.bittybytes.SortingAlgorithms.Algorithms;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.get().start(stage);
    }

    public static void main(String[] args) {
        //test(); // <------- Comment out test
        launch();
    }
    static private void test(){
        ArrayList<Integer> list = new ArrayList<>();
        Algorithms algorithms = new Algorithms();
        for(int i = 1; i <= 10; i++){ //array size <------
            list.add(i);
        }
        print(list);
        algorithms.randomize(list, 3);
        print(list);

        ArrayList<ArrayList<Integer>> all = algorithms.heap(list);   //Algorithm type <------

        for(int i = 0; i < all.size(); i++){
            print(all.get(i));
        }
    }
    static void print(ArrayList<Integer> list){
        System.out.println("");
        System.out.print("[");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i));
            if(i < list.size()-1) System.out.print(", ");
        }
        System.out.print("]");
        System.out.println("");
    }
}