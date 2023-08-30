package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[][] DIRECTION = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] m = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            m[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        int[] complexesCount = new int[30 + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    int number = m[i][j];
                    boolean isComplex = bfs(n, k, m, isVisited, i, j, number);
                    if (isComplex) {
                        complexesCount[number]++;
                    }
                }
            }
        }

        int answer = 0;
        int max = 0;
        for (int i = 1; i < complexesCount.length; i++) {
            int count = complexesCount[i];
            if (max < count || (max == count && answer < i)) {
                max = count;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int n, int k, int[][] m, boolean[][] isVisited, int startRow, int startCol, int number) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        isVisited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            count++;
            int row = coordinates[0];
            int col = coordinates[1];

            for (int[] ints : DIRECTION) {
                int nextRow = ints[0] + row;
                int nextCol = ints[1] + col;
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n ||
                        isVisited[nextRow][nextCol] || m[nextRow][nextCol] != number) {
                    continue;
                }

                isVisited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
        return count >= k;
    }
}