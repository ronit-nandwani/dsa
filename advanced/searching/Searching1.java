package advanced.searching;

import java.util.Arrays;

public class Searching1 {
    // Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.
    // Elements which are appearing twice are adjacent to each other.
    // NOTE: Users are expected to solve this in O(log(N)) time.
    // Problem Constraints
    // 1 <= |A| <= 100000
    // 1 <= A[i] <= 10^9
    // Input Format
    // The only argument given is the integer array A.
    // Output Format
    // Return the single element that appears only once.
    // Example Input
    // Input 1:
    // A = [1, 1, 7]
    // Input 2:
    // A = [2, 3, 3]
    // Example Output
    // Output 1:
    // 7
    // Output 2:
    // 2
    // Example Explanation
    // Explanation 1:
    // 7 appears once
    // Explanation 2:
    // 2 appears once
    public int singleElementinSortedArray(int[] A) {
        int n = A.length;
        int l=0,r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if((mid==0||A[mid-1]!=A[mid])&&(mid==n-1 || A[mid+1]!=A[mid])) {
                return A[mid];
            }
            if(mid==0 || A[mid-1]!=A[mid]) {
                if(mid%2 == 0) {
                    l=mid+1;
                } else {
                    r=mid-1;
                }
            } else {
                if(mid%2 != 0) {
                    l=mid+1;
                } else {
                    r=mid-1;
                }
            }
        }
        return -1;
    }
    // You are given a sorted array A of size N and a target value B.
    // Your task is to find the index (0-based indexing) of the target value in the array.
    // If the target value is present, return its index.
    // If the target value is not found, return the index of least element greater than equal to B.
    // Your solution should have a time complexity of O(log(N)).
    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] <= 105
    // 1 <= B <= 105
    // Input Format
    // The first argument is an integer array A of size N.
    // The second argument is an integer B.
    // Output Format
    // Return an integer denoting the index of target value.
    // Example Input
    // Input 1:
    // A = [1, 3, 5, 6]
    // B = 5 
    // Input 2:
    // A = [1, 4, 9]
    // B = 3
    // Example Output
    // Output 1:
    // 2 
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // The target value is present at index 2. 
    // Explanation 2:
    // The target value should be inserted at index 1.
    public int sortedInsertPosition(int[] A, int B) {
        int n = A.length;
        int l=0,r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(A[mid] == B) {
                return mid;
            }
            if(B<A[mid]) {
                r = mid - 1;
            } else {
                l=mid+1;
            }
        }
        return l;
    }
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
