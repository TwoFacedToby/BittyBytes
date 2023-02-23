package com.example.bittybytes.SortingAlgorithms;

import java.util.ArrayList;
import java.util.Random;

public class Algorithms {

    ArrayList<ArrayList<Integer>> step = new ArrayList<>();
    private int operations;

    public ArrayList<ArrayList<Integer>> getSorted(ArrayList<Integer> current, String type){
        operations = 0;
        step.clear();
        switch (type){
            case "merge":
                step.add(copy(current));
                return merge(current);
            case "bubble":
                return bubble(current);
            case "selection":
                return selection(current);
            case "insertion":
                return insertion(current);
            case "heap":
                return heap(current);
            case "count":
                return count(copy(current));
            case "quick":
            case "radix":
            case "bucket":
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
        for(int i = 0; i < toControl.size()-1; i++){
            if(toControl.get(i) > toControl.get(i+1)){
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
    private void insert(ArrayList<Integer> A, int start){
        ArrayList<Integer> arr = step.get(step.size()-1);
        ArrayList<Integer> whole = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            whole.add(arr.get(i));
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

    //region count
    public ArrayList<ArrayList<Integer>> count(ArrayList<Integer> toSort){
        COUNTING_SORT(toSort);
        return step;
    }
    private void COUNTING_SORT(ArrayList<Integer> toSort){
        step.add(copy(toSort));
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Integer> sortedArray = new ArrayList<>();

        for(int i = 1; i <= toSort.size()+1; i++){ //Between min value (1) and max value (toSort.size()+1)
            count.add(0);
        }
        for(int i = 0; i < toSort.size(); i++){
            count.set(toSort.get(i), (count.get(toSort.get(i)))+1); //Set the value of spot in toSort element in count to one what it already is +1.
            toSort.remove(i); //remove value (Not nescesarry, only for showing the same size array
            i--;
            //This should be outside the for loop, it is only insede to update the lists
            sortedArray.clear();
            for(int k = 0; k < count.size(); k++){
                if(count.get(k) > 0){
                    for(int j = 0; j < count.get(k); j++){
                        sortedArray.add(k);
                    }
                }
                else sortedArray.add(0);
            }
            step.add(collectArrayLists(copy(sortedArray), copy(toSort)));
        }



    }
    private ArrayList<Integer> collectArrayLists(ArrayList<Integer> A, ArrayList<Integer> B){
        ArrayList<Integer> collected = new ArrayList<>();
        for(int i = 0; i < A.size(); i++){
            if(A.get(i) != 0){
                collected.add(A.get(i));
            }
        }
        for(int i = 0; i < B.size(); i++){
            if(B.get(i) != 0) collected.add(B.get(i));
        }
        return collected;
    }



    //endregion
    //region heap
    public ArrayList<ArrayList<Integer>> heap(ArrayList<Integer> toSort){
        HEAP_SORT(toSort);
        return step;
    }
    private void HEAP_SORT(ArrayList<Integer> toSort){
        int length = toSort.size();
        operations++;

        //Build heap
        for(int i = Math.floorDiv(length, 2) - 1; i >= 0; i--){ //might need ceilDiv
            HEAP_RECURSION(toSort, length, i);
        }


        // One by one extract an element from heap
        for (int i = length - 1; i > 0; i--) {
            // Move current root to end
            int temp = toSort.get(0);
            toSort.set(0, toSort.get(i));
            toSort.set(i, temp);
            operations+= 3;
            // call max HEAP on the reduced heap
            HEAP_RECURSION(toSort, i, 0);
        }
    }
    private void HEAP_RECURSION(ArrayList<Integer> toSort, int length, int i){
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < length && toSort.get(left) >  toSort.get(largest)){
            largest = left;
            operations++;
        }


        // If right child is larger than largest so far
        if (right < length &&  toSort.get(right) >  toSort.get(largest)){
            largest = right;
            operations++;
        }


        // If largest is not root
        if (largest != i) {
            int swap =  toSort.get(i);
            toSort.set(i, toSort.get(largest));
            toSort.set(largest, swap);
            operations += 3;
            // Recursively HEAP the affected sub-tree
            HEAP_RECURSION(toSort, length, largest);
        }
        operations += 6;
        step.add(copy(toSort));
    }
    //endregion
    //region insertion
    public ArrayList<ArrayList<Integer>> insertion(ArrayList<Integer> toSort){
        INSERTION_SORT(toSort);
        return step;
    }
    private ArrayList<Integer> INSERTION_SORT(ArrayList<Integer> toSort){
        for(int i = 1; i < toSort.size(); i++){
            int j = i;
            operations++;
            while (j>0 && toSort.get(j-1) > toSort.get(j)){//while previous element is higher than current.
                int temp = toSort.get(j); //Swaps the elements
                toSort.set(j, toSort.get(j-1));
                toSort.set(j-1, temp);
                step.add(copy(toSort));
                j--; //Goes further back the arrayList
                operations += 5;
            }
        }
        return toSort;
    }

    //endregion
    //region selection
    public ArrayList<ArrayList<Integer>> selection(ArrayList<Integer> toSort){
        SELECTION_LOOP(toSort);
        step.add(toSort);
        return step;
    }
    private ArrayList<Integer> SELECTION_LOOP(ArrayList<Integer> toSort){
        int count = 0;
        while(!control(toSort)){
            toSort = SELECTION_SORT(toSort, count);
            step.add(copy(toSort));
            count++;
        }
        return toSort;
    }
    private ArrayList<Integer> SELECTION_SORT(ArrayList<Integer> toSort, int pos){
        int lowest = -1;
        int lowestIndex = -1;
        operations+= 2;
        for(int i = pos; i < toSort.size(); i++){
            if(lowest == -1){//If no lowest have been found, set it to the current.
                lowest = toSort.get(i);
                lowestIndex = i;
                operations+= 2;
            }
            if(lowest > toSort.get(i)){//if a lower number than current lowest is found, set it to this new one.
                lowest = toSort.get(i);
                lowestIndex = i;
                operations+= 2;
            }
            operations+= 2;
        }
        operations++;
        if(lowest != -1){ //Some error has occurred this should not happen and would be outside the arrayList.
            toSort.set(lowestIndex, toSort.get(pos));
            toSort.set(pos, lowest);
            operations+= 2;
        }

        return toSort;
    }


    //endregion
    //region bubble

    private ArrayList<ArrayList<Integer>> bubble(ArrayList<Integer> toSort){
        BUBBLE_LOOP(toSort);
        return step;
    }
    private void BUBBLE_LOOP(ArrayList<Integer> toSort){
        int count = 0;
        while(!control(toSort)){
            toSort = BUBBLE_SORT(toSort);
            step.add(copy(toSort));
            count++;
        }
    }
    private ArrayList<Integer> BUBBLE_SORT(ArrayList<Integer> toSort){
        for(int i = 1; i < toSort.size(); i++){
            operations++;
            if(toSort.get(i-1) > toSort.get(i)){
                int temp = toSort.get(i-1);
                toSort.set(i-1, toSort.get(i));
                toSort.set(i, temp);
                operations+=3;
            }
        }
        return toSort;
    }
    //endregion
    //region merge

    public ArrayList<ArrayList<Integer>> merge(ArrayList<Integer> toSort){
        MERGE_SORT(toSort, toSort.size());
        return step;
    }
    private void MERGE_SORT(ArrayList<Integer> a, int length){
        ArrayList<Integer> A = MERGE_RECURSION(a, 0);
        step.add(A);

    }
    private ArrayList<Integer> MERGE_RECURSION(ArrayList<Integer> a, int start){
        operations++; //in case this if statement returns
        if(a.size() < 2) return a;
        int r = a.size(); //1
        int m = Math.floorDiv(r,2);//1
        ArrayList<Integer> A1 = new ArrayList<>();//1
        ArrayList<Integer> A2 = new ArrayList<>();//1
        for(int i = 0; i < m; i++){//m number of operations
            A1.add(a.get(i));
        }
        for(int i = m; i < r; i++){//r-m number of operations
            A2.add(a.get(i));
        }
        A1 = MERGE_RECURSION(A1, start);//1
        A2 = MERGE_RECURSION(A2, start + m);//1
        ArrayList<Integer> A = MERGE_SORTED(A1, A2);//this
        insert(A, start);
        operations += 6+m+(r-m); //Total operations for this method
        return A;//1
    }

    private ArrayList<Integer> MERGE_SORTED(ArrayList<Integer> A1, ArrayList<Integer> A2){
        int n = 0, m = 0, i; //3
        ArrayList<Integer> A = new ArrayList<>();//1
        operations += 4;
        for(i = 0; i < A1.size()+A2.size(); i++){//A1.size()+A2.size()* between 3 to 7. Putting operations in each statement instead
            if(n >= A1.size()){ //Reached end of A1
                A.add(i, A2.get(m));
                m++;
                operations +=3;
            }
            else if(m >= A2.size()){ //Reached end of A2
                A.add(i, A1.get(n));
                n++;
                operations +=4;
            }
            else if(A1.get(n) <= A2.get(m)){
                A.add(i, A1.get(n));
                n++;
                operations +=5;
            }
            else{
                A.add(i, A2.get(m));
                m++;
                operations +=5;
            }
        }
        return A;
    }
    //endregion

}
