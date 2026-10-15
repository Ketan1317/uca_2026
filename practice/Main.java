import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static int largestSubsequence(int[] arr) {
        int[] freq = new int[32];
        for(int i=31;i>=0;i--){
            for(int j=0;j<arr.length;j++){
                int bit = arr[j]&1;
                arr[j] = arr[j] >> 1;
                if(bit == 1){
                    freq[i]++;
                }
            }
        }

        int ans = 0;
        for(int x : freq){
            ans = Math.max(ans,x);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());
        int[] arr = new int[n];
        line = br.readLine();
        if (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < n; i++) {
                if (st.hasMoreTokens()) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }
            }
        }
        System.out.println(largestSubsequence(arr));
    }
}