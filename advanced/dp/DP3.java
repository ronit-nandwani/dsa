package advanced.dp;

import java.util.Arrays;
import java.util.Comparator;

public class DP3 {
    // Fractional Knapsack
    // Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.

    // Also given an integer C which represents knapsack capacity.

    // Find out the maximum total value that we can fit in the knapsack. If the maximum total value is ans, then return ⌊ans × 100⌋ , i.e., floor of (ans × 100).

    // NOTE:

    // You can break an item for maximizing the total value of the knapsack


    // Problem Constraints
    // 1 <= N <= 105

    // 1 <= A[i], B[i] <= 103

    // 1 <= C <= 103



    // Input Format
    // First argument is an integer array A of size N denoting the values on N items.

    // Second argument is an integer array B of size N denoting the weights on N items.

    // Third argument is an integer C denoting the knapsack capacity.



    // Output Format
    // Return a single integer denoting the maximum total value of A such that sum of the weights of this subset is smaller than or equal to C.



    // Example Input
    // Input 1:

    // A = [60, 100, 120]
    // B = [10, 20, 30]
    // C = 50
    // Input 2:

    // A = [10, 20, 30, 40]
    // B = [12, 13, 15, 19]
    // C = 10


    // Example Output
    // Output 1:

    // 24000
    // Output 2:

    // 2105


    // Example Explanation
    // Explanation 1:

    // Taking the full items with weight 10 and 20 and 2/3 of the item with weight 30 will give us 
    // the maximum value i.e 60 + 100 + 80 = 240. So we return 24000.
    // Explanation 2:

    // Taking 10/19 the fourth item gives us the maximum value i.e. 21.0526. So we return 2105.
    public class SolutionCake {
        public static class cake {
            int weight;
            int value;
            double vpw;
            cake(int weight, int value) {
                this.weight = weight;
                this.value = value;
                this.vpw = value*1.0/weight;
            }
        }
        public  int solve(int[] A, int[] B, int C) {
            cake[] cakes = new cake[A.length];
            int n = A.length;
            for(int i=0;i<n;i++) {
                cakes[i] = new cake(B[i],A[i]);
            }
            Arrays.sort(cakes,new Comparator<cake>(){
                public int compare(cake c1, cake c2) {
                    if(c1.vpw > c2.vpw) {
                        return -1;
                    }
                    return 1;
                }
            });
            double ans =0;
            for(int i=0;i<n;i++) {
                if(cakes[i].weight <= C) {
                    ans += cakes[i].value;
                    C -= cakes[i].weight;
                }
                else {
                    ans += cakes[i].vpw * C;
                    break;
                }
            }
            ans = Math.floor((ans *1000)/10);
            return (int)ans;
        }
    }
    // Solution by team

    public class Solution1 {
        class Items {
            double cost;
            double weight, value, ind;
            Items(int weight, int value, int ind){
                this.weight = weight;
                this.value = value;
                this.ind = ind;
                this.cost = new Double((double)value / (double)weight);
            }
        }
        
        public int solve(int[] A, int[] B, int C) {
            Items[] iVal = new Items[A.length];
            for (int i = 0; i < A.length; i++) {
                iVal[i] = new Items(B[i], A[i], i);
            }
            Arrays.sort(iVal, new Comparator<Items>() {
                @Override
                public int compare(Items o1, Items o2){
                    if(o1.cost >= o2.cost){
                        return -1;
                    }
                    return 1;
                }
            });
            double totalValue = 0d;
            for (Items i : iVal) {
                int curWt = (int)i.weight;
                int curVal = (int)i.value;
                if (C - curWt >= 0) {
                    C = C - curWt;
                    totalValue += curVal;
                }
                else {
                    double fraction = ((double)C / (double)curWt);
                    totalValue += (curVal * fraction);
                    C = (int)(C - (curWt * fraction));
                    break;
                }
            }
            totalValue *= 1000;
            return (int)(totalValue / 10);
        }
    }




    // 0-1 knapsack

    // Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
    // Also given an integer C which represents knapsack capacity.
    // Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

    // NOTE:

    // You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

    // Problem Constraints
    // 1 <= N <= 103

    // 1 <= C <= 103

    // 1 <= A[i], B[i] <= 103

    // Input Format
    // First argument is an integer array A of size N denoting the values on N items.

