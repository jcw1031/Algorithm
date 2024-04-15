package woopaca.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] sequence;
    private static boolean[] isUsed;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[m];
        isUsed = new boolean[n + 1];

        selectSequence(0);
        System.out.println(result);
    }

    private static void selectSequence(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                result.append(sequence[i])
                        .append(" ");
            }
            result.append(System.lineSeparator());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!isUsed[i]) {
                sequence[depth] = i;
                isUsed[i] = true;
                selectSequence(depth + 1);
                isUsed[i] = false;
            }
        }
    }
}
