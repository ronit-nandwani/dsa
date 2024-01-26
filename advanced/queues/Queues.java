package advanced.queues;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


// Queue Using Stacks

// Implement a First In First Out (FIFO) queue using stacks only.
//The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
//Implement the UserQueue class:
//void push(int X) : Pushes element X to the back of the queue.
//int pop() : Removes the element from the front of the queue and returns it.
//int peek() : Returns the element at the front of the queue.
//boolean empty() : Returns true if the queue is empty, false otherwise.
//NOTES:
//You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
//Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
//Problem Constraints
//1 <= X <= 109
//At most 1000 calls will be made to push, pop, peek, and empty function.
//All the calls to pop and peek are valid. i.e. pop and peek are called only when the queue is non-empty.
//Example Input
//Input 1:
// 1) UserQueue()
// 2) push(20)
// 3) empty()
// 4) peek()
// 5) pop()
// 6) empty()
// 7) push(30)
// 8) peek()
// 9) push(40)
// 10) peek()
//Input 2:
// 1) UserQueue()
// 2) push(10)
// 3) push(20)
// 4) push(30)
// 5) pop()
// 6) pop()
//Example Output
//Output 1:
// false
// 20
// 20
// true
// 30
// 30
//Output 2:
// 10
// 20
//Example Explanation
//Explanation 1:
// Queue => 20
// Queue => -
// Queue => 30
// Queue => 30, 40
//Explanation 2:
// Queue => 10
// Queue => 10, 20
// Queue => 10, 20, 30
// Queue => 20, 30
// Queue => 30

/**
 * Your UserQueue object will be instantiated and called as such:
 * UserQueue obj = new UserQueue();
 * obj.push(X);
 * int param2 = obj.pop();
 * int param3 = obj.peek();
 * boolean param4 = obj.empty();
 */
class UserQueue {
    static Stack<Integer> st1 = new Stack();
    static Stack<Integer> st2 = new Stack();
    /** Initialize your data structure here. */
    UserQueue() {

    }

    /** Push element X to the back of queue. */
    static void push(int X) {
        st1.push(X);
    }

    /** Removes the element from in front of queue and returns that element. */
    static int pop() {
        if(st2.isEmpty()) {
            move();
        }
        return st2.pop();
    }

    /** Get the front element of the queue. */
    static int peek() {
        if(st2.isEmpty()) {
            move();
        }
        return st2.peek();
    }

    /** Returns whether the queue is empty. */
    static boolean empty() {
        return st1.isEmpty() && st2.isEmpty();
    }

    static void move() {
        while(!st1.isEmpty()) {
            st2.push(st1.pop());
        }
    }
}
public class Queues {

