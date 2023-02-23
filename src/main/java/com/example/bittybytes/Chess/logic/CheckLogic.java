package com.example.bittybytes.Chess.logic;

import com.example.bittybytes.Chess.pieces.Piece;
import com.example.bittybytes.Chess.pieces.Pieces;

import java.util.ArrayList;

public class CheckLogic {
    ArrayList<Piece> pieces;

    //Returns true if moving to this spot, doesn't cause any checks for opposite team.
    public boolean noChecksForSamePayerAt(int[] futureMove, Piece current){
        int[] savePos = current.getPosition();
        boolean turn = current.getColor();
        boolean toReturn = true;
        pieces = Pieces.getInstance().getPieces();
        current.setPosition(futureMove);
        Piece sameKing = null;
        Piece otherKing = null;
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getType().equals("King")){
                if(pieces.get(i).getColor() == turn) sameKing = pieces.get(i);
                else otherKing = pieces.get(i);
            }
        }
        if(sameKing != null && otherKing != null){
            if(checkForKing(sameKing)){
                //Can't do the move then
                toReturn = false;
            }
            if(checkForKing(otherKing)){
                //Can do the move, and warn the opposite Player


            }
        }






        current.setPosition(savePos);
        return toReturn;
    }

    private boolean checkForKing(Piece king){
        boolean toReturn = false;


        return toReturn;
    }
}
