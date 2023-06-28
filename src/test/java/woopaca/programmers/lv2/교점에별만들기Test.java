package woopaca.programmers.lv2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class 교점에별만들기Test {

    @DisplayName("교점에 별 만들기")
    @Test
    void test() throws Exception {
        교점에별만들기 교점에별만들기 = new 교점에별만들기();
        String[] result =
                교점에별만들기.solution(new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}});

        assertArrayEquals(new String[]{"....*....", ".........", ".........", "*.......*",
                ".........", ".........", ".........", ".........", "*.......*"}, result);
    }
}