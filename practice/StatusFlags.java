public class StatusFlags {

    public static boolean[] extractFlags(int statusByte) {
        boolean[] ans = new boolean[3];
        for (int i = 0; i < 3; i++) {
            int bit = statusByte & 1;
            ans[i] = bit == 1;
            statusByte = statusByte >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] testCases = { 5, 2, 0, 7, 4, 3, 8 };

        for (int status : testCases) {
            boolean[] flags = extractFlags(status);

            System.out.println(
                    "Input: " + status +
                            " -> Output: " + java.util.Arrays.toString(flags));
        }
    }
}

// Input: 5 -> Output: [true, false, true]
// Input: 2 -> Output: [false, true, false]
// Input: 0 -> Output: [false, false, false]
// Input: 7 -> Output: [true, true, true]
// Input: 4 -> Output: [false, false, true]
// Input: 3 -> Output: [true, true, false]
// Input: 8 -> Output: [false, false, false]

// More generally, for (n) loop iterations:
// - Total processes at the end: (2^n), assuming every fork() succeeds.
// - Total executions of printf: (2 + 4 + ... + 2^n = 2^{n+1}-2).