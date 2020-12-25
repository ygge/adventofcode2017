import util.Direction;
import util.Pos;
import util.Util;

public class Day3 {

    public static void main(String[] args) {
        int input = Util.readInt();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(int input) {
        int[][] board = new int[100][100];
        int v = 1;
        var pos = new Pos(50, 50);
        board[pos.y][pos.x] = v;
        var dir = Direction.RIGHT;
        while (v < input) {
            pos = pos.move(dir);
            v = 0;
            for (int dy = -1; dy <= 1; ++dy) {
                for (int dx = -1; dx <= 1; ++dx) {
                    v += board[pos.y+dy][pos.x+dx];
                }
            }
            board[pos.y][pos.x] = v;
            var newDir = dir.turnLeft();
            var nextPos = pos.move(newDir);
            if (board[nextPos.y][nextPos.x] == 0) {
                dir = newDir;
            }
        }
        return v;
    }

    private static int part1(int input) {
        var a = 1;
        while (a*a <= input) {
            a += 2;
        }
        var c = input-((a-2)*(a-2));
        return c%(a-1);
    }
}
