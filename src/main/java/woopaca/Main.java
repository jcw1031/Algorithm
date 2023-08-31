package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Main {

    private static final int[][] DIRECTION = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberOfNodes = Integer.parseInt(st.nextToken());
        int numberOfEdges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        boolean[] isVisited = new boolean[numberOfNodes + 1];

        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        int count = 0;
        int targetNode = startNode;
        while (true) {
            List<Integer> targetNodeEdges = graph.get(targetNode);
            isVisited[targetNode] = true;
            count++;
            OptionalInt minimumNode = targetNodeEdges.stream()
                    .filter(node -> !isVisited[node])
                    .mapToInt(Integer::intValue)
                    .min();
            if (minimumNode.isPresent()) {
                targetNode = minimumNode.getAsInt();

            } else {
                break;
            }
        }

        System.out.println(count + " " + targetNode);
    }
}