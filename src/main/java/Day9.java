import util.Util;

public class Day9 {

    public static void main(String[] args) {
        String input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        int count = 0;
        boolean garbage = false;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (garbage) {
                if (c == '!') {
                    ++i;
                } else if (c == '>') {
                    garbage = false;
                } else {
                    ++count;
                }
            } else if (c == '<') {
                garbage = true;
            }
        }
        return count;
    }

    private static int part1(String input) {
        int sum = 0;
        int level = 0;
        boolean garbage = false;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (garbage) {
                if (c == '!') {
                    ++i;
                } else if (c == '>') {
                    garbage = false;
                }
            } else if (c == '<') {
                garbage = true;
            } else if (c == '{') {
                ++level;
            } else if (c == '}') {
                sum += level;
                --level;
            }
        }
        return sum;
    }
}
