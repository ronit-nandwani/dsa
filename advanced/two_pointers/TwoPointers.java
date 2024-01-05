package advanced.two_pointers;

import java.util.Arrays;

public class TwoPointers {
    // Given N non-negative integers A[0], A[1], ..., A[N-1] , where each represents a point at coordinate (i, A[i]).
    //N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).
    //Find two lines, which together with x-axis forms a container, such that the container contains the most water. You need to return this maximum area.
    //Note: You may not slant the container. It is guaranteed that the answer will fit in integer limits.
    //Problem Constraints
    //1 <= N <= 105
    //1 <= A[i] <= 105
    //Input Format
    //Single Argument representing a 1-D array A.
    //Output Format
    //Return single Integer denoting the maximum area you can obtain.
    //Example Input
    //Input 1:
    //A = [1, 5, 4, 3]
    //Input 2:
    //A = [1]
    //Example Output
    //Output 1:
    // 6
    //Output 2:
    // 0
    //Example Explanation
    //Explanation 1:
    //5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3.
    //So total area = 3 * 2 = 6
    //Explanation 2:
    //No container is formed.
    public int maxArea(int[] A) {
        int n = A.length;
        int i=0,j=n-1,ans=0;
        while(i<j) {
            int area = (j-i) * Math.min(A[i],A[j]);
            ans = Math.max(ans,area);
            if(A[i] < A[j]) {
                i++;
            } else if(A[i] > A[j]) {
                j--;
            } else {
                i++;j--;
            }
        }
        return ans;
    }
    // Given an array of positive integers A and an integer B, find and return first continuous subarray which adds to B.
    //If the answer does not exist return an array with a single integer "-1".
    //First sub-array means the sub-array for which starting index in minimum.
    //Problem Constraints
    //1 <= length of the array <= 100000
    //1 <= A[i] <= 109
    //1 <= B <= 109
    //Input Format
    //The first argument given is the integer array A.
    //The second argument given is integer B.
    //Output Format
    //Return the first continuous sub-array which adds to B and if the answer does not exist return an array with a single integer "-1".
    //Example Input
    //Input 1:
    // A = [1, 2, 3, 4, 5]
    // B = 5
    //Input 2:
    // A = [5, 10, 20, 100, 105]
    // B = 110
    //Example Output
    //Output 1:
    // [2, 3]
    //Output 2:
    // [-1]
    //Example Explanation
    //Explanation 1:
    // [2, 3] sums up to 5.
    //Explanation 2:
    // No subarray sums up to required number.
    public static int[] subArrayWithGivenSum(int[] A, int B) {
        int n = A.length;
        int i=0,j=0,sum=A[0];
        while(j<n) {
            if(sum == B) {
                int k=i,l=0;
                int e = j-i+1;
                int[] arr = new int[e];
                while(k<=j) {
                    arr[l] = A[k];
                    k++;
                    l++;
                }
                return arr;
            }
            if(sum < B) {
                j++;
                if(j==n) {
                    return new int[]{-1};
                }
                sum += A[j];
            } else {
                sum -= A[i];
                i++;
            }
        }
        return new int[]{-1};
    }
    // Given an one-dimensional integer array A of size N and an integer B.
    //Count all distinct pairs with difference equal to B.
    //Here a pair is defined as an integer pair (x, y), where x and y are both numbers in the array and their absolute difference is B.
    //Problem Constraints
    //1 <= N <= 104
    //0 <= A[i], B <= 105
    //Input Format
    //First argument is an one-dimensional integer array A of size N.
    //Second argument is an integer B.
    //Output Format
    //Return an integer denoting the count of all distinct pairs with difference equal to B.
    //Example Input
    //Input 1:
    // A = [1, 5, 3, 4, 2]
    // B = 3
    //Input 2:
    // A = [8, 12, 16, 4, 0, 20]
    // B = 4
    //Input 3:
    // A = [1, 1, 1, 2, 2]
    // B = 0
    //Example Output
    //Output 1:
    // 2
    //Output 2:
    // 5
    //Output 3:
    // 2
    //Example Explanation
    //Explanation 1:
    // There are 2 unique pairs with difference 3, the pairs are {1, 4} and {5, 2}
    //Explanation 2:
    // There are 5 unique pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20}
    //Explanation 3:
    // There are 2 unique pairs with difference 0, the pairs are {1, 1} and {2, 2}.
    public static int pairsWithGivenDifference(int[] A, int B) {
        Arrays.sort(A);
        int i=0,j=1,count=0;
        int n = A.length;
        while(j<n) {
            if((A[j]-A[i]) == B) {
                count++;
                int x=A[i], y=A[j];
                while(i<n && A[i] == x) {
                    i++;
                }
                while(j<n && A[j] == y) {
                    j++;
                }
                if(j<n && i==j) {
                    j++;
                }
            } else if((A[j]-A[i]) > B) {
                i++;
                if(i==j) {
                    j++;
                }
            } else {
                j++;
            }
        }
        return count;
    }
    // Given a sorted array of integers (not necessarily distinct) A and an integer B, find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
    //Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).
    //Problem Constraints
    //1 <= |A| <= 100000
    //1 <= A[i] <= 10^9
    //1 <= B <= 10^9
    //Input Format
    //The first argument given is the integer array A.
    //The second argument given is integer B.
    //Output Format
    //Return the number of pairs for which sum is equal to B modulo (10^9+7).
    //Example Input
    //Input 1:
    //A = [1, 1, 1]
    //B = 2
    //Input 2:
    //A = [1, 5, 7, 10]
    //B = 8
    //Example Output
    //Output 1:
    // 3
    //Output 2:
    // 1
    //Example Explanation
    //Explanation 1:
    // The pairs of A[i] and A[j] which sum up to 2 are (0, 1), (0, 2) and (1, 2).
    // There are 3 pairs.
    //Explanation 2:
    // There is only one pair, such that i = 0, and j = 2 sums up to 8.
    public static int pairsWithGivenSum(int[] A, int B) {
        long count = 0;
        int i=0,j=A.length-1;
        while(i<j) {
            long ans = A[i]+A[j];
            if(ans < B) {
                i++;
            } else if(ans > B) {
                j--;
            } else {
                if(A[i]==A[j]) {
                    long c = j-i+1;
                    count = count + (c*(c-1))/2;
                    return (int)(count%1000000007);
                } else {
                    int c1 = 1;
                    while(A[i]==A[i+1]) {
                        c1++;i++;
                    }
                    int c2 = 1;
                    while(A[j]==A[j-1]) {
                        c2++;j--;
                    }
                    count = count + ((long)c1*c2);
                    i++;j--;
                }
            }
        }
        return (int)(count%1000000007);
    }
    public static void main(String[] args) {
//        int[] A = new int[]{1, 5, 7, 10};
//        // Expected Output: 1
//        System.out.println(pairsWithGivenSum(A,8));

//        int[] A = new int[]{1,2,3,4,5,6,7,8,9,10};
//        int B = 1;
//        // Expected Output: 1

//        int[] A = new int[]{8,5,1,10,5,9,9,3,5,6,6,2,8,2,2,6,3,8,7,2,5,3,4,3,3,2,7,9,6,8,7,2,9,10,3,8,10,6,5,4,2,3};
//        int B = 3;
//        // Expected Output: 7
//        System.out.println(pairsWithGivenDifference(A,B));

        int[] A = new int[]{1,2,3,4,5};
        int B = 5;
        System.out.println(Arrays.toString(subArrayWithGivenSum(A, B)));
    }
}
