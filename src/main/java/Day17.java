import util.Util;

public class Day17 {

    public static void main(String[] args) {
        Util.verifySubmission();
        int v = Util.readInt();
        Util.submitPart1(part1(v));
        Util.submitPart2(part2(v));
    }

    private static int part2(int v) {
        int len = 2;
        int pos = 1;
        int ans = 1;
        for (int i = 2; i <= 50000000; ++i) {
            pos = (pos+v)%len;
            if (pos == 0) {
                ans = i;
            }
            ++len;
            ++pos;
        }
        return ans;
    }

    private static int part1(int v) {
        Node node = new Node(0);
        node.next = node;
        for (int i = 1; i <= 2017; ++i) {
            for (int j = 0; j < v; ++j) {
                node = node.next;
            }
            Node nn = new Node(i);
            nn.next = node.next;
            node.next = nn;
            node = nn;
        }
        return node.next.value;
    }

    private static class Node {
        int value;
        Node next;

        public Node(int v) {
            value = v;
        }
    }
}
