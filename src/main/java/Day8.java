import util.Util;

import java.util.*;

public class Day8 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        Map<String, Integer> re = new HashMap<>();
        int highest = 0;
        for (String ins : input) {
            String[] split = ins.split(" ");
            String checkR = split[4];
            int checkV = Integer.parseInt(split[6]);
            int v = re.getOrDefault(checkR, 0);
            boolean check = check(split[5], checkV, v);
            if (check) {
                int dv = Integer.parseInt(split[2]) * (split[1].equals("inc") ? 1 : -1);
                int vv = re.getOrDefault(split[0], 0);
                re.put(split[0], vv+dv);
                highest = Math.max(highest, vv+dv);
            }
        }
        return highest;
    }

    private static int part1(List<String> input) {
        Map<String, Integer> re = new HashMap<>();
        for (String ins : input) {
            String[] split = ins.split(" ");
            String checkR = split[4];
            int checkV = Integer.parseInt(split[6]);
            int v = re.getOrDefault(checkR, 0);
            boolean check = check(split[5], checkV, v);
            if (check) {
                int dv = Integer.parseInt(split[2]) * (split[1].equals("inc") ? 1 : -1);
                int vv = re.getOrDefault(split[0], 0);
                re.put(split[0], vv+dv);
            }
        }
        List<Integer> values = new ArrayList<>(re.values());
        Collections.sort(values);
        return values.get(values.size()-1);
    }

    private static boolean check(String op, int checkV, int v) {
        switch (op) {
            case ">":
                return v > checkV;
            case "<":
                return v < checkV;
            case "<=":
                return v <= checkV;
            case ">=":
                return v >= checkV;
            case "==":
                return v == checkV;
            case "!=":
                return v != checkV;
        }
        throw new RuntimeException("operation " + op + " not understood");
    }
}
