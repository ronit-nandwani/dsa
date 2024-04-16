package advanced.dp;

import java.util.Arrays;

public class DP4 {
    // Coin sum infinite

    //     You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.
    // NOTE:
    // Coins in set A will be unique. Expected space complexity of this problem is O(B).
    // The answer can overflow. So, return the answer % (106 + 7).

    // Problem Constraints
    // 1 <= A <= 500
    // 1 <= A[i] <= 1000
    // 1 <= B <= 50000

    // Input Format
    // First argument is an integer array A representing the set.
    // Second argument is an integer B.

    // Output Format
    // Return an integer denoting the number of ways.

    // Example Input
    // Input 1:

    // A = [1, 2, 3]
    // B = 4
    // Input 2:

    // A = [10]
    // B = 10
    // Example Output
    // Output 1:
    // 4
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // The 4 possible ways are:
    // {1, 1, 1, 1}
    // {1, 1, 2}
    // {2, 2}
    // {1, 3} 
    // Explanation 2:

    // There is only 1 way to make sum 10.

    public int coinchange2(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[B+1];
        dp[0] = 1;
        for(int i=0;i<n;i++) {
            for(int j=1;j<=B;j++) {
                if(A[i] <= j)
                    dp[j] += dp[j-A[i]];
                    dp[j] = dp[j]%1000007;
            }
        }
        return dp[B];
    }

    // Solution by team
    public class Solution {
        public int coinchange2(int[] A, int B) {
            int[] num_ways = new int[B + 1];
            int Mod = 1000000 + 7;
            int i, j, m = A.length;
            Arrays.fill(num_ways, 0);
            num_ways[0] = 1;

            for (i = 0; i < m; i++) {
                for (j = A[i]; j <= B; j++) {
                    num_ways[j] += num_ways[j - A[i]];
                    num_ways[j] %= Mod;

                }
            }
            return num_ways[B];
        }
    }

    // 0-1 Knapsack 2
    // Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
    // Also given an integer C which represents knapsack capacity.

    // Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

    // NOTE: You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

    // Problem Constraints
    // 1 <= N <= 500

    // 1 <= C, B[i] <= 106

    // 1 <= A[i] <= 50

    // Input Format
    // First argument is an integer array A of size N denoting the values on N items.

    // Second argument is an integer array B of size N denoting the weights on N items.

    // Third argument is an integer C denoting the knapsack capacity.

    // Output Format
    // Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

    // Example Input
    // Input 1:
    //  A = [6, 10, 12]
    //  B = [10, 20, 30]
    //  C = 50
    // Input 2:
    //  A = [1, 3, 2, 4]
    //  B = [12, 13, 15, 19]
    //  C = 10
    // Example Output
    // Output 1:
    //  22
    // Output 2:
    //  0
    // Example Explanation
    // Explanation 1:
    //  Taking items with weight 20 and 30 will give us the maximum value i.e 10 + 12 = 22
    // Explanation 2:
    //  Knapsack capacity is 10 but each item has weight greater than 10 so no items can be considered in the knapsack therefore answer is 0.

    public int oTo1Knapsack2(int[] A, int[] B, int C) {
        int n = A.length;
        int h = 0;
        for (int i = 0; i < n; i++) h += A[i];
        int[][] dp = new int[n+1][h+1];
        for(int j=0;j<=h;j++) {
            if(j == 0) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = Integer.MAX_VALUE - 1000000000;
            }
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=h;j++) {
                if(j>=A[i-1] ){
                    dp[i][j] = Math.min(dp[i-1][j], B[i-1]+dp[i-1][j-A[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int result = 0;
        for (int j =  h; j >= 0; j--) {
            if (dp[n][j] <= C) {
                result = Math.max(result, j);
            }
        }

        return result;
    }

    // Solution by team
    public class Solution {
        public int solve(int[] A, int[] B, int C) {
            int n = A.length;
            int mxval = 50 * n;
            int[] dp = new int[mxval + 1];
            for (int i = 0; i < mxval + 1; i++) {
                dp[i] = 1000000000;
            }
            dp[0] = 0;
            for (int i = 0; i < n; i++) {
                for (int val = mxval; val >= A[i]; val--) {
                    dp[val] = Math.min(dp[val], B[i] + dp[val - A[i]]);
                }
            }
            int ans = 0;
            for (int val = mxval; val >= 0; val--) {
                if (dp[val] <= C) {
                    ans = val;
                    break;
                }
            }
            return ans;
        }
    }
}
