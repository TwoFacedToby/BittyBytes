package com.example.bittybytes.SortingAlgorithms;

import java.util.ArrayList;
import java.util.Random;

public class Algorithms {

    ArrayList<ArrayList<Integer>> step = new ArrayList<>();
    private int operations;

    public ArrayList<ArrayList<Integer>> getSorted(ArrayList<Integer> current, String type){
        switch (type){
            case "merge":
                return merge(current);
            case "bubble":
                return bubble(current);
            case "selection":
            case "insertion":
            case "quick":
            case "count":
            case "radix":
            case "bucket":
            case "heap":
            case "shell":
            default:
                System.out.println(type + " does not exist");
                break;
        }
        return null;
    }
    public int getOperations(){
        return operations;
    }
    public void setOperations(int operations){
        this.operations = operations;
    }
    public boolean control(ArrayList<Integer> toControl){
        for(int i = 0; i < toControl.size(); i++){
            if(i < toControl.size()-1 && toControl.get(i) > toControl.get(i+1)){
                return false;
            }
        }
        return true;
    }
    public ArrayList<Integer> randomize(ArrayList<Integer> toRandomize, int shuffles) {
        Random rnd = new Random();
        for(int i = 0; i < shuffles; i++){
            for(int n = 0; n < toRandomize.size(); n++){
                int temp = toRandomize.get(n);
                int random = rnd.nextInt(0, toRandomize.size());
                toRandomize.set(n, toRandomize.get(random));
                toRandomize.set(random, temp);
            }
        }
        return toRandomize;
    }
    private void insert(ArrayList<Integer> A){
        ArrayList<Integer> arr = step.get(step.size()-1);
        ArrayList<Integer> whole = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            whole.add(arr.get(i));
        }
        int start = -1;
        for(int i = 0; i < whole.size(); i++){
            if(start != -1) break;
            for(int j = 0; j < A.size(); j++){
                if(whole.get(i) == A.get(j)){
                    start = i;
                    break;
                }
            }
        }

        for(int i = start; i < start+A.size() && i < whole.size(); i++){
            whole.set(i, A.get(i-start));
        }
        step.add(whole);
    }
    private void print(ArrayList<Integer> list){
        System.out.print("[");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + "");
            if(i < list.size()-1) System.out.print(", ");
        }
        System.out.print("]");
    }
    private ArrayList<Integer> copy(ArrayList<Integer> list){
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            arr.add(list.get(i));
        }
        return arr;
    }

    //region bubble

    private ArrayList<ArrayList<Integer>> bubble(ArrayList<Integer> toSort){
        step.clear();
        step.add(toSort);
        BUBBLE_LOOP(toSort);
        return step;
    }
    private void BUBBLE_LOOP(ArrayList<Integer> toSort){
        int count = 0;
        while(!control(toSort) || count < 1000){
            toSort = BUBBLE_SORT(toSort);
            step.add(copy(toSort));
            count++;
        }
    }
    private ArrayList<Integer> BUBBLE_SORT(ArrayList<Integer> toSort){
        for(int i = 1; i < toSort.size()-1; i++){
            if(toSort.get(i-1) < toSort.get(i)){
                int temp = toSort.get(i-1);
                toSort.set(i-1, toSort.get(i));
                toSort.set(i, temp);
            }
            if(toSort.get(i) < toSort.get(i+1)){
                int temp = toSort.get(i);
                toSort.set(i, toSort.get(i+1));
                toSort.set(i+1, temp);
            }
        }
        return toSort;
    }
    //endregion

    //region merge

    public ArrayList<ArrayList<Integer>> merge(ArrayList<Integer> toSort){
        step.clear();
        step.add(toSort);
        MERGE_SORT(toSort, toSort.size());
        return step;
    }
    private void MERGE_SORT(ArrayList<Integer> a, int length){
        ArrayList<Integer> A = MERGE_RECURSION(a);
        step.add(A);

    }
    private ArrayList<Integer> MERGE_RECURSION(ArrayList<Integer> a){
        if(a.size() < 2) return a;
        int r = a.size();
        int m = Math.floorDiv(r,2);

        ArrayList<Integer> A1 = new ArrayList<>();
        ArrayList<Integer> A2 = new ArrayList<>();
        for(int i = 0; i < m; i++){
            A1.add(a.get(i));
        }
        for(int i = m; i < r; i++){
            A2.add(a.get(i));
        }
        A1 = MERGE_RECURSION(A1);
        A2 = MERGE_RECURSION(A2);
        ArrayList<Integer> A = MERGE_SORTED(A1, A2);
        insert(A);
        return A;
    }

    private ArrayList<Integer> MERGE_SORTED(ArrayList<Integer> A1, ArrayList<Integer> A2){
        int n = 0, m = 0, i;
        ArrayList<Integer> A = new ArrayList<>();

        for(i = 0; i < A1.size()+A2.size(); i++){
            if(n >= A1.size()){ //Reached end of A1
                A.add(i, A2.get(m));
                m++;
            }
            else if(m >= A2.size()){ //Reached end of A2
                A.add(i, A1.get(n));
                n++;
            }
            else if(A1.get(n) <= A2.get(m)){
                A.add(i, A1.get(n));
                n++;
            }
            else{
                A.add(i, A2.get(m));
                m++;
            }
        }
        return A;
    }
    //endregion

}
