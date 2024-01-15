package advanced.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


class Solution {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        Collections.sort(A, new SortByFactors());
        return A;
    }
}

class SortByFactors implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        int factorsOfA = factors(a);
        int factorsOfB = factors(b);

        if (factorsOfA == factorsOfB) return a - b;

        return factorsOfA - factorsOfB;
    }

    // Fast Factors find
    public int factors(int N) {
        int count=0;
        for(int i=1;i*i<=N; i++){
            if(N%i==0){
                if(i==N/i){
                    count++;
                }
                else{
                    count= count+2;
                }
            }
        }
        return count;
    }

    // Slow factors find
    // public int factors(int num) {
    //     int i = 1, ans = 0;

    //     while (i <= num) {
    //         if (num % i == 0) ans += 2;
    //         if (i == num) ans--;
    //         i++;
    //     }

    //     return ans;
    // }
}

public class Sorting {
    // Merge Two Sorted Arrays
    // Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.
    //Note: A linear time complexity is expected and you should avoid use of any library function.
    //Problem Constraints
    //-2×109 <= A[i], B[i] <= 2×109
    //1 <= |A|, |B| <= 5×104
    //Input Format
    //First Argument is a 1-D array representing  A.
    //Second Argument is also a 1-D array representing B.
    //Output Format
    //Return a 1-D vector which you got after merging A and B.
    //Example Input
    //Input 1:
    //A = [4, 7, 9]
    //B = [2, 11, 19]
    //Input 2:
    //A = [1]
    //B = [2]
    //Example Output
    //Output 1:
    //[2, 4, 7, 9, 11, 19]
    //Output 2:
    //[1, 2]
    //Example Explanation
    //Explanation 1:
    //Merging A and B produces the output as described above.
    //Explanation 2:
    // Merging A and B produces the output as described above.
    public int[] mergeTwoSortedArrays(final int[] A, final int[] B) {
        int n = A.length;
        int m = B.length;
        int[] ans = new int[n+m];
        int i=0, j=0, k=0;
        while(i<n && j<m) {
            if(A[i] <= B[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ans[k] = B[j];
                j++;
            }
            k++;
        }
        while(i<n) {
            ans[k] = A[i];
            k++;i++;
        }
        while(j<m) {
            ans[k] = B[j];
            k++;j++;
        }
        return ans;
    }

    // Given an array A. Sort this array using Count Sort Algorithm and return the sorted array.
    public static int[] countSort(int[] A) {
        int n = A.length;
        int l = 100001;
        int[] f = new int[l];
        for(int i=0;i<n;i++) {
            f[A[i]]++;
        }
        int[] ans = new int[n];
        int j=0;
        for(int d=0;d<l;d++) {
            for(int i=1;i<=f[d];i++) {
                ans[j++] = d;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       int[] arr = {6,3,3,6,7,8,7,3,7};
       // Expected Output: [3,3,3,6,6,7,7,7,8]
       System.out.println(Arrays.toString(countSort(arr)));
    }
}
