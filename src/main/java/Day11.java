import util.Pos;
import util.Util;

import java.util.*;

public class Day11 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        Pos goal = new Pos(0, 0);
        Set<Pos> allPos = new HashSet<>();
        for (String move : input.split(",")) {
            if (move.equals("n")) {
                goal = new Pos(goal.x, goal.y-2);
            } else if (move.equals("s")) {
                goal = new Pos(goal.x, goal.y+2);
            } else if (move.startsWith("n")) {
                if (move.charAt(1) == 'w') {
                    goal = new Pos(goal.x-1, goal.y-1);
                } else {
                    goal = new Pos(goal.x+1, goal.y-1);
                }
            } else {
                if (move.charAt(1) == 'w') {
                    goal = new Pos(goal.x-1, goal.y+1);
                } else {
                    goal = new Pos(goal.x+1, goal.y+1);
                }
            }
            allPos.add(goal);
        }
        Set<Pos> seen = new HashSet<>();
        Deque<State> que = new LinkedList<>();
        que.add(new State(new Pos(0, 0), 0));
        while (!que.isEmpty()) {
            State state = que.pollFirst();
            if (!seen.add(state.pos)) {
                continue;
            }
            allPos.remove(state.pos);
            if (allPos.size() == 0) {
                return state.moves;
            }
            for (Pos neighbour : neighbours(state.pos)) {
                que.add(new State(neighbour, state.moves+1));
            }
        }
        throw new RuntimeException("Goal not found!");
    }

    private static int part1(String input) {
        Pos goal = new Pos(0, 0);
        for (String move : input.split(",")) {
            if (move.equals("n")) {
                goal = new Pos(goal.x, goal.y-2);
            } else if (move.equals("s")) {
                goal = new Pos(goal.x, goal.y+2);
            } else if (move.startsWith("n")) {
                if (move.charAt(1) == 'w') {
                    goal = new Pos(goal.x-1, goal.y-1);
                } else {
                    goal = new Pos(goal.x+1, goal.y-1);
                }
            } else {
                if (move.charAt(1) == 'w') {
                    goal = new Pos(goal.x-1, goal.y+1);
                } else {
                    goal = new Pos(goal.x+1, goal.y+1);
                }
            }
        }
        Set<Pos> seen = new HashSet<>();
        Deque<State> que = new LinkedList<>();
        que.add(new State(new Pos(0, 0), 0));
        while (!que.isEmpty()) {
            State state = que.pollFirst();
            if (!seen.add(state.pos)) {
                continue;
            }
            if (state.pos.equals(goal)) {
                return state.moves;
            }
            for (Pos neighbour : neighbours(state.pos)) {
                que.add(new State(neighbour, state.moves+1));
            }
        }
        throw new RuntimeException("Goal not found!");
    }

    private static List<Pos> neighbours(Pos pos) {
        return Arrays.asList(
                new Pos(pos.x, pos.y-2),
                new Pos(pos.x, pos.y+2),
                new Pos(pos.x-1, pos.y-1),
                new Pos(pos.x-1, pos.y+1),
                new Pos(pos.x+1, pos.y-1),
                new Pos(pos.x+1, pos.y-1)
        );
    }

    public static class State {
        Pos pos;
        int moves;

        public State(Pos pos, int moves) {
            this.pos = pos;
            this.moves = moves;
        }
    }
}
