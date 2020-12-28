import util.Util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Day18 {

    public static void main(String[] args) {
        List<String> input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static long part2(List<String> input) {
        var state1 = new State(0);
        var state2 = new State(1);
        long times1 = 0;
        long times2 = 0;
        while (!state2.terminated) {
            times1 += run(state1, state2.values, input);
            times2 += run(state2, state1.values, input);
            if (state1.values.isEmpty() && state2.values.isEmpty()) {
                break;
            }
        }
        return times2;
    }

    private static int run(State state, Deque<Long> values, List<String> input) {
        if (state.terminated) {
            return 0;
        }
        int times = 0;
        for (; state.index >= 0 && state.index < input.size(); ++state.index) {
            var split = input.get(state.index).split(" ");
            switch (split[0]) {
                case "snd":
                    state.values.add(getValue(state.reg, split[1]));
                    ++times;
                    break;
                case "set":
                    state.reg[split[1].charAt(0)-'a'] = getValue(state.reg, split[2]);
                    break;
                case "add":
                    state.reg[split[1].charAt(0)-'a'] += getValue(state.reg, split[2]);
                    break;
                case "mul":
                    state.reg[split[1].charAt(0)-'a'] *= getValue(state.reg, split[2]);
                    break;
                case "mod":
                    state.reg[split[1].charAt(0)-'a'] %= getValue(state.reg, split[2]);
                    break;
                case "rcv":
                    if (values.isEmpty()) {
                        return times;
                    }
                    state.reg[split[1].charAt(0)-'a'] = values.pollFirst();
                    break;
                case "jgz":
                    if (getValue(state.reg, split[1]) > 0) {
                        state.index += getValue(state.reg, split[2]) - 1;
                    }
                    break;
                default:
                    throw new RuntimeException("command " + split[0] + " not understood");
            }
        }
        state.terminated = true;
        return times;
    }

    private static long part1(List<String> input) {
        long[] reg = new long[26];
        long played = 0;
        for (int i = 0; i < input.size(); ++i) {
            var split = input.get(i).split(" ");
            System.out.println(i + " " + input.get(i));
            switch (split[0]) {
                case "snd":
                    played = getValue(reg, split[1]);
                    break;
                case "set":
                    reg[split[1].charAt(0)-'a'] = getValue(reg, split[2]);
                    break;
                case "add":
                    reg[split[1].charAt(0)-'a'] += getValue(reg, split[2]);
                    break;
                case "mul":
                    reg[split[1].charAt(0)-'a'] *= getValue(reg, split[2]);
                    break;
                case "mod":
                    reg[split[1].charAt(0)-'a'] %= getValue(reg, split[2]);
                    break;
                case "rcv":
                    if (getValue(reg, split[1]) != 0) {
                        return played;
                    }
                    break;
                case "jgz":
                    if (getValue(reg, split[1]) > 0) {
                        i += getValue(reg, split[2]);
                        --i;
                    }
                    break;
                default:
                    throw new RuntimeException("command " + split[0] + " not understood");
            }
        }
        throw new RuntimeException("program halted");
    }

    private static long getValue(long[] reg, String value) {
        if (value.length() == 1 && value.charAt(0) >= 'a' && value.charAt(0) <= 'z') {
            return reg[value.charAt(0)-'a'];
        }
        return Integer.parseInt(value);
    }

    private static class State {
        long[] reg;
        int index;
        boolean terminated = false;
        Deque<Long> values = new LinkedList<>();

        public State(int id) {
            reg = new long[26];
            index = 0;
            reg['p'-'a'] = id;
        }
    }
}
