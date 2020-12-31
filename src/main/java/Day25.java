import util.Util;

import java.util.*;

public class Day25 {

    public static void main(String[] args) {
        Util.verifySubmission();
        List<String> input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(0);
    }

    private static int part1(List<String> input) {
        char state = input.get(0).charAt(input.get(0).length()-2);
        int steps = Integer.parseInt(input.get(1).split(" ")[5]);
        Map<Character, Map<Integer, State>> states = new HashMap<>();
        for (int i = 3; i < input.size(); ++i) {
            char s = input.get(i).charAt(input.get(i).length()-2);
            ++i;
            Map<Integer, State> stateMap = new HashMap<>();
            for (int j = 0; j < 2; ++j) {
                int v = input.get(i).charAt(input.get(i).length()-2)-'0';
                ++i;
                int nv = input.get(i).charAt(input.get(i).length()-2)-'0';
                ++i;
                var m = input.get(i).endsWith("right.") ? 1 : -1;
                ++i;
                char ns = input.get(i).charAt(input.get(i).length()-2);
                ++i;
                stateMap.put(v, new State(nv, m, ns));
            }
            states.put(s, stateMap);
        }

        Set<Integer> reg = new HashSet<>();
        int p = 0;
        for (int i = 0; i < steps; ++i) {
            Map<Integer, State> stateMap = states.get(state);
            State s = stateMap.get(reg.contains(p) ? 1 : 0);
            if (s.newValue == 1) {
                reg.add(p);
            } else {
                reg.remove(p);
            }
            p += s.move;
            state = s.nextState;
        }
        return reg.size();
    }

    private static class State {
        private final int newValue, move;
        private final char nextState;

        public State(int newValue, int move, char nextState) {
            this.newValue = newValue;
            this.move = move;
            this.nextState = nextState;
        }
    }
}
