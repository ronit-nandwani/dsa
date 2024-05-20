package advanced.dp;

import java.util.Arrays;

public class DPLeetCode {
    // 664. Strange Printer
    // Solved
    // Hard
    // Topics
    // Companies
    // There is a strange printer with the following two special properties:

    // The printer can only print a sequence of the same character each time.
    // At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
    // Given a string s, return the minimum number of turns the printer needed to print it.

    

    // Example 1:

    // Input: s = "aaabbb"
    // Output: 2
    // Explanation: Print "aaa" first and then print "bbb".
    // Example 2:

    // Input: s = "aba"
    // Output: 2
    // Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
    

    // Constraints:

    // 1 <= s.length <= 100
    // s consists of lowercase English letters.

    // Solution by me - 14 ms
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // Initialize the dp array
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill the dp array
        for (int len = 2; len <= n; len++) { // len is the length of the substring
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = len; // Start with the worst case
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1]; // Extend the last character's printing
                } else {
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        
        return dp[0][n - 1];
    }

    // Faster Solution - 4 ms
    int[] nextOccurrence;
    int n;
    public int strangePrinterFaster(String s) {
       
       computeNextOccurrence(s);

       return compute(0, n - 1, new Integer[n][n]);
    }

    int compute(int start, int end, Integer[][] memo){
        if(start > end) return 0;
        if(memo[start][end] != null) return memo[start][end];

        int min = 1 + compute(start + 1, end, memo);
        
        int nextPos =  nextOccurrence[start];
        while(nextPos != -1 && nextPos <= end){
            int cur = compute(start, nextPos - 1, memo) + compute(nextPos + 1, end, memo);
            min = Math.min(min, cur);
            nextPos = nextOccurrence[nextPos];
        }

        return memo[start][end] = min;
    }
    void computeNextOccurrence(String s){
        var sb = new StringBuilder();
        sb.append(s.charAt(0));

        for(int i = 1 ; i < s.length(); i++)
            if(s.charAt(i) != s.charAt(i-1)) sb.append(s.charAt(i));

        n = sb.length();

        nextOccurrence = new int[n];
        int[] map = new int[26];

        Arrays.fill(map, -1);

        for(int i = n-1; i >= 0; i--){
            int ind = sb.charAt(i) - 'a';
            nextOccurrence[i] = map[ind];
            map[ind] = i;
        }
    }
}
