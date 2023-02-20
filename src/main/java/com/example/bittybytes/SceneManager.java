package com.example.bittybytes;

import com.example.bittybytes.Controllers.LabyrinthController;
import com.example.bittybytes.Labyrinth.LabyrinthHandler;
import com.example.bittybytes.Controllers.MenuController;
import com.example.bittybytes.Controllers.RunnerController;
import com.example.bittybytes.Runner.RunnerHandler;
import com.example.bittybytes.Controllers.SnakeController;
import com.example.bittybytes.Snake.SnakeHandler;
import com.example.bittybytes.Controllers.SortingController;
import com.example.bittybytes.SortingAlgorithms.SortingHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SceneManager {

    private static SceneManager sceneManager;
    private FXMLLoader fxmlLoader;
    private AnchorPane menu;
    private AnchorPane snake;
    private AnchorPane runner;
    private AnchorPane maze;
    private AnchorPane sort;
    public MenuController menuController;
    public SnakeController snakeController;
    public RunnerController runnerController;
    public LabyrinthController mazeController;
    public SortingController sortingController;



    private SnakeHandler snakeHandler;
    private RunnerHandler runnerHandler;
    private LabyrinthHandler mazeHandler;
    private SortingHandler sortingHandler;

    private Stage stage;
    ArrayList<String> sceneNames = new ArrayList<>();
    ArrayList<Scene> scenes = new ArrayList<>();

    public static SceneManager get(){
        if(sceneManager == null) sceneManager = new SceneManager();
        return sceneManager;
    }

    public void start(Stage s) throws IOException {
        stage = s;
        loadScenes();
        switchScene ("menu");
    }
    private void loadScenes() throws IOException {
        //try{
            fxmlLoader = new FXMLLoader(SceneManager.class.getResource("menu.fxml"));
            menu = fxmlLoader.load();
            scenes.add(new Scene(menu, 600, 400));
            menuController = fxmlLoader.getController();
            sceneNames.add("menu");
            fxmlLoader = new FXMLLoader(SceneManager.class.getResource("snakeView.fxml"));
            snake = fxmlLoader.load();
            scenes.add(new Scene(snake, 1200, 800));
            snakeController = fxmlLoader.getController();
            sceneNames.add("Snake");
            fxmlLoader = new FXMLLoader(SceneManager.class.getResource("runnerView.fxml"));
            runner = fxmlLoader.load();
            scenes.add(new Scene(runner, 1200, 800));
            runnerController = fxmlLoader.getController();
            sceneNames.add("Runner");
            fxmlLoader = new FXMLLoader(SceneManager.class.getResource("sortingView.fxml"));
            sort = fxmlLoader.load();
            scenes.add(new Scene(sort, 1200, 800));
            sortingController = fxmlLoader.getController();
            sceneNames.add("Sorting");
            fxmlLoader = new FXMLLoader(SceneManager.class.getResource("labyrinth.fxml"));
            maze = fxmlLoader.load();
            scenes.add(new Scene(maze, 1200, 800));
            mazeController = fxmlLoader.getController();
            sceneNames.add("Maze");

        /*

        }catch (Exception e){
            System.out.println("Cannot load scenes");
        }*/
    }
    public ArrayList<String> getSceneNames(){
        return sceneNames;
    }
    public void switchScene(String scene){
        if(!sceneNames.contains(scene)){
            System.out.println("Cannot switch scene. Scene: " + scene + " does not exist");
            return;
        }
        stage.close();
        switch (scene){
            case "menu":
                toMenu();
                break;
            case "Snake":
                toSnake();
                break;
            case "Runner":
                toRunner();
                break;
            case "Sorting":
                toSorting();
                break;
            case "Maze":
                toMaze();
                break;
            default:
                System.out.println("Cannot switch to scene, not loaded in arrayList. Switching to Main Menu instead");
                toMenu();
                break;
        }
    }

    private void toMenu(){
        stage.setTitle("Main Menu");
        stage.setScene(scenes.get(0));
        stage.show();
        menuController.initButtons();
    }
    private void toSnake(){
        stage.setTitle("Snake");
        stage.setScene(scenes.get(1));
        stage.show();
        snakeController.initBoard();
        snakeHandler = new SnakeHandler();
        snakeHandler.initiate();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {
            @Override
            public void handle(KeyEvent t) {
                snakeHandler.input(t);
            }
        });
    }
    private void toRunner(){
        stage.setTitle("Runner");
        stage.setScene(scenes.get(2));
        stage.show();
        runnerController.initBoard();
        runnerHandler = new RunnerHandler();
        runnerHandler.initiate();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {
            @Override
            public void handle(KeyEvent t) {
                runnerHandler.input(t);
            }
        });
    }
    private void toSorting(){
        stage.setTitle("Sorting Algorithms");
        stage.setScene(scenes.get(3));
        stage.show();
        sortingController.initBoard();
        sortingHandler = new SortingHandler();
        sortingHandler.initiate();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {
            @Override
            public void handle(KeyEvent t) {
                sortingHandler.input(t);
            }
        });
    }
    private void toMaze(){
        stage.setTitle("Maze");
        stage.setScene(scenes.get(4));
        stage.show();
        mazeController.initBoard();
        mazeHandler = new LabyrinthHandler();
        mazeHandler.initiate();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {
            @Override
            public void handle(KeyEvent t) {
                mazeHandler.input(t);
            }
        });
    }




    public SnakeHandler getSnakeHandler() {
        return snakeHandler;
    }
    public RunnerHandler getRunnerHandler() {
        return runnerHandler;
    }
    public SortingHandler getSortingHandler(){
        return sortingHandler;
    }

}
