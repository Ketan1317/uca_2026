
import java.util.*;

public class ConsecutiveSequence {

    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 6, 3, 4, 2};
        Set<Integer> set = new HashSet<>();

        for (int x : arr) {
            set.add(x);
        }
        System.out.println(helper(arr, set));
    }

    public static int helper(int[] arr, Set<Integer> set) {
        int ans = 0;
        for (int number : arr) {
            if (!set.contains(number - 1)) {
                int cnt = 1;
                int num = number;
                while (set.contains(num + 1)) {
                    cnt++;
                    num++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
