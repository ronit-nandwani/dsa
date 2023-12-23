package advanced.searching;

public class Searching2 {
    // Given a sorted array of integers A of size N and an integer B, 
    // where array A is rotated at some pivot unknown beforehand.
    // For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].
    // Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.
    // You can assume that no duplicates exist in the array.
    // NOTE: You are expected to solve this problem with a time complexity of O(log(N)).
    // Problem Constraints
    // 1 <= N <= 1000000
    // 1 <= A[i] <= 109
    // All elements in A are Distinct.
    // Input Format
    // The First argument given is the integer array A.
    // The Second argument given is the integer B.
    // Output Format
    // Return index of B in array A, otherwise return -1
    // Example Input
   //  Input 1:
    // A = [4, 5, 6, 7, 0, 1, 2, 3]
    // B = 4 
    // Input 2:
    // A : [ 9, 10, 3, 5, 6, 8 ]
    // B : 5
    // Example Output
    // Output 1:
    // 0 
    // Output 2:
    // 3
    public int rotatedArrayArraySearch(final int[] A, int B) {
        int n = A.length;
        int l=0,r=n-1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            if(A[mid] == B) {
                return mid;
            }
            if(B < A[0]) {
                if(A[mid] < A[0]) {
                    if(A[mid] < B) {
                        l=mid+1;
                    } else {
                        r=mid-1;
                    }
                } else {
                    l = mid+1;
                }
            } else {
                if(A[mid] > A[0]) {
                    if(A[mid] < B) {
                        l=mid+1;
                    } else {
                        r=mid-1;
                    }
                } else {
                    r=mid-1;
                }
            }
        }
        return -1;
    }

    // Given an integer A. Compute and return the square root of A.
    //If A is not a perfect square, return floor(sqrt(A)).
    //NOTE:
    //   The value of A*A can cross the range of Integer.
    //   Do not use the sqrt function from the standard library.
    //   Users are expected to solve this in O(log(A)) time.
    //Problem Constraints
    //0 <= A <= 109
    //Input Format
    //The first and only argument given is the integer A.
    //Output Format
    //Return floor(sqrt(A))
    //Example Input
    //Input 1:
    // 11
    //Input 2:
    // 9
    //Example Output
    //Output 1:
    // 3
    //Output 2:
    // 3
    //Example Explanation
    //Explanation 1:
    //When A = 11 , square root of A = 3.316. It is not a perfect square so we return the floor which is 3.
    //Explanation 2:
    //When A = 9 which is a perfect square of 3, so we return 3.
    public static int sqrt(int N) {
        int l=1,r=N;
        while(l<=r) {
            long mid = l+(r-l)/2;
            if((mid*mid) == N) {
                return (int)mid;
            }
            if((mid*mid) < N) {
                l=(int)mid+1;
            } else {
                r=(int)mid-1;
            }
        }
        return r;
    }
    public static void main(String[] args) {
        // expected output: 46340
        System.out.println(sqrt(2147483647));
    }
}
