import util.Util;

public class Day1 {

    public static void main(String[] args) {
        var num = Util.readString();
        Util.submitPart1(part1(num));
        Util.submitPart2(part2(num));
    }

    private static int part2(String num) {
        int sum = 0;
        for (int i = 0; i < num.length(); ++i) {
            if (num.charAt(i) == num.charAt((i+num.length()/2)%num.length())) {
                sum += num.charAt(i)-'0';
            }
        }
        return sum;
    }

    private static int part1(String num) {
        int sum = num.charAt(0) == num.charAt(num.length()-1) ? num.charAt(0)-'0' : 0;
        for (int i = 1; i < num.length(); ++i) {
            if (num.charAt(i-1) == num.charAt(i)) {
                sum += num.charAt(i)-'0';
            }
        }
        return sum;
    }
}
