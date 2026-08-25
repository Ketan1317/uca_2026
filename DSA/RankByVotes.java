import java.util.*;
class RankByVotes {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] freq = new int[26][n];

        for (String s : votes) {
            for (int i = 0; i < n; i++) {
                freq[s.charAt(i) - 'A'][i]++;
            }
        }

        Character[] temp = new Character[n];
        for (int i = 0; i < n; i++) {
            temp[i] = votes[0].charAt(i);
        }

        Arrays.sort(temp, (a, b) -> {
            for (int i = 0; i < n; i++) {
                if (freq[a - 'A'][i] != freq[b - 'A'][i]) {
                    return freq[b - 'A'][i] - freq[a - 'A'][i];
                }
            }
            return a - b;
        });

        StringBuilder ans = new StringBuilder();
        for (char ch : temp) {
            ans.append(ch);
        }
        return ans.toString();
    }
}