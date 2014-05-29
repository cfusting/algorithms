package com.fusting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Graph.
 */
public class Graph {

    private SortedMap<Integer, Vertex> vertices = new TreeMap<Integer, Vertex>();
    private List<Edge> edges = new ArrayList<Edge>();

    public Graph(List<List<Integer>> linesFields) {
        initVertices(linesFields.size());
        initEdges(linesFields);
    }

    private void initVertices(int n) {
        for (Integer i = 0; i < n; i++) {
            vertices.put(i, new Vertex(i));
        }
    }

    private void initEdges(List<List<Integer>> linesFields) {
        for (List<Integer> line : linesFields) {
            // The first vertex will create an edge with each on the same line.
            Vertex v1 = vertices.get(line.get(0));
            for (Integer i = 1; i < line.size(); i++) {
                Vertex v2 = vertices.get(line.get(i));
                // Create a Set of vertices for the edge
                List<Vertex> verts = new ArrayList<Vertex>();
                verts.add(v1);
                verts.add(v2);
                // Create the edge, add the Set of vertices
                Edge edge = new Edge();
                edge.setVertices(verts);
                // Add the edge to each of the vertices
                v1.addEdge(edge);
                v2.addEdge(edge);
                // Add the edge to the master list
                edges.add(edge);
            }
        }
    }
}