    // Imagine you're an ice cream truck driver in a beachside town. The beach is divided into several sections, and each section has varying numbers of beachgoers wanting ice cream given by the array of integers A.
    // For simplicity, let's say the beach is divided into 8 sections. One day, you note down the number of potential customers in each section: [5, 12, 3, 4, 8, 10, 2, 7]. This means there are 5 people in the first section, 12 in the second, and so on.
    // You can only stop your truck in B consecutive sections at a time because of parking restrictions. To maximize sales, you want to park where the most customers are clustered together.
    // For all B consecutive sections, identify the busiest stretch to park your ice cream truck and serve the most customers. Return an array C, where C[i] is the busiest section in each of the B consecutive sections. Refer to the given example for clarity.
    // NOTE: If B > length of the array, return 1 element with the max of the array.
    // Problem Constraints
    // 1 <= |A|, B <= 106
    // Input Format
    // The first argument given is the integer array A.
    // The second argument given is the integer B.
    // Output Format
    // Return an array C, where C[i] is the maximum value from A[i] to A[i+B-1].
    // Example Input
    // Input 1:
    // A = [1, 3, -1, -3, 5, 3, 6, 7]
    // B = 3
    // Input 2:
    // A = [1, 2, 3, 4, 2, 7, 1, 3, 6]
    // B = 6
    // Example Output
    // Output 1:
    // [3, 3, 5, 5, 6, 7]
    // Output 2:
    // [7, 7, 7, 7]
    // Example Explanation
    // Explanation 1:
    // Window position     | Max
    // --------------------|-------
    // [1 3 -1] -3 5 3 6 7 | 3
    // 1 [3 -1 -3] 5 3 6 7 | 3
    // 1 3 [-1 -3 5] 3 6 7 | 5
    // 1 3 -1 [-3 5 3] 6 7 | 5
    // 1 3 -1 -3 [5 3 6] 7 | 6
    // 1 3 -1 -3 5 [3 6 7] | 7
    // Explanation 2:
    // Window position     | Max
    // --------------------|-------
    // [1 2 3 4 2 7] 1 3 6 | 7
    // 1 [2 3 4 2 7 1] 3 6 | 7
    // 1 2 [3 4 2 7 1 3] 6 | 7
    // 1 2 3 [4 2 7 1 3 6] | 7
    public int[] slidingMaximum(final int[] A, int B) {
        int n = A.length;
        if(B>n) {
            int max = Integer.MIN_VALUE;
            for(int i=0;i<n;i++) {
                if(A[i] > max) {
                    max = A[i];
                }
            }
            return new int[]{max};
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<B;i++) {
            while(deque.size() != 0 && A[deque.peekLast()] < A[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        ArrayList<Integer> arr = new ArrayList();
        arr.add(A[deque.peekFirst()]);
        for(int i=B;i<n;i++) {
            while(deque.size() != 0 && A[deque.peekLast()] < A[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if(deque.peekFirst() == (i-B)) { // Out of Window
                deque.removeFirst();
            }
            arr.add(A[deque.peekFirst()]);
        }
        int[] a = new int[arr.size()];
        for(int i=0;i<arr.size();i++) {
            a[i] = arr.get(i);
        }
        return a;
    }

    // Perfect Number
    // Given an integer A, you have to find the Ath Perfect Number.
    //A Perfect Number has the following properties:
    //It comprises only 1 and 2.
    //The number of digits in a Perfect number is even.
    //It is a palindrome number.
    //For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.
    //Problem Constraints
    //1 <= A <= 100000
    //Input Format
    //The only argument given is an integer A.
    //Output Format
    //Return a string that denotes the Ath Perfect Number.
    //Example Input
    //Input 1:
    // A = 2
    //Input 2:
    // A = 3
    //Example Output
    //Output 1:
    // 22
    //Output 2:
    // 1111
    //Example Explanation
    //Explanation 1:
    //First four perfect numbers are:
    //1. 11
    //2. 22
    //3. 1111
    //4. 1221
    //Return the 2nd Perfect number.
    //Explanation 2:
    //First four perfect numbers are:
    //1. 11
    //2. 22
    //3. 1111
    //4. 1221
    //Return the 3rd Perfect number.
    public String perfectNumber(int A) {
        if(A <= 2) {
            return A+""+A;
        }

        Queue<Long> q = new LinkedList<>();
        q.add((long)1);
        q.add((long)2);
        int i = 3;
        while(i <= A) {
            long x = q.remove();
            long a=x*10 + 1;
            long b=x*10 + 2;

            if((long)i == A) {
                String s = new StringBuilder(""+a).reverse().toString();
                return ""+a+""+s;
            }
            if((long)(i+1) == A) {
                String s = new StringBuffer(""+b).reverse().toString();
                return ""+b+""+s;
            }
            q.add(a);q.add(b);
            i += 2;
        }
        return "";

        /*
 Optimized Code
        Queue < String > q = new LinkedList < String > ();
        if (A == 1)
            return "11";
        else if (A == 2)
            return "22";
        q.add("1");
        q.add("2");
        int cur = 2;
        String ans = new String();
        while (q.size() < A) {
            StringBuilder sb = new StringBuilder(q.peek());
            q.remove();
            sb.append("1");
            q.add(sb.toString());
            cur++;
            if (cur == A)
                ans = sb.toString();
            sb.deleteCharAt(sb.length() - 1);
            sb.append("2");
            cur++;
            if (cur == A)
                ans = sb.toString();
            q.add(sb.toString());
        }
        // ans has the first half of our final answer
        StringBuilder sb = new StringBuilder(ans);
        return ans + sb.reverse().toString();
*/

    }
}
