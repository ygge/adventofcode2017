import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day16 {

    public static void main(String[] args) {
        String[] split = Util.readString().split(",");
        Util.submitPart1(part1(split));
        Util.submitPart2(part2(split));
    }

    private static String part2(String[] input) {
        var data = new char[16];
        for (char c = 'a'; c <= 'p'; ++c) {
            data[c-'a'] = c;
        }
        List<String> list = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        list.add(new String(data));
        seen.add(new String(data));
        int goal = 1000000000;
        for (int t = 0; t < goal; ++t) {
            for (String op : input) {
                if (op.startsWith("s")) {
                    int n = Integer.parseInt(op.substring(1));
                    var data2 = new char[16];
                    for (int i = 0; i < data.length; ++i) {
                        data2[(i+n)%data.length] = data[i];
                    }
                    data = data2;
                } else if (op.startsWith("x")) {
                    String[] split = op.substring(1).split("/");
                    int a = Integer.parseInt(split[0]);
                    int b = Integer.parseInt(split[1]);
                    char c = data[a];
                    data[a] = data[b];
                    data[b] = c;
                } else if (op.startsWith("p")) {
                    char a = op.charAt(1);
                    char b = op.charAt(3);
                    int ai = index(data, a);
                    int bi = index(data, b);
                    char c = data[ai];
                    data[ai] = data[bi];
                    data[bi] = c;
                } else {
                    throw new RuntimeException("Unknown operation: " + op);
                }
            }
            var res = new String(data);
            if (!seen.add(res)) {
                int index = list.indexOf(res);
                int steps = list.size()-index;
                while (t+steps < goal) {
                    t += steps;
                }
                return list.get(goal-(t+1)+index);
            }
            list.add(res);
        }
        return new String(data);
    }

    private static String part1(String[] input) {
        var data = new char[16];
        for (char c = 'a'; c <= 'p'; ++c) {
            data[c-'a'] = c;
        }
        for (String op : input) {
            if (op.startsWith("s")) {
                int n = Integer.parseInt(op.substring(1));
                var data2 = new char[16];
                for (int i = 0; i < data.length; ++i) {
                    data2[(i+n)%data.length] = data[i];
                }
                data = data2;
            } else if (op.startsWith("x")) {
                String[] split = op.substring(1).split("/");
                int a = Integer.parseInt(split[0]);
                int b = Integer.parseInt(split[1]);
                char c = data[a];
                data[a] = data[b];
                data[b] = c;
            } else if (op.startsWith("p")) {
                char a = op.charAt(1);
                char b = op.charAt(3);
                int ai = index(data, a);
                int bi = index(data, b);
                char c = data[ai];
                data[ai] = data[bi];
                data[bi] = c;
            } else {
                throw new RuntimeException("Unknown operation: " + op);
            }
        }
        return new String(data);
    }

    private static int index(char[] data, char c) {
        for (int i = 0; i < data.length; ++i) {
            if (data[i] == c) {
                return i;
            }
        }
        throw new RuntimeException("Char " + c + " not found!");
    }
}
