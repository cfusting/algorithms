package com.fusting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AdjList adj = new AdjList("/home/elpinguino/algorithms/graphs/kargerMinCut.txt");
    }

    private static List<List<Integer>> readCsv() {
        String file = "/home/elpinguino/algorithms/graphs/kargerMinCut.txt";
        BufferedReader br;
        String line;
        String delim = "\t";
        List<List<Integer>> linesFields = new ArrayList<List<Integer>>();

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delim);
                List<Integer> v = new ArrayList<Integer>();
                for (String i : fields) {
                    if (i.equals("")) {
                        break;
                    }
                    v.add(Integer.parseInt(i));
                }
                linesFields.add(v);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return(linesFields);
    }
}