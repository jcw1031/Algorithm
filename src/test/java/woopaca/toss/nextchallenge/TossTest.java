package woopaca.toss.nextchallenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TossTest {

    @Test
    void first() {
        int answer;
        First first = new First();
        answer = first.solution("1451232125", 2);
        assertEquals(21, answer);
        answer = first.solution("313253123", 3);
        assertEquals(312, answer);
        answer = first.solution("12412415", 4);
        assertEquals(-1, answer);
    }

    @Test
    void second() {
        int answer;
        Second second = new Second();
        answer = second.solution(new int[][]{{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}}, 2, 3);
        assertEquals(37, answer);

        answer = second.solution(new int[][]{{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}}, 1, 2);
        assertEquals(27, answer);

        answer = second.solution(new int[][]{{1, 2}, {2, 1}, {1, 3}, {3, 4}, {4, 3}}, 1, 2);
        assertEquals(21, answer);

        answer = second.solution(new int[][]{{1, 2}}, 1, 2);
        assertEquals(5, answer);

        answer = second.solution(new int[][]{{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}}, 3, 10);
        assertEquals(43, answer);
    }
}