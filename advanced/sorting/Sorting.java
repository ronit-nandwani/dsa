// Modified: sorting added form largest number problem by soring
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

    // B Closest Points To Origin
    // We have a list A of points (x, y) on the plane. Find the B closest points to the origin (0, 0).
    //Here, the distance between two points on a plane is the Euclidean distance.
    //You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)
    //NOTE: Euclidean distance between two points P1(x1, y1) and P2(x2, y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).
    //Problem Constraints
    //1 <= B <= length of the list A <= 105
    //-105 <= A[i][0] <= 105
    //-105 <= A[i][1] <= 105
    //Input Format
    //The argument given is list A and an integer B.
    //Output Format
    //Return the B closest points to the origin (0, 0) in any order.
    //Example Input
    //Input 1:
    // A = [
    //       [1, 3],
    //       [-2, 2]
    //     ]
    // B = 1
    //Input 2:
    // A = [
    //       [1, -1],
    //       [2, -1]
    //     ]
    // B = 1
    //Example Output
    //Output 1:
    // [ [-2, 2] ]
    //Output 2:
    // [ [1, -1] ]
    //Example Explanation
    //Explanation 1:
    // The Euclidean distance will be sqrt(10) for point [1,3] and sqrt(8) for point [-2,2].
    // So one closest point will be [-2,2].
    //Explanation 2:
    // The Euclidean distance will be sqrt(2) for point [1,-1] and sqrt(5) for point [2,-1].
    // So one closest point will be [1,-1].
    public int[][] bClosestPointsToOrigin(int[][] A, int B) {
        // Using built-in sort function Arrays.sort
        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            // Compare values according to columns
            public int compare(final int[] f,
                               final int[] s)
            {

                // To sort in descending order revert
                // the '>' Operator
                return (f[0]*f[0] + f[1]*f[1]) - (s[0]*s[0] + s[1]*s[1]);
            }
        }); // End of function call sort().
        int[][] ans = new int[B][2];
        for(int i = 0;i<B;i++) {
            ans[i] = A[i];
        }
        return ans;
    }

    // Form largest number
    // Given an array A of non-negative integers, arrange them such that they form the largest number.
    //Note: The result may be very large, so you need to return a string instead of an integer.
    //Problem Constraints
    //1 <= len(A) <= 100000
    //0 <= A[i] <= 2*109
    //Input Format
    //The first argument is an array of integers.
    //Output Format
    //Return a string representing the largest number.
    //Example Input
    //Input 1:
    // A = [3, 30, 34, 5, 9]
    //Input 2:
    // A = [2, 3, 9, 0]
    //Example Output
    //Output 1:
    // "9534330"
    //Output 2:
    // "9320"
    //Example Explanation
    //Explanation 1:
    //Reorder the numbers to [9, 5, 34, 3, 30] to form the largest number.
    //Explanation 2:
    //Reorder the numbers to [9, 3, 2, 0] to form the largest number 9320.
    public String largestNumber(ArrayList<Integer> A) {
        Collections.sort(A, new Comparator<Integer>() {
            @Override
            public int compare(Integer f,Integer s)
            {
                String sf = String.valueOf(f);
                String ss = String.valueOf(s);
                String s1 = sf + ss;
                String s2 = ss + sf;
                return s2.compareTo(s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < A.size(); i++) {
            sb.append(Integer.toString(A.get(i)));
        }
        if(A.get(0) == 0) {
            return "0";
        }
        return sb.toString();
    }

    static long ic_c = 0;
    static long mod = 1000000000 + 7;

    public static void merge(int[] A, int l, int mid, int r) {
        int[] ans = new int[r-l+1];
        int i=l, j=mid+1, k=0;
        while(i<=mid && j<=r) {
            if(A[i] <= A[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ic_c = (long)(ic_c + mid-i+1)%mod;
                ans[k] = A[j];
                j++;
            }
            k++;
        }
        while(i<= mid) {
            ans[k] = A[i];
            k++;i++;
        }
        while(j<= r) {
            ans[k] = A[j];
            k++;j++;
        }

        for(int ii = 0; ii < ans.length; ii++) {
            A[ii+l]=ans[ii];
        }
    }

    public static void mergeSort(int[] A, int l, int r) {
        if(l>=r) return;
        int mid = (l+r)/2;
        mergeSort(A,l,mid);
        mergeSort(A,mid+1,r);
        merge(A, l, mid, r);
    }

    // Count Inversion Pair
    // Given an array of integers A. If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A. Find the total number of inversions of A modulo (109 + 7).
    //Problem Constraints
    //1 <= length of the array <= 105
    //1 <= A[i] <= 109
    //Input Format
    //The only argument given is the integer array A.
    //Output Format
    //Return the number of inversions of A modulo (109 + 7).
    //Example Input
    //Input 1:
    //A = [1, 3, 2]
    //Input 2:
    //A = [3, 4, 1, 2]
    //Example Output
    //Output 1:
    //1
    //Output 2:
    //4
    //Example Explanation
    //Explanation 1:
    //The pair (1, 2) is an inversion as 1 < 2 and A[1] > A[2]
    //Explanation 2:
    //The pair (0, 2) is an inversion as 0 < 2 and A[0] > A[2]
    //The pair (0, 3) is an inversion as 0 < 3 and A[0] > A[3]
    //The pair (1, 2) is an inversion as 1 < 2 and A[1] > A[2]
    //The pair (1, 3) is an inversion as 1 < 3 and A[1] > A[3]
    public static int inversionCountInAnArray(int[] A) {
        int n = A.length;
        mergeSort(A,0,n-1);
        return (int)ic_c;
    }

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
//        int[] arr = {6,3,3,6,7,8,7,3,7};
//        // Expected Output: [3,3,3,6,6,7,7,7,8]
//        System.out.println(Arrays.toString(countSort(arr)));


        // Expected Output: 3
        int[] arr = {45,10,15,25,50};
        System.out.println(inversionCountInAnArray(arr));
        System.out.println(ic_c);
    }


    // ------------------------



    // Max Chunks To Make Sorted

    // Problem Description
    // Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)], if we split the array into some number of "chunks" (partitions), and individually sort each chunk. After concatenating them in order of splitting, the result equals the sorted array.

    // What is the most number of chunks we could have made?



    // Problem Constraints
    // 1 <= N <= 100000
    // 0 <= A[i] < N



    // Input Format
    // The only argument given is the integer array A.



    // Output Format
    // Return the maximum number of chunks that we could have made.



    // Example Input
    // Input 1:

    // A = [1, 2, 3, 4, 0]
    // Input 2:

    // A = [2, 0, 1, 3]


    // Example Output
    // Output 1:

    // 1
    // Output 2:

    // 2


    // Example Explanation
    // Explanation 1:

    // A = [1, 2, 3, 4, 0]
    // To get the 0 in the first index, we have to take all elements in a single chunk.
    // Explanation 2:

    // A = [2, 0, 1, 3] 
    // We can divide the array into 2 chunks.
    // First chunk is [2, 0, 1] and second chunk is [3].
    
    // Solution by me
    
    public int maxChunks(int[] A) {
        int n = A.length;
        int i=0, max = 0,count=0;
        for(i=0;i<n;i++) {
            max = Math.max(max,A[i]);
            if(i == max) {
                count = count+1;
            }
        }
        return count;
    }

    // Solution by team

    public int maxChunksTeam(int[] A) {
        int cnt = 0, ma = 0, i = 0;
        for (int x: A) {
            ma = Math.max(ma, x);
            // checks if the maximum number so far equals the index number
            if (ma == i)
                cnt += 1;
            i += 1;
        }
        return cnt;
    }
}
