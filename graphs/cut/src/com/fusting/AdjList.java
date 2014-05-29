package com.fusting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * AdjList.
 */
public class AdjList {

    private Map<Integer, Set<Integer>> adj = new HashMap<Integer, Set<Integer>>();

    public AdjList(String file) {
        buildAdj(file);
    }

    private void buildAdj(String file) {
        //String file = "/home/elpinguino/algorithms/graphs/kargerMinCut.txt";
        BufferedReader br;
        String line;
        String delim = "\t";

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delim);
                Set<Integer> neighbors = new HashSet<Integer>();
                for (int i = 1; i < fields.length; i++) {
                    if (fields[i].equals("")) {
                        break;
                    }
                    neighbors.add(Integer.parseInt(fields[i]));
//                    System.out.println("Added vertex: " + fields[0] + ", with neighbor: " + fields[i]);
                }
                adj.put(Integer.parseInt(fields[0]), neighbors);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selectRandomEdge();
    }

    private int[] selectRandomEdge() {
        Integer[] vertices = (Integer[]) adj.keySet().toArray(new Integer[0]);
        int v1 = 0;
        int v2 = 0;
        while (v1 == v2) {
            v1 = new Random().nextInt(vertices.length);
            v2 = new Random().nextInt(vertices.length);
        }
        return (new int[] {v1, v2});
    }

    private void mergeVertices(int[] vertices) {
        Set<Integer> winner = adj.get(vertices[0]);
        Set<Integer> looser = adj.remove(vertices[1]);
        winner.addAll(looser);
        winner.remove(vertices[0]);
    }

    private void replaceVertex(int v1, int v2) {

    }

}
