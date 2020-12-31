import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day24 {

    public static void main(String[] args) {
        var input = Util.readStrings().stream()
                .map(Node::new)
                .collect(Collectors.toList());
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<Node> input) {
        Set<Node> seen = new HashSet<>();
        return calc2(0, input, seen).value;
    }

    private static Result calc2(int value, List<Node> input, Set<Node> seen) {
        Result best = new Result();
        for (Node node : input) {
            if (!seen.contains(node) && (node.a == value || node.b == value)) {
                seen.add(node);
                var result = new Result(calc2(node.a == value ? node.b : node.a, input, seen), node.a + node.b);
                if (result.len > best.len || (result.len == best.len && result.value > best.value)) {
                    best = result;
                }
                seen.remove(node);
            }
        }
        return best;
    }

    private static int part1(List<Node> input) {
        Set<Node> seen = new HashSet<>();
        return calc(0, input, seen);
    }

    private static int calc(int value, List<Node> input, Set<Node> seen) {
        int best = 0;
        for (Node node : input) {
            if (!seen.contains(node) && (node.a == value || node.b == value)) {
                seen.add(node);
                var v = node.a + node.b + calc(node.a == value ? node.b : node.a, input, seen);
                best = Math.max(best, v);
                seen.remove(node);
            }
        }
        return best;
    }

    private static class Node {
        private final int a, b;

        public Node(String row) {
            String[] split = row.split("/");
            a = Integer.parseInt(split[0]);
            b = Integer.parseInt(split[1]);
        }
    }

    private static class Result {
        private final int len, value;

        public Result() {
            this.len = 0;
            this.value = 0;
        }

        public Result(Result result, int value) {
            this.len = result.len + 1;
            this.value = result.value + value;
        }
    }
}
