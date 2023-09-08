package woopaca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int citiesSize = Integer.parseInt(st.nextToken());
        int loadsSize = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] country = new ArrayList[citiesSize + 1];
        for (int i = 1; i <= citiesSize; i++) {
            country[i] = new ArrayList<>();
        }

        for (int i = 0; i < loadsSize; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            country[city1].add(city2);
            country[city2].add(city1);
        }


        for (int constructionCity = 1; constructionCity <= citiesSize; constructionCity++) {
            if (constructionCity == startCity || constructionCity == endCity) {
                bw.write(-1 + "\n");
                continue;
            }
            int result = bfs(startCity, endCity, country, constructionCity);
            bw.write(result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int bfs(int startCity, int endCity, ArrayList<Integer>[] country, int constructionCity) {
        Queue<Integer> queue = new LinkedList<>();
        int[] isVisited = new int[country.length + 1];
        Arrays.fill(isVisited, Integer.MAX_VALUE);
        queue.add(startCity);
        isVisited[startCity] = 1;

        while (!queue.isEmpty()) {
            int city = queue.poll();
            ArrayList<Integer> adjacentCities = country[city];
            for (Integer adjacentCity : adjacentCities) {
                if (adjacentCity == constructionCity) {
                    continue;
                }

                if (isVisited[adjacentCity] > isVisited[city] + 1) {
                    isVisited[adjacentCity] = isVisited[city] + 1;
                    queue.add(adjacentCity);
                }
                if (adjacentCity == endCity) {
                    return isVisited[endCity] == Integer.MAX_VALUE ? -1 : isVisited[endCity];
                }
            }
        }

        return -1;
    }
}
