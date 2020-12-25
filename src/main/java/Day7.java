import util.Util;

import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Map<String, Node> nodes = new HashMap<>();
        var root = buildTree(nodes, input);
        Util.submitPart1(root.name);
        Util.submitPart2(part2(root, nodes));
    }

    private static int part2(Node root, Map<String, Node> nodes) {
        return calc(root, nodes);
    }

    private static int calc(Node node, Map<String, Node> nodes) {
        Map<Integer, List<String>> weights = new HashMap<>();
        for (String child : node.children) {
            int w = calcWeight(child, nodes);
            if (!weights.containsKey(w)) {
                weights.put(w, new ArrayList<>());
            }
            weights.get(w).add(child);
        }
        if (weights.size() > 1) {
            for (Map.Entry<Integer, List<String>> entry : weights.entrySet()) {
                var value = entry.getValue();
                if (value.size() == 1) {
                    Node checkNode = nodes.get(value.get(0));
                    var w = calc(checkNode, nodes);
                    if (w == -1) {
                        for (Integer otherWeight : weights.keySet()) {
                            if (!otherWeight.equals(entry.getKey())) {
                                return otherWeight - (entry.getKey()-checkNode.weight);
                            }
                        }
                    }
                    return w;
                }
            }
        }
        return -1;
    }

    private static int calcWeight(String node, Map<String, Node> nodes) {
        int w = nodes.get(node).weight;
        for (String child : nodes.get(node).children) {
            w += calcWeight(child, nodes);
        }
        return w;
    }

    private static Node buildTree(Map<String, Node> nodes, List<String> input) {
        Set<String> children = new HashSet<>();
        for (String s : input) {
            String[] split = s.split(" ");
            String name = split[0];
            int w = Integer.parseInt(split[1].substring(1, split[1].length()-1));
            Node node = new Node(name, w);
            nodes.put(name, node);
            for (int i = 3; i < split.length; ++i) {
                String child = split[i];
                if (child.endsWith(",")) {
                    child = child.substring(0, child.length()-1);
                }
                node.children.add(child);
                children.add(child);
            }
        }
        Set<String> allNames = new HashSet<>(nodes.keySet());
        allNames.removeAll(children);
        return nodes.get(allNames.iterator().next());
    }

    private static class Node {
        final String name;
        final int weight;
        final List<String> children = new ArrayList<>();

        public Node(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }
    }
}
