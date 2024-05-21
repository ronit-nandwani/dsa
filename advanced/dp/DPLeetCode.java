package advanced.dp;

import java.util.Arrays;

public class DPLeetCode {
    // 650. 2 Keys Keyboard
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:

    // Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
    // Paste: You can paste the characters which are copied last time.
    // Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

    

    // Example 1:

    // Input: n = 3
    // Output: 3
    // Explanation: Initially, we have one character 'A'.
    // In step 1, we use Copy All operation.
    // In step 2, we use Paste operation to get 'AA'.
    // In step 3, we use Paste operation to get 'AAA'.
    // Example 2:

    // Input: n = 1
    // Output: 0
    

    // Constraints:

    // 1 <= n <= 1000

    // Solution by me - 0 ms
    public int minSteps(int n) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                result += i;
                n /= i;
            }
        }
        return result;
    }

    // Faster Solution - 0 ms
    public int minStepsFaster(int n) {
        int steps = 0;
        int maxDivider;
        while(n>1){
            maxDivider = getMaxDivider(n);
            steps = n / maxDivider + steps;
            n = maxDivider;
        }
        return steps;
    }
    private int getMaxDivider(int count){
        int maxDivider = 1;
        for(int i = maxDivider;i<count;i++){
            if(count%i==0){
                maxDivider = i;
            }
        }
        return maxDivider;
    }

    // ---------------------------------------------------

    // 1140. Stone Game II
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

    // Alice and Bob take turns, with Alice starting first.

    // On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

    // The game continues until all the stones have been taken.

    // Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

    

    // Example 1:

    // Input: piles = [2,7,9,4,4]

    // Output: 10

    // Explanation:

    // If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
    // If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
    // So we return 10 since it's larger.

    // Example 2:

    // Input: piles = [1,2,3,4,5,100]

    // Output: 104

    

    // Constraints:

    // 1 <= piles.length <= 100
    // 1 <= piles[i] <= 104


    // Solution by me - 8 ms
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];

        // Calculate the suffix sum
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        // DP table to store results of subproblems
        int[][] dp = new int[n][n + 1];

        // Start filling the DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = suffixSum[i];
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        dp[i][m] = Math.max(dp[i][m], suffixSum[i] - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }

        return dp[0][1];
    }

    // Faster Solution - 2 ms

    public int stoneGameIIFaster(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] suffixSum = new int[n];

        // Calculate suffix sums
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        return dfs(piles, dp, suffixSum, 0, 1);
    }

    private int dfs(int[] piles, int[][] dp, int[] suffixSum, int i, int M) {
        if (i == piles.length)
            return 0;
        if (2 * M >= piles.length - i)
            return suffixSum[i];
        if (dp[i][M] != 0)
            return dp[i][M];

        int min = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * M; x++) {
            min = Math.min(min, dfs(piles, dp, suffixSum, i + x, Math.max(M, x)));
        }

        dp[i][M] = suffixSum[i] - min;
        return dp[i][M];
    }


    // ------------------------------------------


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
