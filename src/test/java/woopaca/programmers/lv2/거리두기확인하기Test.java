package woopaca.programmers.lv2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class 거리두기확인하기Test {

    private final 거리두기확인하기 거리두기확인하기 = new 거리두기확인하기();

    @Test
    void test() {
        int[] answer = 거리두기확인하기.solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        });

        assertArrayEquals(new int[]{1, 0, 1, 1, 1}, answer);
    }
}