package woopaca.programmers.lv2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class 삼각달팽이Test {

    @DisplayName(value = "삼각 달팽이")
    @Test
    void test() throws Exception {
        삼각달팽이 삼각달팽이 = new 삼각달팽이();
        int[] result = 삼각달팽이.solution(6);

        assertArrayEquals(new int[]{1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}, result);
    }
}