package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MAX_VALUE = (int) (Math.pow(10, 6) + 1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] painValue = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = MAX_VALUE;
        }

        for (int i = 0; i < 2; i++) {
            if (painValue[i] > n) {
                continue;
            }
            dp[painValue[i]] = 1;
            for (int j = 1; j <= n; j++) {
                if (j < painValue[i]) {
                    continue;
                }
                int oldValue = dp[j - painValue[i]];
                if (oldValue == MAX_VALUE) {
                    continue;
                }

                dp[j] = Integer.min(oldValue + 1, dp[j]);
            }
        }

        System.out.println(dp[n] == MAX_VALUE ? -1 : dp[n]);
    }
}