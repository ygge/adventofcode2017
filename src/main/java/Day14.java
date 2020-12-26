import util.Pos;
import util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day14 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        boolean[][] board = new boolean[128][128];
        for (int i = 0; i < 128; ++i) {
            int[] hash = knotHash(input + "-" + i);
            for (int k = 0; k < hash.length; ++k) {
                int h = hash[k];
                for (int j = 0; j < 8; ++j) {
                    if ((h&(1<<j)) != 0) {
                        board[i][k*8+(7-j)] = true;
                    }
                }
            }
        }
        Set<Pos> seen = new HashSet<>();
        int regions = 0;
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board.length; ++x) {
                if (board[y][x]) {
                    Pos pos = new Pos(x, y);
                    if (!seen.contains(pos)) {
                        ++regions;
                        mark(board, seen, pos);
                    }
                }
            }
        }
        return regions;
    }

    private static void mark(boolean[][] board, Set<Pos> seen, Pos pos) {
        if (pos.y < 0 || pos.y >= board.length || pos.x < 0 || pos.x >= board[pos.y].length || !board[pos.y][pos.x]) {
            return;
        }
        if (!seen.add(pos)) {
            return;
        }
        mark(board, seen, new Pos(pos.x-1, pos.y));
        mark(board, seen, new Pos(pos.x, pos.y-1));
        mark(board, seen, new Pos(pos.x+1, pos.y));
        mark(board, seen, new Pos(pos.x, pos.y+1));
    }

    private static int part1(String input) {
        int count = 0;
        for (int i = 0; i < 128; ++i) {
            int[] hash = knotHash(input + "-" + i);
            for (int h : hash) {
                for (int j = 0; j < 16; ++j) {
                    if ((h&(1<<j)) != 0) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    private static int[] knotHash(String inputString) {
        var data = new int[256];
        for (int i = 1; i < data.length; ++i) {
            data[i] = i;
        }
        var input = new int[inputString.length()+5];
        for (int i = 0; i < inputString.length(); ++i) {
            input[i] = inputString.charAt(i);
        }
        input[inputString.length()] = 17;
        input[inputString.length()+1] = 31;
        input[inputString.length()+2] = 73;
        input[inputString.length()+3] = 47;
        input[inputString.length()+4] = 23;
        int curr = 0;
        int skip = 0;
        for (int t = 0; t < 64; ++t) {
            for (int len : input) {
                for (int i = 0; i < len/2; ++i) {
                    int p1 = (curr+i)%data.length;
                    int p2 = (curr+len-1-i)%data.length;
                    int c = data[p1];
                    data[p1] = data[p2];
                    data[p2] = c;
                }
                curr = (curr+len+skip)%data.length;
                ++skip;
            }
        }
        int[] hash = new int[16];
        for (int i = 0; i < data.length; ++i) {
            hash[i/16] ^= data[i];
        }
        return hash;
    }
}
