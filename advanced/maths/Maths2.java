package advanced.maths;

public class Maths2 {

    // Helper for findrank: Returns factorial of n
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
        // 318057
        System.out.println(findRank("ZCSFLVHXRYJQKWABGT"));
    }
}
