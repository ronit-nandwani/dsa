package advanced.dp;

import java.util.ArrayList;
import java.util.Scanner;

public class DP1 {
    // Max Sum without Adjacent Elements

    // Given a 2 x N grid of integers, A, your task is to choose numbers from the grid such that sum of these numbers is maximized. 
    // However, you cannot choose two numbers that are adjacent horizontally, vertically, or diagonally. 
    // Return the maximum possible sum.
    // Note: You are allowed to choose more than 2 numbers from the grid.
    // Problem Constraints
    // 1 <= N <= 20000
    // 1 <= A[i] <= 2000
    // Input Format
    // The first and the only argument of input contains a 2d matrix, A.
    // Output Format
    // Return an integer, representing the maximum possible sum.
    // Example Input
    // Input 1:
    // A = [   
    //         [1]
    //         [2]    
    //     ]
    // Input 2:
    // A = [   
    //         [1, 2, 3, 4]
    //         [2, 3, 4, 5]    
    //     ]
    // Example Output
    // Output 1:
    // 2
    // Output 2:
    // 8
    // Example Explanation
    // Explanation 1:
    // We will choose 2 (From 2nd row 1st column).
    // Explanation 2:
    // We will choose 3 (From 2nd row 2nd column) and 5 (From 2nd row 4th column).
    public int adjacent(int[][] A) {
        int n = A[0].length;
        int[] merged = new int[n];
        for(int i=0;i<n;i++) {
            merged[i] = Math.max(A[0][i],A[1][i]);
        }

        int[] res = new int[n];
        res[0] = merged[0];
        if(n>1) res[1] = Math.max(merged[0],merged[1]);
        
        for(int i=2;i<n;i++) {
            res[i] = Math.max(res[i-1],merged[i]+res[i-2]);
        }
        return res[n-1];
    }
    // Solution by team
    //     public class Solution {
    // public int adjacent(ArrayList < ArrayList < Integer >> A) {

    //     int n;
    //     int dp[];
    //     ArrayList < Integer > first, second;
    //     first = A.get(0);
    //     second = A.get(1);

    //     n = A.get(0).size();

    //     if (n == 0)
    //     return 0;

    //     dp = new int[n];
    //     dp[0] = Math.max(first.get(0), second.get(0));

    //     if (n < 2)
    //     return dp[0];

    //     dp[1] = Math.max(first.get(1), second.get(1));
    //     dp[1] = Math.max(dp[0], dp[1]);

    //     for (int i = 2; i < n; i++) {
    //     dp[i] = Math.max(first.get(i), second.get(i));
    //     dp[i] += dp[i - 2];
    //     dp[i] = Math.max(dp[i], dp[i - 1]);
    //     }

    //     return dp[n - 1];
    // }
    // }

    // Minimum No. of squares

    // Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.
    // Problem Constraints
    // 1 <= A <= 105
    // Input Format
    // First and only argument is an integer A.
    // Output Format
    // Return an integer denoting the minimum count.
    // Example Input
    // Input 1:
    // A = 6
    // Input 2:
    // A = 5
    // Example Output
    // Output 1:
    // 3
    // Output 2:
    // 2
    // Example Explanation
    // Explanation 1:
    // Possible combinations are : (12 + 12 + 12 + 12 + 12 + 12) and (12 + 12 + 22).
    // Minimum count of numbers, sum of whose squares is 6 is 3. 
    // Explanation 2:

    // We can represent 5 using only 2 numbers i.e. 12 + 22 = 5
    public int countMinSquares(int A) {
        int[] ans = new int[A+1];
        for(int i=0;i<=A;i++) {
            ans[i] = i;
        }
        ans[0] = 0;
        for(int i=1; i<=A ;++i) {
            for(int x=1;x*x<=i;++x) {
                ans[i] = Math.min(ans[i],1+ans[i-x*x]);
            }
        }
        return ans[A];
    }
    // Solution by team
    // public class Solution {
    //     public int countMinSquares(int n) {
    //       int[] dp = new int[n + 1];
      
    //       // simple base case assignment
    //       dp[0] = 0;
    //       dp[1] = 1;
      
    //       //finding optimal answer for every 2<=i<=N in bottom-up manner
    //       for (int i = 2; i <= n; i++) {
      
