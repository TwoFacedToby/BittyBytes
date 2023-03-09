package com.example.bittybytes.Tetris;

import java.util.ArrayList;

public class MovementLogic {


    TetrisHandler handler;
    int[][] screen;
    ArrayList<Position> positions = new ArrayList<>();
    int currentColor = 0;

    public MovementLogic(TetrisHandler tetris){
        handler = tetris;
        getScreen();
    }

    private void setAllOneDown(){
        for(int i = 0; i < positions.size(); i++){
            Position pos = positions.get(i);

            if(pos.y()+1 < screen[0].length){
                screen[pos.x()][pos.y()] = 0;
                pos.setPos(pos.x(), pos.y()+1);
                screen[pos.x()][pos.y()] = currentColor;
            }
        }
        send();
    }
    private void setAllToSide(int side){ //-1 for left 1 for right
        if(side == 1){
            for(int i = 0; i < positions.size(); i++){
                Position pos = positions.get(i);
                if(pos.x()+1 < screen.length){
                    screen[pos.x()][pos.y()] = 0;
                    pos.setPos(pos.x()+1, pos.y());
                    screen[pos.x()][pos.y()] = currentColor;
                }
            }
        }
        else if(side == -1){
            for(int i = positions.size()-1; i >= 0 ; i--){
                Position pos = positions.get(i);
                if(pos.x()>0){
                    screen[pos.x()][pos.y()] = 0;
                    pos.setPos(pos.x()-1, pos.y());
                    screen[pos.x()][pos.y()] = currentColor;
                }
            }
        }
        send();
    }
    private boolean canMoveToSide(int side){//-1 for left 1 for right
        boolean free = true;
        for(int i = 0; i < positions.size(); i++){
            Position pos = positions.get(i);
            if(side == 1){
                if(pos.x()+1 < screen.length)
                {
                    if(!(screen[pos.x()+1][pos.y()] == 0)){ //Is the spot to the right not free
                        boolean isPart = false;
                        //Could be part of itself
                        for(int j = 0; j < positions.size(); j++){
                            Position pos2 = positions.get(j);
                            if(pos2.y() == pos.y() && pos2.x() == pos.x()+1){ //Is it part of itself
                                isPart = true;
                                break;
                            }
                        }
                        if(!isPart){
                            free = false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            else if(side == -1){
                if(pos.x() > 0)
                {
                    if(!(screen[pos.x()-1][pos.y()] == 0)){ //Is the spot to the right not free
                        boolean isPart = false;
                        //Could be part of itself
                        for(int j = 0; j < positions.size(); j++){
                            Position pos2 = positions.get(j);
                            if(pos2.y() == pos.y() && pos2.x() == pos.x()-1){ //Is it part of itself
                                isPart = true;
                                break;
                            }
                        }
                        if(!isPart){
                            free = false;
                        }
                    }
                }
                else{
                    return false;
                }
            }

        }
        return free;
    }
    private boolean checkAllBelow(){
        boolean free = true;
        for(int i = 0; i < positions.size(); i++){
            Position pos = positions.get(i);
            if(pos.y()+1 < screen[0].length)
            {
                if(!(screen[pos.x()][pos.y()+1] == 0)){ //Is the spot below is not free
                    boolean isPart = false;
                    //Could be part of itself
                    for(int j = 0; j < positions.size(); j++){
                        Position pos2 = positions.get(j);
                        if(pos2.y() == pos.y()+1 && pos2.x() == pos.x()){ //Is it part of itself
                            isPart = true;
                            break;
                        }
                    }
                    if(!isPart){
                        free = false;
                    }
                }
            }
            else{
                return false;
            }
        }
        return free;
    }
    public void next(int type){
        positions.clear();
        switch (type){
            case 0: //2x2
                positions.add(new Position(4, 1));
                positions.add(new Position(5, 1));
                positions.add(new Position(4, 0));
                positions.add(new Position(5, 0));
                currentColor = 1;
                break;
            case 1: //T
                positions.add(new Position(4, 1));
                positions.add(new Position(5, 1));
                positions.add(new Position(6, 1));
                positions.add(new Position(5, 0));
                currentColor = 4;
                break;
            case 2: //1x4
                positions.add(new Position(3, 0));
                positions.add(new Position(4, 0));
                positions.add(new Position(5, 0));
                positions.add(new Position(6, 0));
                currentColor = 5;
                break;
            case 3: //Hinge Right
                positions.add(new Position(6, 1));
                positions.add(new Position(4, 0));
                positions.add(new Position(5, 0));
                positions.add(new Position(6, 0));
                currentColor = 2;
                break;
            case 4: //Hinge Left
                positions.add(new Position(4, 1));
                positions.add(new Position(4, 0));
                positions.add(new Position(5, 0));
                positions.add(new Position(6, 0));
                currentColor = 3;
                break;
        }


        //send();
        //0 = 2x2 - 1
        //1 = T - 4
        //2 = 1x4 - 5
        //3 = Hinge Right - 2
        //4 = Hinge Left - 3
    }
    public void moveRight(){
        if(canMoveToSide(1)){
            setAllToSide(1);
        }
    }
    public void moveLeft(){
        if(canMoveToSide(-1)){
            setAllToSide(-1);
        }
    }

    public void turnRight(){

    }
    public void turnLeft(){

    }
    public boolean goDown(){
        if(checkAllBelow()){
            setAllOneDown();
            return true;
        }
        else{
            return false;
        }

    }
    public void instantDown(){

    }
    public void send(){
        handler.setScreen(screen);
        handler.drawScreen();
    }
    private void getScreen(){
        screen = handler.getScreen();
    }
}
