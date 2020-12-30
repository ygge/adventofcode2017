import util.Direction;
import util.Pos;
import util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day22 {

    public static void main(String[] args) {
        boolean[][] board = Util.readBoard('#', '.');
        Set<Pos> infected = new HashSet<>();
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[y].length; ++x) {
                if (board[y][x]) {
                    infected.add(new Pos(x, y));
                }
            }
        }
        Pos pos = new Pos(board[0].length/2, board.length/2);
        //Util.submitPart1(part1(infected, pos));
        Map<Pos, Integer> infected2 = new HashMap<>();
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[y].length; ++x) {
                if (board[y][x]) {
                    infected2.put(new Pos(x, y), 2);
                }
            }
        }
        Util.submitPart2(part2(infected2, pos));
    }

    private static int part2(Map<Pos, Integer> infected, Pos pos) {
        int count = 0;
        Direction dir = Direction.UP;
        for (int i = 0; i < 10000000; ++i) {
            Integer level = infected.get(pos);
            if (level == null) {
                dir = dir.turnLeft();
                infected.put(pos, 1);
            } else if (level == 1) {
                infected.put(pos, 2);
                ++count;
            } else if (level == 2) {
                dir = dir.turnRight();
                infected.put(pos, 3);
            } else {
                dir = dir.turnRight().turnRight();
                infected.remove(pos);
            }
            pos = pos.move(dir);
        }
        return count;
    }

    private static int part1(Set<Pos> infected, Pos pos) {
        int count = 0;
        Direction dir = Direction.UP;
        for (int i = 0; i < 10000; ++i) {
            if (infected.contains(pos)) {
                dir = dir.turnRight();
                infected.remove(pos);
            } else {
                dir = dir.turnLeft();
                infected.add(pos);
                ++count;
            }
            pos = pos.move(dir);
        }
        return count;
    }
}
