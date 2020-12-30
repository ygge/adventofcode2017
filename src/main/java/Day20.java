import util.MultiDimPos;
import util.Util;

import java.util.*;

public class Day20 {

    public static void main(String[] args) {
        Util.verifySubmission();
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        List<Particle> list = new ArrayList<>();
        int index = 0;
        for (String row : input) {
            String[] split = row.split(", ");
            list.add(new Particle(index++, parse(split[0]), parse(split[1]), parse(split[2])));
        }
        for (int i = 0; i < 10000; ++i) {
            List<Particle> toRemove = new ArrayList<>();
            Map<Particle, Particle> seen = new HashMap<>();
            for (Particle particle : list) {
                particle.move();
                if (seen.containsKey(particle)) {
                    toRemove.add(particle);
                    toRemove.add(seen.get(particle));
                } else {
                    seen.put(particle, particle);
                }
            }
            list.removeAll(toRemove);
            list.sort(Comparator.comparingInt(Particle::p));
        }
        return list.size();
    }

    private static int part1(List<String> input) {
        var list = new ArrayList<Particle>();
        int index = 0;
        for (String row : input) {
            String[] split = row.split(", ");
            list.add(new Particle(index++, parse(split[0]), parse(split[1]), parse(split[2])));
        }
        list.sort(Comparator.comparingInt(Particle::a).thenComparingInt(Particle::v).thenComparingInt(Particle::p));
        return list.get(0).id;
    }

    private static MultiDimPos parse(String str) {
        String[] elements = str.substring(3, str.length() - 1).split(",");
        return new MultiDimPos(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
    }

    private static class Particle {
        int id;
        MultiDimPos p, v, a;

        public Particle(int id, MultiDimPos p, MultiDimPos v, MultiDimPos a) {
            this.id = id;
            this.p = p;
            this.v = v;
            this.a = a;
        }

        public int p() {
            return Math.abs(p.dim[0])+Math.abs(p.dim[1])+Math.abs(p.dim[2]);
        }

        public int v() {
            return Math.abs(v.dim[0])+Math.abs(v.dim[1])+Math.abs(v.dim[2]);
        }

        public int a() {
            return Math.abs(a.dim[0])+Math.abs(a.dim[1])+Math.abs(a.dim[2]);
        }

        public void move() {
            v.dim[0] += a.dim[0];
            v.dim[1] += a.dim[1];
            v.dim[2] += a.dim[2];
            p.dim[0] += v.dim[0];
            p.dim[1] += v.dim[1];
            p.dim[2] += v.dim[2];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Particle particle = (Particle) o;
            return Objects.equals(p, particle.p);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p);
        }
    }
}
