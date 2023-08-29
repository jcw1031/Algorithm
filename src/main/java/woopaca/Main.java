package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[][] DIRECTION = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] m = new char[n][n];
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            m[i] = br.readLine().replaceAll(" ", "").toCharArray();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m[i][j] == '1' && !isVisited[i][j]) {
                    count++;
                    bfs(n, m, isVisited, i, j);
                }
            }
        }

        System.out.println(count);
    }

    private static void bfs(int n, char[][] m, boolean[][] isVisited, int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        isVisited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int row = coordinates[0];
            int col = coordinates[1];

            for (int[] ints : DIRECTION) {
                int nextRow = ints[0] + row;
                int nextCol = ints[1] + col;
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n ||
                        isVisited[nextRow][nextCol] || m[nextRow][nextCol] != '1') {
                    continue;
                }

                isVisited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
    }
}