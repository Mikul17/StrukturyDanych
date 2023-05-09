package org.example;

import org.example.Hashmap.Hashmap;

public class Main {

    public static int[] generateRandomNumbers(int size){
        int[] randomNumbers = new int[size];
        for(int i = 0; i < size; i++){
            randomNumbers[i] = (int) (Math.random() * 100);
        }
        return randomNumbers;
    }

    public static String[] generateRandomStrings(int size) {
        String[] randomStrings = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 8; j++) { // Generate an 8-character string
                int randomChar = 'a' + (int) (Math.random() * 26); // Generate a random character between 'a' and 'z'
                sb.append((char) randomChar);
            }
            randomStrings[i] = sb.toString();
        }
        return randomStrings;
    }

    public static void main (String[] args) throws Exception {
        Hashmap<String, Integer> hashmap = new Hashmap();
        String[] randomStrings = generateRandomStrings(100);
        int[] randomNumbers = generateRandomNumbers(100);
        for(int i = 0; i < randomStrings.length; i++){
            hashmap.put(randomStrings[i], randomNumbers[i]);
        }
        hashmap.print();
    };
}