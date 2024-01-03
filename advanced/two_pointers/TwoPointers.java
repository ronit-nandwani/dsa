package advanced.two_pointers;

public class TwoPointers {
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
        int[] A = new int[]{1, 5, 7, 10};
        // Expected Output: 1
        System.out.println(pairsWithGivenSum(A,8));
    }
}
