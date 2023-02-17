package com.example.bittybytes.Runner;

public class CalculateJumpHeight {

    private double jumpLength = 1;

    public double getY(double x){
        return (-0.01*jumpLength*(Math.pow(x, 2))+100);
    }


}
