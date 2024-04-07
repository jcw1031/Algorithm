package woopaca.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<int[]> processes = new ArrayList<>();

        if (n > 20) {
            System.out.println(BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE));
            return;
        }

        hanoi(n, 1, 3, processes);
        int size = processes.size();
        StringBuilder result = new StringBuilder();
        result.append(size)
                .append("\n");
        for (int i = 0; i < size; i++) {
            int[] process = processes.get(i);
            result.append(process[0])
                    .append(" ")
                    .append(process[1])
                    .append("\n");
        }
        System.out.println(result);
    }

    private static void hanoi(int n, int from, int to, List<int[]> processes) {
        if (n == 1) {
            processes.add(new int[]{from, to});
            return;
        }

        int empty = 6 - from - to;
        hanoi(n - 1, from, empty, processes);
        hanoi(1, from, to, processes);
        hanoi(n - 1, empty, to, processes);
    }
}
