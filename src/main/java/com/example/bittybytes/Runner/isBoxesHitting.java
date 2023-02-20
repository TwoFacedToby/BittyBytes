package com.example.bittybytes.Runner;

import javafx.scene.layout.VBox;

public class isBoxesHitting {
    public boolean isHitting(VBox a, VBox b){
        double aX = a.getLayoutX();
        double aY = a.getLayoutY();
        double bX = b.getLayoutX();
        double bY = b.getLayoutY();
        double aW = a.getWidth()/2;
        double aH = a.getHeight()/2;
        double bW = b.getWidth()/2;
        double bH = b.getHeight()/2;

        boolean xWithin = false;
        boolean yWithin = false;

        if(aX-aW > bX-bW && aX-aW < bX+bW){ //is a's left wall between b's left and right wall
            xWithin = true;
        }
        else if(aX+aW > bX-bW && aX+aW < bX+bW){ //is a's right wall between b's left and right wall
            xWithin = true;
        }
        if(aY-aH > bY-bH && aY-aH < bY+bH){ //is a's bottom wall between b's top and bottom wall
            yWithin = true;
        }
        else if(aY+aH > bY-bH && aY+aH < bY+bH){ //is a's top wall between b's top and bottom wall
            yWithin = true;
        }
        if(xWithin && yWithin) return true;
        else return false;
    }
}
