public class Fib {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(helper(n));
    }

    public static int helper(int n) {
        int a = 0;
        int b = 1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (a % 2 == 0) {
                sum += a;
            }

            int temp = a + b;
            a = b;
            b = temp;
        }

        return sum;
    }
}
