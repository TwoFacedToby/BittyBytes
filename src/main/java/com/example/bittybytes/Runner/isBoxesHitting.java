package com.example.bittybytes.Runner;

import javafx.scene.layout.VBox;

public class isBoxesHitting {
    public boolean isHitting(VBox a, VBox b){
        double aX = a.getLayoutX();
        double aY = a.getLayoutY();
        double bX = a.getLayoutX();
        double bY = a.getLayoutY();
        double aW = a.getWidth();
        double aH = a.getHeight();
        double bW = b.getWidth();
        double bH = b.getHeight();

        double[] aWalls = new double[4];
        double[] bWalls = new double[4];

        //Anchorpoint at top left corner
        aWalls[0] = aX-(aW/2);  //left
        aWalls[1] = aX+(aW/2);  //Right
        aWalls[2] = aY-(aH/2);  //Top
        aWalls[3] = aY+(aH/2);  //Bottom
        bWalls[0] = bX-(bW/2);  //left
        bWalls[1] = bX+(bW/2);  //Right
        bWalls[2] = bY-(bH/2);  //Top
        bWalls[3] = bY+(bH/2);  //Bottom

        boolean xWithin = false;
        boolean yWithin = false;

        if(aWalls[0] > bWalls[0] && bWalls[0] > aWalls[1]){ //B's left wall is within A's walls with the x position.
            xWithin = true;
        }
        if(aWalls[0] > bWalls[1] && bWalls[1] > aWalls[1]){ //B's right wall is within A's walls with the x position.
            xWithin = true;
        }
        if(aWalls[2] > bWalls[0] && bWalls[2] > aWalls[3]){ //B's Top wall is within A's walls with the y position.
            yWithin = true;
        }
        if(aWalls[2] > bWalls[1] && bWalls[3] > aWalls[3]){ //B's Bottom wall is within A's walls with the y position.
            yWithin = true;
        }
        if(xWithin && yWithin) return true;
        else return false;
    }
}
