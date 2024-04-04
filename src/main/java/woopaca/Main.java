package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int WHITE = 0;
    private static final int BLUE = 1;

    private static int n;
    private static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        n = Integer.parseInt(reader.readLine());
        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        ColorPaper colorPaper = circulate(0, 0, n);
        System.out.println(colorPaper.white + System.lineSeparator() + colorPaper.blue);
    }

    private static ColorPaper circulate(int startX, int startY, int size) {
        for (int i = startY; i < startY + size; i++) {
            for (int j = startX; j < startX + size; j++) {
                if (paper[i][j] != paper[startY][startX]) {
                    return circulate(startX, startY, size / 2)
                            .add(circulate(startX + size / 2, startY, size / 2))
                            .add(circulate(startX, startY + size / 2, size / 2))
                            .add(circulate(startX + size / 2, startY + size / 2, size / 2));
                }
            }
        }

        if (paper[startY][startX] == WHITE) {
            return new ColorPaper(1, 0);
        }
        return new ColorPaper(0, 1);
    }

    static class ColorPaper {

        int white;
        int blue;

        public ColorPaper(int white, int blue) {
            this.white = white;
            this.blue = blue;
        }

        public ColorPaper add(ColorPaper other) {
            return new ColorPaper(white + other.white, blue + other.blue);
        }
    }
}
