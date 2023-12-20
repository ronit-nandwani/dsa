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
