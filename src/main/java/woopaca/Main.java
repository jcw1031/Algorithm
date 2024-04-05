package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int[][] dp = new int[MAX][MAX];
        for (int i = 0; i < MAX; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 2; i < MAX; i++) {
            for (int j = 1; j < MAX; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        int t = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            result.append(dp[m][n])
                    .append("\n");
        }
        System.out.println(result);
    }
}
