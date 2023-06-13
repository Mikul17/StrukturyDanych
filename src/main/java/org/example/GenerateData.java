package org.example;

import org.example.LinkedHashMap.LinkedHashMap;

import java.io.*;

public class GenerateData {

    private static String generateRandomString (){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 5; j++) { // Generate an 8-character string
            int randomChar = 'a' + (int) (Math.random() * 26); // Generate a random character between 'a' and 'z'
            sb.append((char) randomChar);
        }
        return sb.toString();
    }

    private static Integer generateRandomNumber () {
        return (int) (Math.random() * 10000);
    }

    public static void generateData(int size) throws IOException {
        String csvFileName = "data("+size+").csv";
        StringBuilder sb = new StringBuilder();
        try( BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName))){
            for(int i = 0; i < size; i++){
                sb.append(generateRandomString()).append(",").append(generateRandomNumber());
                writer.write(sb.toString());
                writer.newLine();
                sb.setLength(0);
            }
        }
    }

    public static void putDataIntoHashmap(LinkedHashMap<String,Integer> map, String filePath) throws FileNotFoundException {
        try(BufferedReader buffer = new BufferedReader(new FileReader(filePath))){
            String line;
            buffer.readLine();
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                map.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
