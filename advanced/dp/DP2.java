package advanced.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class DP2 {

    // Min Sum Path in Matrix

    // Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    // Return the minimum sum of the path.

    // NOTE: You can only move either down or right at any point in time.
    // Problem Constraints
    // 1 <= M, N <= 2000

    // -1000 <= A[i][j] <= 1000

    // Input Format
    // First and only argument is a 2-D grid A.

    // Output Format
    // Return an integer denoting the minimum sum of the path.

    // Example Input
    // Input 1:

    // A = [
    //     [1, 3, 2]
    //     [4, 3, 1]
    //     [5, 6, 1]
    //     ]
    // Input 2:

    // A = [
    //     [1, -3, 2]
    //     [2, 5, 10]
    //     [5, -5, 1]
    //     ]

    // Example Output
    // Output 1:

    // 8
    // Output 2:

    // -1

    // Example Explanation
    // Explanation 1:

    // The path will be: 1 -> 3 -> 2 -> 1 -> 1.
    // Input 2:

    // The path will be: 1 -> -3 -> 5 -> -5 -> 1.

    public int minPathSum(int[][] A) {
        int m = A.length;
        int n= A[0].length;
        int[] dp = new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0)
                    dp[j] = A[i][j];
                else if(i==0)
                    dp[j] = A[i][j] + dp[j-1];
                else if(j==0)
                    dp[j] = dp[j] + A[i][j];
                else
                    dp[j] = A[i][j] + Math.min(dp[j-1], dp[j]);
            }
        }
        return dp[n-1];
    }

    // Solution by team

    public class Solution {
        private int mem[][];
        private ArrayList < ArrayList < Integer >> A;
        public int minPathSum(ArrayList < ArrayList < Integer >> A) {
            int m, n;
            if (A == null || A.size() == 0 || A.get(0).size() == 0)
                return 0;
            m = A.size();
            n = A.get(0).size();
            mem = new int[m][n];
            for (int i = 0; i < m; i++)
                Arrays.fill(mem[i], Integer.MAX_VALUE);
            mem[0][0] = A.get(0).get(0);
            this.A = A;
            rec(m - 1, n - 1);
            return mem[m - 1][n - 1];
        }
    
        public int rec(int i, int j) {
            if (i < 0 || j < 0)
                return Integer.MAX_VALUE;
            if (mem[i][j] != Integer.MAX_VALUE)
                return mem[i][j];
            int res = rec(i - 1, j);
            res = Math.min(rec(i, j - 1), res);
            mem[i][j] = res + A.get(i).get(j);
            return mem[i][j];
        }
    }
    
    // N digit number

    // Find out the number of A digit positive numbers, whose digits on being added equals to a given number B.
    // Note that a valid number starts from digits 1-9 except the number 0 itself. i.e. leading zeroes are not allowed.
    // Since the answer can be large, output answer modulo 1000000007
    // Problem Constraints
    // 1 <= A <= 1000
    // 1 <= B <= 10000
    // Input Format
    // First argument is the integer A
    // Second argument is the integer B
    // Output Format
    // Return a single integer, the answer to the problem
    // Example Input
    // Input 1:
    // A = 2
    // B = 4
    // Input 2:
    // A = 1
    // B = 3
    // Example Output
    // Output 1:
    // 4
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // Valid numbers are {22, 31, 13, 40}
    // Hence output 4.
    // Explanation 2:
    // Only valid number is 3

    // My implementation pause 2
    public class Solution {
        int[][] dp;
        public int solve(int A, int B) {
            int ans = 0;
            dp = new int[A][B];
            for(int i=0;i<A;i++) {
                for(int j=0;j<B;j++) {
                    dp[i][j] = -1;
                }
            }
            for(int i=1;i<10;i++) {
                ans += rec(A-1, B-i);
                ans %= 1000000007;
            }
    
            return ans;
        }
    
        public int rec(int id, int sum) {
            if(sum < 0) {
                return 0;
            }
            if(id == 0 && sum == 0) {
                return 1;
            }
            if(id == 0) {
                return 0;
            }
    
            if(dp[id][sum]!=-1) {
                return dp[id][sum];
            }
    
            int ans = 0;
            for(int i=0;i<10;i++) {
                ans += rec(id-1, sum-i);
                ans %= 1000000007;
            }
            dp[id][sum]=ans;
            return dp[id][sum];
        }
    }
    
    // My implementation
    int[][] dp = new int[1][1];
    public int rec(int digit, int sum) {
        if(digit == 0 && sum == 0) return 1;
        if(digit == 0) return 0;
        if(dp[digit][sum] != -1) return dp[digit][sum];
        int ans = 0;
        for(int i=0;i<10;i++) {
            if(sum-i >= 0) {
                ans += rec(digit-1,sum-i);
                ans %= 1000000007;
            }
        }
        dp[digit][sum] = ans;
        return ans;
    }
    public int solve(int A, int B) {
        int ans = 0;
        dp = new int[A+1][B+1];
        for(int i=0;i<=A;i++)
        {
            for(int j=0;j<=B;j++)
            {
                dp[i][j]=-1;
            }
        }
        for(int i=1;i<10;i++) {
            if(B-i >= 0) {
                ans += rec(A-1,B-i);
                ans %= 1000000007;
            }
        }
        return ans;
    }
    // Solution by team
    // public class Solution {
    //     public int dp[][] = new int[1001][10001];
    //     int rec(int id, int sum) {
    //         if (sum < 0)
    //             return 0;
    //         if (id == 0 && sum == 0)
    //             return 1;
    //         if (id == 0)
    //             return 0;
    //         if (dp[id][sum] != -1)
    //             return dp[id][sum];
    //         int ans = 0;
    //         for (int i = 0; i < 10; i++) {
    //             ans += rec(id - 1, sum - i);
    //             ans %= 1000000007;
    //         }
    //         return dp[id][sum] = ans;
    //     }
    //     public int solve(int A, int B) {
    //         int ans = 0;
    //         for (int i = 0; i < A + 1; i++) {
    //             for (int j = 0; j < B + 1; j++)
    //                 dp[i][j] = -1;
    //         }
    //         for (int i = 1; i < 10; i++) {
    //             ans += rec(A - 1, B - i);
    //             ans %= 1000000007;
    //         }
    //         return ans;
    //     }
    // }


    // Dungeon Princess
    
    // The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
    // The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
    // Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
    // In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
    // Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health so that he is able to rescue the princess.
    // Problem Constraints
    // 1 <= M, N <= 500
    // -100 <= A[i] <= 100
    // Input Format
    // First and only argument is a 2D integer array A denoting the grid of size M x N.
    // Output Format
    // Return an integer denoting the knight's minimum initial health so that he is able to rescue the princess.
    // Example Input
    // Input 1:
    // A = [ 
    //     [-2, -3, 3],
    //     [-5, -10, 1],
    //     [10, 30, -5]
    //     ]
    // Input 2:
    // A = [ 
    //     [1, -1, 0],
    //     [-1, 1, -1],
    //     [1, 0, -1]
    //     ]
    // Example Output
    // Output 1:
    // 7
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // Initially knight is at A[0][0].
    // If he takes the path RIGHT -> RIGHT -> DOWN -> DOWN, the minimum health required will be 7.
    // At (0,0) he looses 2 health, so health becomes 5.
    // At (0,1) he looses 3 health, so health becomes 2.
    // At (0,2) he gains 3 health, so health becomes 5.
    // At (1,2) he gains 1 health, so health becomes 6.
    // At (2,2) he looses 5 health, so health becomes 1.
    // At any point, the health point doesn't drop to 0 or below. So he can rescue the princess with minimum health 7.
    
    // Explanation 2:

    // Take the path DOWN -> DOWN ->RIGHT -> RIGHT, the minimum health required will be 1.
    public int calculateMinimumHP(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] h = new int[n][m];
        for(int i=n-1;i>=0;--i) {
            for(int j=m-1;j>=0;j--) {
                if(i==(n-1) && j==(m-1)) {
                    h[i][j] = Math.max(1-A[i][j],1);
                } else if(i == (n-1)) {
                    h[i][j] = Math.max(h[i][j+1]-A[i][j],1);
                } else if(j == (m-1)) {
                    h[i][j] = Math.max(h[i+1][j]-A[i][j],1);
                } else {
                    h[i][j] = Math.max(Math.min(h[i][j+1],h[i+1][j])-A[i][j],1);
                }
            }
        }
        return h[0][0];
    }
    // Solution by team
    // public class Solution {
    // int dp[][];
    // ArrayList < ArrayList < Integer >> A;
    // int m, n;
    // public int calculateMinimumHP(ArrayList < ArrayList < Integer >> A) {
    //     if (A == null)
    //         return 0;
    //     m = A.size();
    //     n = A.get(0).size();
    //     dp = new int[m][n];
    //     this.A = A;
    //     for (int i = 0; i < m; i++)
    //         Arrays.fill(dp[i], -1);
    //     rec(0, 0);
    //     if (dp[0][0] <= 0)
    //         return 1;
    //     return dp[0][0];
    // }

    // public int rec(int row, int col) 
    //     if (row == m - 1 && col == n - 1) {
    //         int num = A.get(row).get(col);
    //         if (num < 0)
    //             return 1 - num;
    //         else
    //             return 1;
    //     }
    //     if (dp[row][col] != -1)
    //         return dp[row][col];
    //     int max = Integer.MAX_VALUE;
    //     int num = A.get(row).get(col);
    //     if (isValid(row + 1, col)) {
    //         max = rec(row + 1, col);
    //         max -= num;
    //         max = Math.max(1, max);
    //     }
    //     if (isValid(row, col + 1)) {
    //         int temp = rec(row, col + 1);
    //         temp -= num;
    //         temp = Math.max(1, temp);
    //         max = Math.min(temp, max);
    //     }
    //     return dp[row][col] = max;
    // }

    // public boolean isValid(int row, int col) {
    //     if (row < 0 || row >= m || col < 0 || col >= n)
    //         return false;
    //     return true;
    // }
    // }
}
