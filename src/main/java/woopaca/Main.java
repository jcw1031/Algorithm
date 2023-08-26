package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] goormPosition = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        boolean[][] goormVisited = new boolean[n][n];

        st = new StringTokenizer(br.readLine());
        int[] playerPosition = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        boolean[][] playerVisited = new boolean[n][n];

        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = st.nextToken();
            }
        }

        int goormScore = move(goormPosition, goormVisited, 1, board, n);
        int playerScore = move(playerPosition, playerVisited, 1, board, n);

        if (goormScore > playerScore) {
            System.out.println("goorm " + goormScore);
        } else if (goormScore < playerScore) {
            System.out.println("player " + playerScore);
        }

        br.close();
    }


    public static int set_Pos(int a, int n) {
        if (a == -1) {
            return n - 1;
        }
        if (a == n) {
            return 0;
        }
        return a;
    }

    private static final HashMap<String, int[]> directions = new HashMap<>() {
        {
            put("U", new int[]{-1, 0});
            put("D", new int[]{1, 0});
            put("L", new int[]{0, -1});
            put("R", new int[]{0, 1});
        }
    };

    public static int move(int[] pos, boolean[][] visited, int score, String[][] board, int n) {
        int x = pos[0];
        int y = pos[1];
        visited[x][y] = true;
        boolean flag = true;

        while (flag) {
            String command = board[x][y];
            int distance = Integer.parseInt(command.substring(0, command.length() - 1));
            String direction = command.substring(command.length() - 1);

            for (int i = 0; i < distance; i++) {
                x += directions.get(direction)[0];
                y += directions.get(direction)[1];
                x = set_Pos(x, n);
                y = set_Pos(y, n);

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    score += 1;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return score;
    }
}