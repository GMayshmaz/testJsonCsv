package com.goeuro;

import com.goeuro.client.GoEuroClient;
import com.goeuro.client.GoEuroServerException;
import com.goeuro.csv.GoEuroCSVWriter;

import java.util.HashMap;
import java.util.List;


public class GoEuroMain {
    public static void main(String[] args) {

        try {
            if (args.length > 0) {
                List<HashMap> responses = GoEuroClient.getInfo(args[0]);
                String csvFileName = "output.csv";
                if (args.length > 1)
                    csvFileName = args[1];
                System.out.println("Output file name " + csvFileName);
                GoEuroCSVWriter.writeToCSV(csvFileName, responses);
            } else {
                System.out.println("Please enter the request string.");
                System.out.println("Example:");
                System.out.println("java -jar testJsonCsv.jar Potsdam");
                System.out.println("Example with specified output filename:");
                System.out.println("java -jar testJsonCsv.jar Potsdam potsdam.csv");
            }
        } catch (GoEuroServerException e) {
            e.printStackTrace();
        }
    }
}
