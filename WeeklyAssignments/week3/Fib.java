
import java.util.*;

public class Fib {

    public static int logarithimic(int n) {
        int a = 0; // pos 0
        int b = 1; // pos 1
        int sum = 0;
        if (n < 2) {
            return 0;
        }
        while (true) {
            int temp = a + b;
            a = b;
            b = temp;
            if (b > n) {
                break;
            }

            if (b % 2 == 0) {
                sum += b;
            }
        }
        return sum;
    }

    public static int rec(int n, Integer[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != null) {
            return dp[n];
        }
        return dp[n] = rec(n - 1, dp) + rec(n - 2, dp);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Sum by logarithimic approach:" + logarithimic(n));
        int sum = 0;
        Integer[] dp = new Integer[300];
        int num = 0;
        while(true) {
            int ans = rec(num++, dp);
            if(ans >  n) break;
            if (ans % 2 == 0) {
                sum += ans;
            }
        }
        System.out.println("Sum by recursive Approach:" + sum);
    }
}
