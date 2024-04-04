package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int TARGET_POINT = 2;
    private static final int IMMOVABLE_POINT = 0;

    private static int n;
    private static int m;
    private static int targetX;
    private static int targetY;
    private static int[][] map;
    private static int[][] distanceMap;
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][m];
        distanceMap = new int[n][m];
        isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                distanceMap[i][j] = -1;
                if (map[i][j] == TARGET_POINT) {
                    targetY = i;
                    targetX = j;
                }
            }
        }

        bfs(new Point(targetX, targetY, 0));

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.append(map[i][j] == IMMOVABLE_POINT ? 0 : distanceMap[i][j])
                        .append(" ");
            }
            result.append(System.lineSeparator());
        }
        System.out.println(result);
    }

    private static void bfs(Point targetPoint) {
        final Queue<Point> queue = new LinkedList<>();
        queue.offer(targetPoint);
        isVisited[targetPoint.y][targetPoint.x] = true;

        while (!queue.isEmpty()) {
            final Point point = queue.poll();
            distanceMap[point.y][point.x] = point.distance;

            addPoint(queue, new Point(point.x + 1, point.y, point.distance + 1));
            addPoint(queue, new Point(point.x - 1, point.y, point.distance + 1));
            addPoint(queue, new Point(point.x, point.y + 1, point.distance + 1));
            addPoint(queue, new Point(point.x, point.y - 1, point.distance + 1));
        }
    }

    private static void addPoint(Queue<Point> queue, Point point) {
        if (point.x < 0 || point.y < 0 || point.y >= n || point.x >= m
                || isVisited[point.y][point.x]) {
            return;
        }
        if (map[point.y][point.x] == IMMOVABLE_POINT) {
            distanceMap[point.y][point.x] = 0;
            isVisited[point.y][point.x] = true;
            return;
        }

        queue.offer(point);
        isVisited[point.y][point.x] = true;
    }

    static class Point {

        final int x;
        final int y;
        final int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
