import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6 {

    public static void main(String[] args) {
        var input = Util.readString().split("\t");
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String[] input) {
        int[] banks = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            banks[i] = Integer.parseInt(input[i]);
        }
        var state = new State(banks);
        Set<State> seen = new HashSet<>();
        while (seen.add(state)) {
            state = new State(state);
        }
        seen.clear();
        int count = 0;
        while (seen.add(state)) {
            state = new State(state);
            ++count;
        }
        return count;
    }

    private static int part1(String[] input) {
        int[] banks = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            banks[i] = Integer.parseInt(input[i]);
        }
        var state = new State(banks);
        int count = 0;
        Set<State> seen = new HashSet<>();
        while (seen.add(state)) {
            state = new State(state);
            ++count;
        }
        return count;
    }

    private static class State {
        private final int[] banks;

        public State(int[] banks) {
            this.banks = banks;
        }

        public State(State state) {
            banks = new int[state.banks.length];
            System.arraycopy(state.banks, 0, banks, 0, banks.length);
            int index = 0;
            for (int i = 1; i < banks.length; ++i) {
                if (banks[i] > banks[index]) {
                    index = i;
                }
            }
            int v = banks[index];
            banks[index] = 0;
            while (v > 0) {
                index = (index+1)%banks.length;
                ++banks[index];
                --v;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(banks, state.banks);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(banks);
        }
    }
}
