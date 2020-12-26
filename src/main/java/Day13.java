import util.Util;

import java.util.List;

public class Day13 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        int[] scanners = new int[100];
        for (String s : input) {
            String[] split = s.split(": ");
            scanners[Integer.parseInt(split[0])] = 2*(Integer.parseInt(split[1])-1);
        }
        for (int i = 1; ; ++i) {
            boolean ok = true;
            for (int j = 0; j < scanners.length; ++j) {
                if (scanners[j] > 0 && (i+j)%(scanners[j]) == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return i;
            }
        }
    }

    private static int part1(List<String> input) {
        int[] scanners = new int[100];
        for (String s : input) {
            String[] split = s.split(": ");
            scanners[Integer.parseInt(split[0])] = Integer.parseInt(split[1])-1;
        }
        int sev = 0;
        int[] scanPos = new int[scanners.length];
        boolean[] scanUp = new boolean[scanners.length];
        for (int pos = 0; pos < scanners.length; ++pos) {
            if (scanners[pos] > 0) {
                if (scanPos[pos] == 0) {
                    sev += pos*(scanners[pos]+1);
                }
            }
            moveScanners(scanners, scanPos, scanUp);
        }
        return sev;
    }

    private static void moveScanners(int[] scanners, int[] scanPos, boolean[] scanUp) {
        for (int i = 0; i < scanners.length; ++i) {
            if (scanners[i] > 0) {
                scanPos[i] += (scanUp[i] ? -1 : 1);
                if (scanPos[i] == 0) {
                    scanUp[i] = false;
                } else if (scanPos[i] == scanners[i]) {
                    scanUp[i] = true;
                }
            }
        }
    }
}
