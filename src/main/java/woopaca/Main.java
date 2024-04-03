package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final StringBuilder result = new StringBuilder();

    private static int[] list;
    private static boolean[] isUsed;
    private static int[] selectedNumbers;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        list = new int[n];
        isUsed = new boolean[n];
        selectedNumbers = new int[m];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            list[i] = number;
        }
        Arrays.sort(list);

        peakNumbers(0);
        System.out.println(result);
    }

    private static void peakNumbers(int depth) {
        if (depth == m) {
            printNumbers(selectedNumbers);
            return;
        }

        int previousNumber = -1;
        for (int i = 0; i < n; i++) {
            if (!isUsed[i] && previousNumber != list[i]) {
                selectedNumbers[depth] = list[i];
                isUsed[i] = true;
                previousNumber = list[i];
                peakNumbers(depth + 1);
                isUsed[i] = false;
            }
        }
    }

    private static void printNumbers(int[] numbers) {
        /*result.append(Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));*/
        for (int number : numbers) {
            result.append(number)
                    .append(" ");
        }
        result.append(System.lineSeparator());
    }
}
