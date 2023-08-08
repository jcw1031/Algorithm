package woopaca.programmers.lv2;

public class 거리두기확인하기 {


    private static final char PERSON = 'P';
    private static final char TABLE = 'O';
    private static final char PARTITION = 'X';
    private static final int[] DX = {0, -1, 1, 0};
    private static final int[] DY = {-1, 0, 0, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            String[] place = places[i];
            char[][] room = new char[place.length][];
            for (int j = 0; j < room.length; j++) {
                room[j] = place[j].toCharArray();
            }

            if (isDistanced(room)) {
                answer[i] = 1;
                continue;
            }
            answer[i] = 0;
        }
        return answer;
    }

    private boolean isDistanced(char[][] room) {
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                if (room[y][x] != PERSON) {
                    continue;
                }
                if (!isDistanced(room, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDistanced(char[][] room, int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                continue;
            }

            switch (room[ny][nx]) {
                case PERSON: {
                    return false;
                }
                case TABLE: {
                    if (isNextToVolunteer(room, nx, ny, 3 - d)) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
        for (int d = 0; d < 4; d++) {
            if (d == exclude) {
                continue;
            }

            int nx = x + DX[d];
            int ny = y + DY[d];
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                continue;
            }

            if (room[ny][nx] == PERSON) {
                return true;
            }
        }
        return false;
    }
}
