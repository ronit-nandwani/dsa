package advanced.searching;

import java.util.Arrays;

public class Searching1 {
    // Given a sorted array of integers A (0-indexed) of size N, find the left most and the right most index of a given integer B in the array A.
    //Return an array of size 2, such that
    //          First element = Left most index of B in A
    //          Second element = Right most index of B in A.
    //If B is not found in A, return [-1, -1].
    //Note : Your algorithm's runtime complexity must be in the order of O(log n).
    // Problem Constraints
    //1 <= N <= 106
    //1 <= A[i], B <= 109
    //
    //Input Format
    //The first argument given is the integer array A.
    //The second argument given is the integer B.
    //
    //Output Format
    //Return the left most and right most index (0-based) of B in A as a 2-element array. If B is not found in A, return [-1, -1].
    //Example Input
    //Input 1:
    // A = [5, 7, 7, 8, 8, 10]
    // B = 8
    //Input 2:
    // A = [5, 17, 100, 111]
    // B = 3
    //
    //Example Output
    //Output 1:
    // [3, 4]
    //Output 2:
    // [-1, -1]
    //
    //Example Explanation
    //Explanation 1:
    // The first occurrence of 8 in A is at index 3.
    // The last occurrence of 8 in A is at index 4.
    // ans = [3, 4]
    //Explanation 2:
    // There is no occurrence of 3 in the array.
    public static int[] searchRange(final int[] A, int B) {
        int n = A.length;
        int l=0,r=n-1;
        int[] ans = {-1,-1};
        while(l <= r) {
            int mid = (l+r)/2;
            if(A[mid]==B && (mid == 0 || A[mid-1] != B)) {
                ans[0] = mid;
                break;
            }
            if(B <= A[mid]) {
                r=mid-1;
            } else {
                l=mid+1;
            }
        }

        l=0;r=n-1;
        while(l <= r) {
            int mid = (l+r)/2;
            if(A[mid]==B && (mid == (n-1) || A[mid+1] != B)) {
                ans[1] = mid;
                break;
            }
            if(B < A[mid]) {
                r=mid-1;
            } else {
                l=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {

        int[] arr = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
        int b = 10;
        // Expected Output: [118, 133]
        System.out.println(Arrays.toString(searchRange(arr, b)));
    }
}
