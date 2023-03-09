package com.example.bittybytes.Tetris;

import com.example.bittybytes.SceneManager;

import java.util.Random;

public class NextSpawn {

    int[] next = new int[3];

    public NextSpawn(){
        next[0] = getNextRandom();
        next[1] = getNextRandom();
        next[2] = getNextRandom();
        updateNextOnScreen();
    }

    public int getNext(){ //Takes the next element, but still keeps the rest.
        int temp = next[0];
        next[0] = next[1];
        next[1] = next[2];
        next[2] = getNextRandom();
        updateNextOnScreen();
        return temp;
    }
    private void updateNextOnScreen(){
        SceneManager.get().tetrisController.drawNext(next);
    }

    private int getNextRandom(){
        Random rnd = new Random();
        return rnd.nextInt(0, 5);
    }
}
