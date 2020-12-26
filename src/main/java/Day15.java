import util.Util;

import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        List<String> input = Util.readStrings();
        int a = Integer.parseInt(input.get(0).split(" ")[4]);
        int b = Integer.parseInt(input.get(1).split(" ")[4]);
        //Util.submitPart1(part1(a, b));
        Util.submitPart2(part2(a, b));
    }

    private static int part2(int a, int b) {
        long aa = a;
        long bb = b;
        long fa = 16807;
        long fb = 48271;
        long mod = 2147483647;
        int same = 0;
        for (int i = 0; i < 5000000; ++i) {
            do {
                aa = (aa*fa)%mod;
            } while ((aa&0x3) > 0);
            do {
                bb = (bb*fb)%mod;
            } while ((bb&0x7) > 0);
            if ((aa&0xffff) == (bb&0xffff)) {
                ++same;
            }
        }
        return same;
    }

    private static int part1(int a, int b) {
        long aa = a;
        long bb = b;
        long fa = 16807;
        long fb = 48271;
        long mod = 2147483647;
        int same = 0;
        for (int i = 0; i < 40000000; ++i) {
            aa = (aa*fa)%mod;
            bb = (bb*fb)%mod;
            if ((aa&0xffff) == (bb&0xffff)) {
                ++same;
            }
        }
        return same;
    }
}
