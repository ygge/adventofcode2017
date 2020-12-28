import util.Direction;
import util.Pos;
import util.Util;

public class Day19 {

    public static void main(String[] args) {
        char[][] input = Util.readBoard();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(char[][] input) {
        Pos pos = null;
        for (int x = 0; x < input[0].length; ++x) {
            if (input[0][x] == '|') {
                pos = new Pos(x, 0);
            }
        }
        assert pos != null;
        var dir = Direction.DOWN;
        int steps = 0;
        while (pos.y >= 0 && pos.y < input.length && pos.x >= 0 && pos.x < input.length) {
            var c = input[pos.y][pos.x];
            if (c == '+') {
                var newPos = pos.move(dir.turnLeft());
                if (input[newPos.y][newPos.x] != ' ') {
                    dir = dir.turnLeft();
                } else {
                    dir = dir.turnRight();
                }
            } else if (c == ' ') {
                break;
            }
            pos = pos.move(dir);
            ++steps;
        }
        return steps;
    }

    private static String part1(char[][] input) {
        Pos pos = null;
        for (int x = 0; x < input[0].length; ++x) {
            if (input[0][x] == '|') {
                pos = new Pos(x, 0);
            }
        }
        assert pos != null;
        var dir = Direction.DOWN;
        var res = new StringBuilder();
        while (pos.y >= 0 && pos.y < input.length && pos.x >= 0 && pos.x < input.length) {
            var c = input[pos.y][pos.x];
            if (c >= 'A' && c <= 'Z') {
                res.append(c);
            } else if (c == '+') {
                var newPos = pos.move(dir.turnLeft());
                if (input[newPos.y][newPos.x] != ' ') {
                    dir = dir.turnLeft();
                } else {
                    dir = dir.turnRight();
                }
            } else if (c == ' ') {
                break;
            }
            pos = pos.move(dir);
        }
        return res.toString();
    }
}