    // Second argument is an integer array B of size N denoting the weights on N items.

    // Third argument is an integer C denoting the knapsack capacity.

    // Output Format
    // Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

    // Example Input
    // Input 1:

    // A = [60, 100, 120]
    // B = [10, 20, 30]
    // C = 50
    // Input 2:

    // A = [10, 20, 30, 40]
    // B = [12, 13, 15, 19]
    // C = 10

    // Example Output
    // Output 1:

    // 220
    // Output 2:

    // 0

    // Example Explanation
    // Explanation 1:

    // Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220
    // Explanation 2:

    // Knapsack capacity is 10 but each item has weight greater than 10 so no items can be considered in the knapsack therefore answer is 0.
    public int zeroto1Knapsack(int[] A, int[] B, int C) {
        int n = A.length;
        int m = B.length;

        int[][] dp = new int[n+1][C+1];
    
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=C;j++) {
                if(B[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], A[i-1]+dp[i-1][j-B[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][C];
    }


    // Solution by team
    public class Solution {
        static int maxn = 1009;
        static int[][] dp = new int[maxn][maxn];
        public static int knapsack(int[] A, int[] B, int C) {
            for (int[] row: dp)
                Arrays.fill(row, 0);
            int n = A.length;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= C; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (B[i - 1] <= j)
                        dp[i][j] = Math.max(dp[i][j], A[i - 1] + dp[i - 1][j - B[i - 1]]);
                }
            }
            return dp[n][C];
        }
        public int solve(int[] A, int[] B, int C) {
            return knapsack(A, B, C);
        }
    }
    // Solution using recursion memoization
    public class Solution1 {

        int[] pft; int[] wt; int cap;
    
        int [][]dp;
    
        public int solve(int[] A, int[] B, int C) {
    
            dp= new int[A.length+1][C+1];
    
            for(int i=0;i<=A.length;i++){
    
                Arrays.fill(dp[i],-1);
    
            }
    
            pft=A; wt=B; cap=C;
    
            return knap(pft.length, cap);
    
        }
    
        int knap(int ele, int cap){
    
            if(ele==0 || cap==0) return 0;
    
            else if(dp[ele][cap]!=-1) return dp[ele][cap];
    
            else{
    
                int npick=knap(ele-1,cap);
    
                int pick=0;
    
                if(cap>=wt[ele-1]) pick=pft[ele-1]+knap(ele-1,cap-wt[ele-1]);
    
                return dp[ele][cap]=Math.max(pick,npick);
    
            }
    
        } 
    }

    // Unbounded knapsack
    // Problem Description
    // Given a knapsack weight A and a set of items with certain value B[i] and weight C[i], we need to calculate maximum amount that could fit in this quantity.

    // This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.

    // Problem Constraints
    // 1 <= A <= 1000

    // 1 <= |B| <= 1000

    // 1 <= B[i] <= 1000

    // 1 <= C[i] <= 1000

    // Input Format
    // First argument is the Weight of knapsack A

    // Second argument is the vector of values B

    // Third argument is the vector of weights C

    // Output Format
    // Return the maximum value that fills the knapsack completely
    // Example Input
    // Input 1:
    // A = 10
    // B = [5]
    // C = [10]
    // Input 2:
    // A = 10
    // B = [6, 7]
    // C = [5, 5]

    // Example Output
    // Output 1:
    // 5
    // Output 2:
    // 14

    // Example Explanation
    // Explanation 1:

    // Only valid possibility is to take the given item.
    // Explanation 2:

    // Take the second item twice.
    public int solve(int A, int[] B, int[] C) {
        int n = B.length;
        int m = C.length;
        int[] dp = new int[A+1];

        for(int i = 1;i<=A;i++) {
            for(int j=0;j<n;j++) {
                if(C[j]<=i) {
                    dp[i] = Math.max(dp[i],B[j]+dp[i-C[j]]);
                }
            }
        }
        return dp[A];
    }
    // Solution by team
    public class Solution {
        public int solve(int A, int[] B, int[] C) {
            int W = A;
            int[] dp = new int[W + 1];
            Arrays.fill(dp, 0);
            for (int i = 0; i <= W; i++)
                for (int j = 0; j < B.length; j++)
                    if (C[j] <= i)
                        dp[i] = Math.max(dp[i], dp[i - C[j]] + B[j]);
            return dp[W];
        }
    }
}
