import java.util.*;

public class ComparableAndComparator {

    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(10);
        list.add(2);
        list.add(5);
        list.add(2);
        list.add(100);

        // System.out.println("Before sorting: " + list);

        Collections.sort(list, (a, b) -> {
            boolean aPrime = isPrime(a);
            boolean bPrime = isPrime(b);

            // negative → e1 comes BEFORE e2
            // 0 → same ordering
            // positive → e1 comes AFTER e2

            // Both are prime → ascending
            if (aPrime && bPrime) {
                return Integer.compare(a, b);
            }

            // Both are non-prime → descending
            if (!aPrime && !bPrime) {
                return Integer.compare(b, a);
            }

            // a is prime, b is non-prime
            if (aPrime) {
                return -1;
            }

            // a is non-prime, b is prime
            return 1;
        });

        // System.out.println("After sorting: " + list);

        ArrayList<UCAStudent> std = new ArrayList<>();
        std.add(new UCAStudent("Ketan", 9.85d));
        std.add(new UCAStudent("garvit", 1.85d));
        std.add(new UCAStudent("gshmit", 1.85d));

        Collections.sort(std);
        System.out.println(std);
    }
}

class UCAStudent implements Comparable<UCAStudent> {
    String name;
    double cgpa;

    public UCAStudent(String name, double cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }

    @Override
    public int compareTo(UCAStudent that) {
        if (this.cgpa == that.cgpa) {
            return this.name.compareTo(that.name);
        }

        return Double.compare(that.cgpa, this.cgpa);

    }

    @Override
    public String toString() {
        return name + " " + cgpa;
    }
}