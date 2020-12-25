import util.Util;

import java.util.Collections;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        var input = Util.readIntLists("\t");
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<List<Integer>> input) {
        int sum = 0;
        for (List<Integer> integers : input) {
            Collections.sort(integers);
            sum += findDivisible(integers);
        }
        return sum;
    }

    private static int findDivisible(List<Integer> integers) {
        for (int i = integers.size()-1; i >= 0; --i) {
            for (int j = i-1; j >= 0; --j) {
                if (integers.get(i)%integers.get(j) == 0) {
                    return integers.get(i)/integers.get(j);
                }
            }
        }
        throw new RuntimeException("No divisible values found");
    }

    private static int part1(List<List<Integer>> input) {
        int sum = 0;
        for (List<Integer> integers : input) {
            Collections.sort(integers);
            sum += integers.get(integers.size()-1)-integers.get(0);
        }
        return sum;
    }
}
