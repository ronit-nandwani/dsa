package advanced.stacks;

import java.util.Stack;

// Max and Min
// Given an array of integers A.
// The value of an array is computed as the difference between the maximum element in the array and the minimum element in the array A.
// Calculate and return the sum of values of all possible subarrays of A modulo 109+7.
// Problem Constraints
// 1 <= |A| <= 100000
// 1 <= A[i] <= 1000000
// Input Format
// The first and only argument given is the integer array A.
// Output Format
// Return the sum of values of all possible subarrays of A modulo 109+7.
// Example Input
// Input 1:
// A = [1]
// Input 2:
// A = [4, 7, 3, 8]
// Example Output
// Output 1:
// 0
// Output 2:
// 26
// Example Explanation
// Explanation 1:
// Only 1 subarray exists. Its value is 0.
// Explanation 2:
// value ( [4] ) = 4 - 4 = 0
// value ( [7] ) = 7 - 7 = 0
// value ( [3] ) = 3 - 3 = 0
// value ( [8] ) = 8 - 8 = 0
// value ( [4, 7] ) = 7 - 4 = 3
// value ( [7, 3] ) = 7 - 3 = 4
// value ( [3, 8] ) = 8 - 3 = 5
// value ( [4, 7, 3] ) = 7 - 3 = 4
// value ( [7, 3, 8] ) = 8 - 3 = 5
// value ( [4, 7, 3, 8] ) = 8 - 3 = 5
// sum of values % 10^9+7 = 26
class Solution {
    public int[] nearestGreaterLeft(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while(!st.isEmpty() && A[st.peek()] <= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int[] nearestGreaterRight(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=n-1;i>=0;i--) {
            while(!st.isEmpty() && A[st.peek()] <= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = n;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int[] nearestSmallerLeft(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while(!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int[] nearestSmallerRight(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=n-1;i>=0;i--) {
            while(!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = n;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int[] nglFunc(int[] A) {

        Stack<Integer> stack=new Stack();

        int ans[]=new int[A.length];

        for(int i=0;i<A.length;i++){

            while(stack.size()!=0 && A[stack.peek()]<=A[i]){ stack.pop(); }

            if(stack.size()==0) ans[i]=-1;

            else ans[i]=stack.peek();

            stack.push(i);

        }

        return ans;

    }
    public int solve(int[] A) {
        long mod = 1L*1000000007;
        long ans = 0;
        int n = A.length;
        int[] smallerLeft = new int[n];
        int[] smallerRight = new int[n];
        int[] greaterLeft = new int[n];
        int[] greaterRight = new int[n];
        smallerLeft = nearestSmallerLeft(A);
        smallerRight = nearestSmallerRight(A);
        greaterLeft = nearestGreaterLeft(A);
        greaterRight = nearestGreaterRight(A);
        for(int i=0; i<n;i++) {
            long max=1L*(i-greaterLeft[i])*(greaterRight[i]-i);
            long min=1L*(i-smallerLeft[i])*(smallerRight[i]-i);
            ans+=(max-min)*A[i];
        }
        
        return (int)(ans%mod);
    }
}

// Solution by team
// public class Solution {
//     int[] a;
//     public void findNextGreaterElement(int[] Next_greater_element, int n){
//         // this function calculates next_greater element index
//         Stack < Integer > s = new Stack < Integer > ();
//         for (int i = 0; i < n; i++) Next_greater_element[i + 1] = n + 1;
//         for (int i = 1; i <= n; i++) { 
//             if (s.empty()) {
//                 s.push(i);
//             } else {
//                 while (!s.empty() && a[s.peek()] <= a[i]) {
//                     Next_greater_element[s.peek()] = i;
//                     s.pop();
//                 }
//                 s.push(i);
//             }
//         }
//     }
//     public void findPreviousGreaterElement(int[] Previous_greater_element, int n){
//         // this function calculates Previous_greater element index
//         Stack < Integer > s = new Stack < Integer > ();
//         for (int i = n; i > 0; i--) { 
//             if (s.empty()) {
//                 s.push(i);
//             } else {
//                 while (!s.empty() && a[i] > a[s.peek()]) {
//                     Previous_greater_element[s.peek()] = i;
//                     s.pop();
//                 }
//                 s.push(i);
//             }
//         }
//     }
//     public void findPreviousSmallerElement(int[] Previous_smaller_element, int n){
//         // this function calculates Previous smaller element index
//         Stack < Integer > s = new Stack < Integer > ();
//         for (int i = n; i > 0; i--) { 
//             if (s.empty()) {
//                 s.push(i);
//             } else {
//                 while (!s.empty() && a[i] <= a[s.peek()]) {
//                     Previous_smaller_element[s.peek()] = i;
//                     s.pop();
//                 }
//                 s.push(i);
//             }
//         }
//     }
//     public void findNextSmallerElement(int[] Next_smaller_element, int n){
//         // function function calculates Next smaller element index
//         Stack < Integer > s = new Stack < Integer > ();
//         for (int i = 0; i < n; i++) Next_smaller_element[i + 1] = n + 1;
//         for (int i = 1; i <= n; i++) { 
//             if (s.empty()) {
//                 s.push(i);
//             } else {
//                 while (!s.empty() && a[i] < a[s.peek()]) {
//                     Next_smaller_element[s.peek()] = i;
//                     s.pop();
//                 }
//                 s.push(i);
//             }
//         }
//     }
    
//     public int solve(int[] A) {
//         int n = A.length, mod = 1000 * 1000 * 1000 + 7;
//         a = new int[n + 1];
//         int Next_greater_element[] = new int[n + 1];
//         int Previous_greater_element[] = new int[n + 1];
//         int Previous_smaller_element[] = new int[n + 1];
//         int Next_smaller_element[] = new int[n + 1];
//         for (int i = 0; i < n; i++) {
//             a[i + 1] = A[i];
//         }
        
//         findNextGreaterElement(Next_greater_element, n);
//         findPreviousGreaterElement(Previous_greater_element, n);
//         findPreviousSmallerElement(Previous_smaller_element, n);
//         findNextSmallerElement(Next_smaller_element, n);
       
//         long ans = 0;
//         for (int i = 1; i <= n; i++) {
//             long right = Next_greater_element[i] - i;
//             long left = i - Previous_greater_element[i];
//             ans += (((left * right) % mod) * a[i]) % mod;
//             ans %= mod;
//             left = i - Previous_smaller_element[i];
//             right = Next_smaller_element[i] - i;
//             ans -= (((left * right) % mod) * a[i]) % mod;
//             ans += mod;
//             ans %= mod;
//         }
//         return (int) ans;
//     }
// }


public class Stacks2 {



    // Largest Rectangle in Histogram
    // Given an array of integers A.
    //A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
    //Find the area of the largest rectangle formed by the histogram.
    //Problem Constraints
    //1 <= |A| <= 100000
    //1 <= A[i] <= 10000
    //Input Format
    //The only argument given is the integer array A.
    //Output Format
    //Return the area of the largest rectangle in the histogram.
    //Example Input
    //Input 1:
    // A = [2, 1, 5, 6, 2, 3]
    //Input 2:
    // A = [2]
    //Example Output
    //Output 1:
    // 10
    //Output 2:
    // 2
    //Example Explanation
    //Explanation 1:
    //The largest rectangle has area = 10 unit. Formed by A[3] to A[4].
    //Explanation 2:
    //Largest rectangle has area 2.
    public int[] nearestSmallerLeft(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while(!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int[] nearestSmallerRight(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=n-1;i>=0;i--) {
            while(!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public int largestRectangleArea(int[] A) {
        int ans = 0;
        int n = A.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left = nearestSmallerLeft(A);
        right = nearestSmallerRight(A);
        for(int i=0; i<n;i++) {
            int j = left[i];
            int k = right[i];

            if(k==-1) k=n;
            ans = Math.max(ans, A[i]*(k-j-1));
        }
        return ans;
    }
    // Solution provided
//    public int largestRectangleArea(int[] A) {
//        Stack < Integer > stack = new Stack < Integer > ();
//        int ans = -1, n = A.length;
//        for (int i = 0; i < A.length; i++) {
//            while (!stack.empty() && A[i] < A[stack.peek()]) {
//                int ind = stack.peek();
//                stack.pop();
//                // (stack.peek()+1) is the left and (i-1) is the right boundary of the rectangle with height A[ind]
//                if (!stack.empty())
//                    ans = Math.max(ans, (i - stack.peek() - 1) * A[ind]);
//                else ans = Math.max(ans, i * A[ind]);
//            }
//            stack.push(i);
//        }
//        while (!stack.empty()) {
//            int ind = stack.peek();
//            stack.pop();
//            // (stack.peek()+1) is the left and (n-1) is the right boundary of the rectangle with height A[ind]
//            if (!stack.empty())
//                ans = Math.max(ans, (n - stack.peek() - 1) * A[ind]);
//            else ans = Math.max(ans, (n) * A[ind]);
//        }
//        return ans;
//    }


    // Nearest Smaller
    // Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
    // More formally,
    // G[i] for an element A[i] = an element A[j] such that
    // j is maximum possible AND
    // j < i AND
    // A[j] < A[i]
    // Elements for which no smaller element exist, consider the next smaller element as -1.
    // Problem Constraints
    // 1 <= |A| <= 100000
    // -109 <= A[i] <= 109
    // Input Format
    // The only argument given is integer array A.
    // Output Format
    // Return the integar array G such that G[i] contains the nearest smaller number than A[i]. If no such element occurs G[i] should be -1.
    // Example Input
    // Input 1:
    // A = [4, 5, 2, 10, 8]
    // Input 2:
    // A = [3, 2, 1]
    // Example Output
    // Output 1:
    // [-1, 4, -1, 2, 2]
    // Output 2:
    // [-1, -1, -1]
    // Example Explanation
    // Explanation 1:
    // index 1: No element less than 4 in left of 4, G[1] = -1
    // index 2: A[1] is only element less than A[2], G[2] = A[1]
    // index 3: No element less than 2 in left of 2, G[3] = -1
    // index 4: A[3] is nearest element which is less than A[4], G[4] = A[3]
    // index 4: A[3] is nearest element which is less than A[5], G[5] = A[3]
    // Explanation 2:
    // index 1: No element less than 3 in left of 3, G[1] = -1
    // index 2: No element less than 2 in left of 2, G[2] = -1
    // index 3: No element less than 1 in left of 1, G[3] = -1
    public int[] prevSmaller(int[] A) {
        Stack<Integer> st = new Stack();
        int n = A.length;
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while(!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = A[st.peek()];
            }
            st.push(i);
        }
        return ans;
    }
}
