package woopaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberOfFruits = Integer.parseInt(st.nextToken());
        int playerMoney = Integer.parseInt(st.nextToken());
        Fruit[] fruits = new Fruit[numberOfFruits];

        for (int i = 0; i < numberOfFruits; i++) {
            String[] input = br.readLine().split(" ");
            fruits[i] = new Fruit(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        long result = 0;
        while (true) {
            Optional<Fruit> optionalFruit = Arrays.stream(fruits)
                    .filter(fruit -> !fruit.isPurchased())
                    .max(Comparator.comparingDouble(Fruit::getCostEffectiveness));
            if (optionalFruit.isEmpty()) {
                break;
            }

            Fruit fruit = optionalFruit.get();
            if (fruit.getPrice() > playerMoney) {
                result += (long) playerMoney * fruit.getCostEffectiveness();
                break;
            }

            result += fruit.getFullness();
            playerMoney -= fruit.getPrice();
            fruit.purchase();
        }

        System.out.println(result);
    }

    static class Fruit {

        private final int price;
        private final int fullness;
        private boolean isPurchased;

        public Fruit(int price, int fullness) {
            this.price = price;
            this.fullness = fullness;
        }

        public int getPrice() {
            return price;
        }

        public int getFullness() {
            return fullness;
        }

        public boolean isPurchased() {
            return isPurchased;
        }

        public int getCostEffectiveness() {
            return fullness / price;
        }

        public void purchase() {
            isPurchased = true;
        }
    }
}
