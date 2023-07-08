package woopaca.toss.nextchallenge;

public class First {

    public int solution(String input, int n) {
        int maxValue = 0;
        boolean[] isContain = null;

        for (int i = 0; i <= input.length() - n; i++) {
            String substring = input.substring(i, i + n);
            isContain = new boolean[n + 1];
            for (int j = 0; j < substring.length(); j++) {
                int bit = convertCharToInt(substring.charAt(j));
                if (bit > n) {
                    break;
                }

                isContain[bit] = true;
            }

            maxValue = getMaxValue(maxValue, isContain, substring);
        }

        if (maxValue != 0) {
            return maxValue;
        }

        return -1;
    }

    private int getMaxValue(int maxValue, boolean[] isContain, String substring) {
        int substringConvertedToInt = Integer.parseInt(substring);
        if (isAllContain(isContain) && maxValue < substringConvertedToInt) {
            maxValue = substringConvertedToInt;
        }
        return maxValue;
    }

    private boolean isAllContain(boolean[] isContain) {
        for (int i = 1; i < isContain.length; i++) {
            if (!isContain[i]) {
                return false;
            }
        }

        return true;
    }

    private int convertCharToInt(char bit) {
        return Integer.parseInt(String.valueOf(bit));
    }
}
