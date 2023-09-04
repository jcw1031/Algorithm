package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberOfNodes = Integer.parseInt(st.nextToken());
        int numberOfEdges = Integer.parseInt(st.nextToken());

        boolean[] isVisited = new boolean[numberOfNodes + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }

        int count = 0;
        for (int i = 1; i <= numberOfNodes; i++) {
            if (isVisited[i]) {
                continue;
            }

            exploreComponents(i, graph, isVisited);
            count++;
        }

        System.out.println(count);
    }

    private static void exploreComponents(int node, Map<Integer, List<Integer>> graph, boolean[] isVisited) {
        isVisited[node] = true;
        List<Integer> adjacentNodes = graph.get(node);
        for (Integer adjacentNode : adjacentNodes) {
            if (graph.get(adjacentNode).contains(node) && !isVisited[adjacentNode]) {
                exploreComponents(adjacentNode, graph, isVisited);
            }
        }
    }
}
