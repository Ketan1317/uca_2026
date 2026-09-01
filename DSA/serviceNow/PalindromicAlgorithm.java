public class PalindromicAlgorithm {
    public static void main(String[] args) {
        String s = "fhaigh";
        // count frequency
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // count odd chars
        int odd = 0;
        for (int f : freq) {
            if (f % 2 == 1) {
                odd++;
            }
        }
        int changes = odd / 2;
        int i = 0;
        int j = 25;

        // convert higher odd chars to lower chars 
        while (changes > 0) {
            while (i <= 25 && freq[i] % 2 == 0) {
                i++;
            }
            while (j >= 0 && freq[j] % 2 == 0) {
                j--;
            }

            if (i >= j)
                break;

            freq[i]++;
            freq[j]--;
            changes--;
        }

        StringBuilder sb = new StringBuilder();
        char mid = '\0';
        // check odd char that will be in the middle and build string
        for (int k = 0; k < 26; k++) {
            if (freq[k] % 2 == 1) {
                mid = (char) ('a' + k);
            }

            for (int l = 1; l <= freq[k] / 2; l++) {
                sb.append((char) ('a' + k));
            }
        }

        // build answer
        StringBuilder ans = new StringBuilder(sb);
        if (mid != '\0') {
            ans.append(mid);
        }
        ans.append(sb.reverse());

        System.out.println(ans.toString());
    }
}
