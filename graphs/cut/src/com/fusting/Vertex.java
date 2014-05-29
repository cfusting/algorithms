package com.fusting;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex.
 */
public class Vertex {

    private Integer id;
    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(int id) {
        this.id = id;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getId() {
        return id;
    }
}
