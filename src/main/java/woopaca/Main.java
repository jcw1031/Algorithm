package woopaca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int[] ROW_D = new int[]{0, 0, 1, -1};
    private static final int[] COL_D = new int[]{1, -1, 0, 0};

    private static char[][] groundStatus;
    private static int[][] bombValues;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        groundStatus = new char[n][n];
        bombValues = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                groundStatus[i][j] = st.nextToken().charAt(0);
            }
        }

        for (int i = 0; i < k; i++) {
            int[] coordinates = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(value -> Integer.parseInt(value) - 1)
                    .toArray();
            updateBombValue(coordinates[0], coordinates[1]);

            updateSurroundBombValue(coordinates);
        }

        System.out.println(getMaxBombValue());
    }

    private static void updateSurroundBombValue(int[] coordinates) {
        for (int j = 0; j < ROW_D.length; j++) {
            int newRow = coordinates[0] + ROW_D[j];
            int newCol = coordinates[1] + COL_D[j];
            if (newRow < 0 || newCol < 0 || newRow >= groundStatus.length || newCol >= groundStatus.length) {
                continue;
            }

            updateBombValue(newRow, newCol);
        }
    }

    private static void updateBombValue(int row, int col) {
        switch (groundStatus[row][col]) {
            case '0': {
                bombValues[row][col] += 1;
                break;
            }
            case '@': {
                bombValues[row][col] += 2;
                break;
            }
        }
    }

    private static int getMaxBombValue() {
        int max = 0;
        for (int[] bombValuesRow : bombValues) {
            for (int bombValue : bombValuesRow) {
                if (max < bombValue) {
                    max = bombValue;
                }
            }
        }
        return max;
    }
}