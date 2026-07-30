public class LCS {
    public static void main(String[] args) {
        System.out.println(lcs("abcde","ace"));
    }
    public static String lcs( String a, String b) {
     int n = a.length();
     int m = b.length();
     int[][] dp = new int[n+1][m+1];
     for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(a.charAt(i-1) == b.charAt(j-1)){
                dp[i][j] = 1 + dp[i-1][j-1];
            }
            else{
                dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
     }

     int x = n;
     int y = m;
     char[] ans = new char[dp[n][m]];
     int k = dp[n][m]-1;
     while(x > 0 && y > 0){
        if(a.charAt(x-1) == b.charAt(y-1)){
            ans[k--] = a.charAt(x-1);
            x--;
            y--;
        }
        else{
            if(dp[x-1][y] > dp[x][y-1]){
                x--;
            }
            else{
                y--;
            }
        }
     }
     return String.valueOf(ans);
    }
}
