import util.Util;

import java.util.List;

public class Day23 {

    public static void main(String[] args) {
        Util.verifySubmission();
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        int b = Integer.parseInt(input.get(0).split(" ")[2]);
        b *= Integer.parseInt(input.get(4).split(" ")[2]);
        b -= Integer.parseInt(input.get(5).split(" ")[2]);
        int c = b-Integer.parseInt(input.get(7).split(" ")[2]);;
        int count = 0;
        for (; b <= c; b += 17) {
            for (int i = 2; i*i < b; ++i) {
                if (b%i == 0) {
                    ++count;
                    break;
                }
            }
        }
        return count;
    }

    private static int part1(List<String> input) {
        var count = 0;
        int c = 0;
        int[] reg = new int[8];
        while (c >= 0 && c < input.size()) {
            String[] ins = input.get(c).split(" ");
            switch (ins[0]) {
                case "set":
                    reg[ins[1].charAt(0)-'a'] = getValue(reg, ins[2]);
                    ++c;
                    break;
                case "sub":
                    reg[ins[1].charAt(0)-'a'] -= getValue(reg, ins[2]);
                    ++c;
                    break;
                case "mul":
                    reg[ins[1].charAt(0)-'a'] *= getValue(reg, ins[2]);
                    ++c;
                    ++count;
                    break;
                case "jnz":
                    if (getValue(reg, ins[1]) != 0) {
                        c += getValue(reg, ins[2]);
                    } else {
                        ++c;
                    }
                    break;
            }
        }
        return count;
    }

    private static int getValue(int[] reg, String in) {
        if (in.charAt(0) >= 'a' && in.charAt(0) <= 'z') {
            return reg[in.charAt(0)-'a'];
        }
        return Integer.parseInt(in);
    }
}
