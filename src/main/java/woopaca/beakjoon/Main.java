package woopaca.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<Node>[] graph;
    private static boolean[] isVisited;
    private static int maxDistance = -1;
    private static int maxDistanceNode;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(reader.readLine());
        graph = new ArrayList[n + 1];
        isVisited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int parentNode = Integer.parseInt(tokenizer.nextToken());
            int childNode = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            graph[parentNode].add(new Node(childNode, weight));
            graph[childNode].add(new Node(parentNode, weight));
        }

        findFarthestNode(1, 0);
        isVisited = new boolean[n + 1];
        findFarthestNode(maxDistanceNode, 0);
        System.out.println(maxDistance);
    }

    private static void findFarthestNode(int currentNode, int distance) {
        isVisited[currentNode] = true;
        List<Node> nodes = graph[currentNode];

        for (Node node : nodes) {
            if (!isVisited[node.number]) {
                findFarthestNode(node.number, distance + node.weight);
            }
        }

        if (maxDistance < distance) {
            maxDistance = distance;
            maxDistanceNode = currentNode;
        }
    }

    static class Node {

        int number;
        int weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}
