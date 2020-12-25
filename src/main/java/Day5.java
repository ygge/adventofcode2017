import util.Util;

import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        var input = Util.readInts();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<Integer> input) {
        int pos = 0;
        int count = 0;
        while (pos >= 0 && pos < input.size()) {
            int v = input.get(pos);
            if (v >= 3) {
                input.set(pos, v-1);
            } else {
                input.set(pos, v+1);
            }
            pos += v;
            ++count;
        }
        return count;
    }

    private static int part1(List<Integer> input) {
        int pos = 0;
        int count = 0;
        while (pos >= 0 && pos < input.size()) {
            int v = input.get(pos);
            input.set(pos, v+1);
            pos += v;
            ++count;
        }
        return count;
    }
}
