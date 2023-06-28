package woopaca.programmers.lv2;

public class 삼각달팽이 {

    private int[][] triangle;
    private int[] result;
    private int value = 1;
    private int x = 0;
    private int y = 0;

    public int[] solution(int n) {
        triangle = new int[n][n];

        while (true) {
            // 아래로 이동
            while (true) {
                triangle[y][x] = value++;
                if (y + 1 == n || triangle[y + 1][x] != 0) {
                    break;
                }
                y++;
            }
            if (x + 1 == n || triangle[y][x + 1] != 0) {
                break;
            }
            x++;

            // 오른쪽으로 이동
            while (true) {
                triangle[y][x] = value++;
                if (x + 1 == n || triangle[y][x + 1] != 0) {
                    break;
                }
                x++;
            }
            if (triangle[y - 1][x - 1] != 0) {
                break;
            }
            x--;
            y--;

            // 왼쪽 위로 이동
            while (true) {
                triangle[y][x] = value++;
                if (triangle[y - 1][x - 1] != 0) {
                    break;
                }
                x--;
                y--;
            }
            if (y + 1 == n || triangle[y + 1][x] != 0) {
                break;
            }
            y++;
        }

        result = new int[value - 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = triangle[i][j];
            }
        }

        return result;
    }
}
