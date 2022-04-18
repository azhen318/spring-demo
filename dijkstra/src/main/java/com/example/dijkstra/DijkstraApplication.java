package com.example.dijkstra;

import com.example.dijkstra.bo.Graph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DijkstraApplication {

    public static void main(String[] args) {

        Graph<String> graph = new Graph<>(7);
        graph.setDatas(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        int[][] matrix = {
                {0, 7, 3, 2, 2, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 3, 0},
                {0, 0, 0, 0, 1, 10, 2},
                {0, 0, 0, 0, 0, 4, 2},
                {0, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0}};
        graph.setMatrix(matrix);
        graph.makeUndirected();
        for (int i = 0; i < 7; i++) {
            graph.initStatuses();
            graph.DijkstraPath(i);
        }

        SpringApplication.run(DijkstraApplication.class, args);
    }

}
