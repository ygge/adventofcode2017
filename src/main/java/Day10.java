import util.Util;

import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        List<Integer> input = Util.readIntLists(",").get(0);
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(Util.readString()));
    }

    private static String part2(String inputString) {
        var data = new int[256];
        for (int i = 1; i < data.length; ++i) {
            data[i] = i;
        }
        var input = new int[inputString.length()+5];
        for (int i = 0; i < inputString.length(); ++i) {
            input[i] = inputString.charAt(i);
        }
        input[inputString.length()] = 17;
        input[inputString.length()+1] = 31;
        input[inputString.length()+2] = 73;
        input[inputString.length()+3] = 47;
        input[inputString.length()+4] = 23;
        int curr = 0;
        int skip = 0;
        for (int t = 0; t < 64; ++t) {
            for (int len : input) {
                for (int i = 0; i < len/2; ++i) {
                    int p1 = (curr+i)%data.length;
                    int p2 = (curr+len-1-i)%data.length;
                    int c = data[p1];
                    data[p1] = data[p2];
                    data[p2] = c;
                }
                curr = (curr+len+skip)%data.length;
                ++skip;
            }
        }
        int[] hash = new int[16];
        for (int i = 0; i < data.length; ++i) {
            hash[i/16] ^= data[i];
        }
        var sb = new StringBuilder();
        for (int h : hash) {
            String str = Integer.toHexString(h);
            if (str.length() == 1) {
                sb.append(0);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private static int part1(List<Integer> input) {
        var data = new int[256];
        for (int i = 1; i < data.length; ++i) {
            data[i] = i;
        }
        int curr = 0;
        int skip = 0;
        for (Integer len : input) {
            for (int i = 0; i < len/2; ++i) {
                int p1 = (curr+i)%data.length;
                int p2 = (curr+len-1-i)%data.length;
                int c = data[p1];
                data[p1] = data[p2];
                data[p2] = c;
            }
            curr = (curr+len+skip)%data.length;
            ++skip;
        }
        return data[0]*data[1];
    }
}
