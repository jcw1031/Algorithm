package woopaca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] ROW_D = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
    private static final int[] COL_D = new int[]{1, -1, 1, -1, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] gameBoard = new int[n][n];
        int[][] flags = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gameBoard[i][j] == 1) {
                    updateFlagsValue(gameBoard, flags, i, j);
                }
            }
        }

        System.out.println(getFlagsCount(flags, k));
    }

    private static void updateFlagsValue(int[][] gameBoard, int[][] flags, int row, int col) {
        for (int i = 0; i < ROW_D.length; i++) {
            int newRow = row + ROW_D[i];
            int newCol = col + COL_D[i];
            if (newRow < 0 || newRow >= flags.length || newCol < 0 || newCol >= flags.length ||
                    gameBoard[newRow][newCol] == 1) {
                continue;
            }

            flags[newRow][newCol]++;
        }
    }

    private static int getFlagsCount(int[][] flags, int k) {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags.length; j++) {
                if (flags[i][j] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}