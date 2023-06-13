package org.example;


import org.example.LinkedHashMap.LinkedHashMap;

import java.io.IOException;

import static org.example.SortingAlgorithm.*;

public class Main {
    public static void main (String[] args) throws IOException {
        SortingAlgorithm[] sortingAlgorithms = {quickSort, mergeSort, shellSort};
        LinkedHashMap<String,Integer> linkedHashMap=new LinkedHashMap<>();
        LinkedList<Integer> sizes=new LinkedList<>();
        int n=1;
        for(int i=0;i<4;i++){
             n = (int)Math.pow(10,i);
             for(int j=1; j<=4; j++){
                 sizes.addLast(n*25*j);
             }
        }

        for(int i=0; i<sizes.size();i++){
            GenerateData.putDataIntoHashmap(linkedHashMap,"data("+sizes.get(i)+").csv");
            System.out.println("Number of hash collisions: "+linkedHashMap.getNumberOfHashCollisions()+"[dataset size = "+sizes.get(i)+"]");
            linkedHashMap.clear();
        }
        System.out.println("-".repeat(50));
        for(int i=0; i<sizes.size();i++){
          for(SortingAlgorithm sortingAlgorithm : sortingAlgorithms){
              GenerateData.putDataIntoHashmap(linkedHashMap, "data("+sizes.get(i)+").csv");
              long startTime = System.currentTimeMillis();
              linkedHashMap.sort(sortingAlgorithm,true,true);
              long endTime = System.currentTimeMillis();
              long duration = (endTime - startTime);
              System.out.println("Time taken by "+sortingAlgorithm+" to sort "+sizes.get(i)+" elements is "+duration+" ms");
              linkedHashMap.clear();
          }
            System.out.println("-".repeat(50));
        }
    }
}