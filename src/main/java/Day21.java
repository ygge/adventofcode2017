import util.Util;

import java.util.HashMap;
import java.util.Map;

public class Day21 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Map<Tile, Tile> tiles = new HashMap<>();
        int id = 0;
        for (String row : input) {
            String[] split = row.split(" => ");
            tiles.put(new Tile(id++, split[0]), new Tile(split[1]));
        }
        Util.submitPart1(part1(tiles));
        Util.submitPart2(part2(tiles));
    }

    private static int part1(Map<Tile, Tile> tiles) {
        return calc(tiles, 5);
    }

    private static int part2(Map<Tile, Tile> tiles) {
        return calc(tiles, 18);
    }

    private static int calc(Map<Tile, Tile> tiles, int iterations) {
        boolean[][] board = new boolean[3][3];
        board[0][1] = true;
        board[1][2] = true;
        board[2][0] = true;
        board[2][1] = true;
        board[2][2] = true;
        for (int i = 0; i < iterations; ++i) {
            boolean[][] newBoard;
            if (board.length%2 == 0) {
                newBoard = new boolean[board.length * 3 / 2][board.length * 3 / 2];
                for (int y = 0; y < board.length; y += 2) {
                    for (int x = 0; x < board.length; x += 2) {
                        calc(board, newBoard, tiles, y, x, y/2*3, x/2*3, 2);
                    }
                }
            } else {
                newBoard = new boolean[board.length * 4 / 3][board.length * 4 / 3];
                for (int y = 0; y < board.length; y += 3) {
                    for (int x = 0; x < board.length; x += 3) {
                        calc(board, newBoard, tiles, y, x, y/3*4, x/3*4, 3);
                    }
                }
            }
            board = newBoard;
        }
        int count = 0;
        for (boolean[] row : board) {
            for (boolean cell : row) {
                if (cell) {
                    ++count;
                }
            }
        }
        return count;
    }

    private static void calc(boolean[][] board, boolean[][] newBoard, Map<Tile, Tile> tiles, int y, int x, int ny, int nx, int size) {
        for (Map.Entry<Tile, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getKey();
            if (tile.board.length == size && matchTile(board, y, x, tile)) {
                Tile newTile = entry.getValue();
                for (int i = 0; i < newTile.board.length; ++i) {
                    for (int j = 0; j < newTile.board.length; ++j) {
                        newBoard[ny+i][nx+j] = newTile.board[i][j];
                    }
                }
                return;
            }
        }
        throw new RuntimeException("Couldn't match tile");
    }

    private static boolean matchTile(boolean[][] board, int y, int x, Tile tile) {
        for (int i = 0; i < 8; ++i) {
            if (matches(tile, board, y, x)) {
                return true;
            }
            if (i == 3) {
                tile.flip();
            } else {
                tile.rotate();
            }
        }
        return false;
    }

    private static boolean matches(Tile tile, boolean[][] board, int y, int x) {
        for (int i = 0; i < tile.board.length; ++i) {
            for (int j = 0; j < tile.board.length; ++j) {
                if (tile.board[i][j] != board[y+i][x+j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Tile {
        int id;
        boolean[][] board;

        public Tile(String data) {
            this(0, data);
        }

        public Tile(int id, String data) {
            this.id = id;
            String[] split = data.split("/");
            board = new boolean[split.length][split[0].length()];
            for (int y = 0; y < split.length; ++y) {
                for (int x = 0; x < split[y].length(); ++x) {
                    board[y][x] = split[y].charAt(x) == '#';
                }
            }
        }

        public void flip() {
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length/2; ++j) {
                    var c = board[i][j];
                    board[i][j] = board[i][board[i].length-1-j];
                    board[i][board[i].length-1-j] = c;
                }
            }
        }

        public void rotate() {
            boolean[][] newBoard = new boolean[board[0].length][board.length];
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    newBoard[i][j] = board[board.length-1-j][i];
                }
            }
            board = newBoard;
        }
    }
}
