package woopaca.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static final int HOUSE = 1;
    private static final int CHICKEN = 2;

    private static int n;
    private static int m;
    private static List<Point> chickens;
    private static List<Point> houses;
    private static Point[] selectedChickens;
    private static int minimumDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                if (input == CHICKEN) {
                    chickens.add(new Point(i, j));
                }
                if (input == HOUSE) {
                    houses.add(new Point(i, j));
                }
            }
        }

        selectedChickens = new Point[m];
        combination(0, 0);

        System.out.println(minimumDistance);
    }

    private static void combination(int depth, int startIndex) {
        if (depth == m) {
            int chickenDistance = calculateChickenDistance();
            if (minimumDistance > chickenDistance) {
                minimumDistance = chickenDistance;
            }
            return;
        }

        for (int i = startIndex; i < chickens.size(); i++) {
            selectedChickens[depth] = chickens.get(i);
            combination(depth + 1, i + 1);
        }
    }

    private static int calculateChickenDistance() {
        Map<Point, Integer> distances = new HashMap<>();
        for (Point chicken : selectedChickens) {
            for (Point house : houses) {
                int distance = chicken.getDistanceBetween(house);
                if (distance < distances.getOrDefault(house, Integer.MAX_VALUE)) {
                    distances.put(house, distance);
                }
            }
        }

        int chickenDistance = 0;
        for (Integer value : distances.values()) {
            chickenDistance += value;
        }
        return chickenDistance;
    }

    static class Point {

        final int row;
        final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getDistanceBetween(Point other) {
            return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
        }
    }
}
