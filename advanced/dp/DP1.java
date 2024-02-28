package advanced.dp;

import java.util.Scanner;

public class DP1 {
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
