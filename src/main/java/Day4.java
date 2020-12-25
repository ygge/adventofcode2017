import util.ParseUtil;
import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String[] args) {
        List<String> input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        return ParseUtil.sum(input, Day4::valid);
    }

    private static Integer valid(String row) {
        var list = Arrays.stream(row.split(" ")).map(Password::new).collect(Collectors.toList());
        return new HashSet<>(list).size() == list.size() ? 1 : 0;
    }

    private static int part1(List<String> input) {
        int sum = 0;
        for (String s : input) {
            List<String> words = Arrays.asList(s.split(" "));
            Set<String> seen = new HashSet<>(words);
            if (seen.size() == words.size()) {
                ++sum;
            }
        }
        return sum;
    }

    private static class Password {
        int[] chars = new int[26];
        public Password(String word) {
            for (int i = 0; i < word.length(); ++i) {
                ++chars[word.charAt(i)-'a'];
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Password password = (Password) o;
            return Arrays.equals(chars, password.chars);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(chars);
        }
    }
}
