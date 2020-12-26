import util.Util;

import java.util.*;

public class Day12 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        Map<Integer, List<Integer>> pipes = new HashMap<>();
        for (String row : input) {
            String[] split = row.split(" ");
            int n = Integer.parseInt(split[0]);
            var neigh = new ArrayList<Integer>();
            for (int i = 2; i < split.length; ++i) {
                String s = split[i];
                if (s.endsWith(",")) {
                    s = s.substring(0, s.length()-1);
                }
                var m = Integer.parseInt(s);
                neigh.add(m);
            }
            pipes.put(n, neigh);
        }
        Set<Integer> seen = new HashSet<>();
        int count = 0;
        for (Integer n : pipes.keySet()) {
            if (count(pipes, seen, n) > 0) {
                ++count;
            }
        }
        return count;
    }

    private static int part1(List<String> input) {
        Map<Integer, List<Integer>> pipes = new HashMap<>();
        for (String row : input) {
            String[] split = row.split(" ");
            int n = Integer.parseInt(split[0]);
            var neigh = new ArrayList<Integer>();
            for (int i = 2; i < split.length; ++i) {
                String s = split[i];
                if (s.endsWith(",")) {
                    s = s.substring(0, s.length()-1);
                }
                var m = Integer.parseInt(s);
                neigh.add(m);
            }
            pipes.put(n, neigh);
        }
        Set<Integer> seen = new HashSet<>();
        return count(pipes, seen, 0);
    }

    private static int count(Map<Integer, List<Integer>> pipes, Set<Integer> seen, int index) {
        if (!seen.add(index)) {
            return 0;
        }
        var count = 1;
        for (Integer neigh : pipes.get(index)) {
            count += count(pipes, seen, neigh);
        }
        return count;
    }
}