    //         //for i answer will be always less than equal to i.
    //         //maximum possible number of squares : i = (1^1+1^1+1^1+.....+1^1, i times)
    //         dp[i] = i;
      
    //         //Now identify from which number we have to make a direct jump to N so that the required answer is minimised.
    //         //do this by considering every possible direct jump
    //         //number of iterations will be <= sqrt(i)
    //         for (int x = 1; x * x <= i; x++) {
    //           dp[i] = Math.min(dp[i], 1 + dp[i - x * x]);
    //         }
    //       }
      
    //       //here we get our optimal answer
    //       return dp[n];
    //     }
    //   }

    // Stairs

    // You are climbing a staircase and it takes A steps to reach the top.
    // Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    // Return the number of distinct ways modulo 1000000007
    // Problem Constraints
    // 1 <= A <= 105
    // Input Format
    // The first and the only argument contains an integer A, the number of steps.
    // Output Format
    // Return an integer, representing the number of ways to reach the top.
    // Example Input
    // Input 1:
    // A = 2
    // Input 2:
    // A = 3
    // Example Output
    // Output 1:
    // 2
    // Output 2:
    // 3
    // Example Explanation
    // Explanation 1:
    // Distinct ways to reach top: [1, 1], [2].
    // Explanation 2:
    // Distinct ways to reach top: [1 1 1], [1 2], [2 1].
    static int[] clarr = new int[1];
    
    public int climb(int A) {
        if (A == 1) {
            clarr[1] = 1;
            return 1;
        } else if (A == 2) {
            clarr[1] = 2;
            return 2;
        }
        if(clarr[A] != -1) return clarr[A];
        clarr[A] = (climb(A-1)%1000000007 + climb(A-2)%1000000007)%1000000007;
        return clarr[A];
    }
    public int climbStairs(int A) {
        clarr = new int[A+1];
        clarr[0]=0;
        for(int i=1; i<clarr.length;i++) {
            clarr[i] = -1;
        }
        return climb(A);
    }
    // Solution by team
    // public class Solution {
    //     int mod = 1000000007;
    //       public int climbStairs(int A) {
    //         int ways[] = new int[A + 1]; // ways[i] denotes the number of ways to reach the i'th step.
        
    //         if (A == 1)
    //           return 1;
        
    //         ways[1] = 1;
    //         ways[2] = 2;
        
    //         for (int i = 3; i <= A; i++) {
    //           ways[i] = (ways[i - 1] + ways[i - 2])%mod;
    //         }
    //         return ways[A];
    //       }
    //     }


    // Fibonacci number

    //     Given a positive integer A, write a program to find the Ath Fibonacci number.
    // In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.
    // NOTE: 0th term is 0. 1th term is 1 and so on.
    // Problem Constraints
    // 0 <= A <= 44
    // Input Format
    // First and only argument is an integer A.
    // Output Format
    // Return an integer denoting the Ath Fibonacci number.
    // Example Input
    // Input 1:
    // A = 4
    // Input 2:
    // A = 6
    // Example Output
    // Output 1:
    // 3
    // Output 2:
    // 8
    // Example Explanation
    // Explanation 1:
    // Terms of Fibonacci series are: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
    // 0th term is 0 So, 4th term of Fibonacci series is 3. 
    // Explanation 2:

    // 6th term of Fibonacci series is 8.
    static int[] arr = new int[1];
    public static int fibo(int n) {
        if(n==1) {
            arr[1] = 1;
            return 1;
        } else if(n==0) {
            arr[0] = 0;
            return 0;
        }
        if(arr[n] != -1) return arr[n];
        arr[n] = fibo(n-1) + fibo(n-2);
        return arr[n];
    }
    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        arr = new int[a+1];
        for(int i=0; i<arr.length;i++) {
            arr[i] = -1;
        }
        System.out.println(fibo(a));
    }
    // Solution by Team
    // public class Main {
    //     public static void main(String[] args) {
    //       Scanner sc = new Scanner(System.in);
    //       int n = sc.nextInt();
    //       int[] fib = new int[n + 1]; //  fib[i] denotes the i'th fibonacci number
    //       fib[0] = 0;
    //       fib[1] = 1;
    //       for (int i = 2; i <= n; i++)
    //         fib[i] = fib[i - 1] + fib[i - 2];
    //       System.out.println(fib[n]);
    //     }
    //   }
}
