package advanced.maths;

public class Maths2 {
    // Given three integers A, B, and C, where A represents n, B represents r, and C represents m, find and return the value of nCr % m where nCr % m = (n!/((n-r)!*r!))% m.
    //x! means factorial of x i.e. x! = 1 * 2 * 3... * x.
    public static int ncrModM(int A, int B, int C) {

        // Optimised their approach

        // dp[n][r] stores the value of nCr
        int[][] dp = new int[A + 1][B + 1];
        for(int i = 0; i <= A; i++) {
            for(int j = 0; j <= Math.min(i, B); j++) {
                if(j == i || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // nCr = (n - 1)C(r - 1) + (n - 1)Cr
                    dp[i][j] = (dp[i - 1][j - 1] % C + dp[i - 1][j] % C) % C;
                }
            }
        }
        return dp[A][B] % C;

        // My Approach - Also Works
        //        int[][] ncr = new int[A+1][B+1];
        //        for(int i=1;i<A+1;i++) {
        //            ncr[i][0] = 1;
        //            // ncr[i][i] = 1;
        //      for(int j=1;j<=Math.min(i,B);j++) {
        //                ncr[i][j] = (ncr[i-1][j-1] + ncr[i-1][j])%C;
        //            }
        //        }
        //        return ncr[A][B]%C;
    }

    // Helper for findRank: Returns factorial of n
    public static int facto(int n) {
        if(n==0) {
            return 1;
        }
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = res%1000003 * i;
        }
        return res;
    }

    //Sorted Permutation rank: Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
    //Assume that no characters are repeated
    //Note: The answer might not fit in an integer, so return your answer % 1000003
    public static int findRank(String A) {
        int ans = 1;
        int n = A.length();
        int fact[] = new int[n+1];
        for(int i=0;i<=n;i++) {
            fact[i] = facto(i);
        }
        for(int i=0;i<n-1;i++) {
            int count = 0;
            for(int j=i+1;j<n;j++) {
                if(A.charAt(j)<A.charAt(i)) {
                    count++;
                }
            }
            ans = ans + (count * fact[n-i-1])%1000003;
        }
        return ans%1000003;
    }

    // Write a program to print the pascal triangle up to A rows.
    public int[][] pascalTriangle(int A) {
        int[][] arr = new int[A][A];
        for(int i=0; i<A;i++) {
            arr[i][0]=1;
            arr[i][i]=1;
            for(int j=1;j<i;j++) {
                arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
            }
        }
        return arr;
    }

    // Given a positive integer A, return its corresponding column title as it appears in an Excel sheet.

    // For example:

    // 1 -> A
    // 2 -> B
    // 3 -> C
    // ...
    // 26 -> Z
    // 27 -> AA
    // 28 -> AB 
    public String excelColumnTitle(int A) {
        String ans = "";
        while (A>0) {
            A=A-1;
            ans = (char)('A'+(A%26)) + ans;
            A=A/26;
        }
        return ans;
    }

    public static void main(String[] args) {
        // Expected Output:: 318057
        // System.out.println(findRank("ZCSFLVHXRYJQKWABGT"));


        // Expected Output: 1
        System.out.println(ncrModM(1000000,1,999999));
    }
}
