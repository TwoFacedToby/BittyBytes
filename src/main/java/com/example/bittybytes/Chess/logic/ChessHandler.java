package com.example.bittybytes.Chess.logic;


import com.example.bittybytes.Controllers.ChessController;

public class ChessHandler extends HighlightLogic{

    private static ChessHandler controller;
    private ChessController viewController;
    private CheckLogic checkLogic = new CheckLogic();

    public static ChessHandler get(){
        if(controller == null) controller = new ChessHandler();
        return controller;
    }

    public void setController(ChessController viewController){
        this.viewController = viewController;
    }
    public ChessController getViewController(){
        return viewController;
    }
    public CheckLogic getCheckLogic(){
        return  checkLogic;
    }


}
