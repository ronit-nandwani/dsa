package advanced.stacks;

import java.util.Stack;

public class Stacks1 {
    
    // Passing Game
    // There is a football event going on in your city. In this event, you are given A passes and players having ids between 1 and 106.
    //Initially, some player with a given id had the ball in his possession. You have to make a program to display the id of the player who possessed the ball after exactly A passes.
    //There are two kinds of passes:
    //1) ID
    //2) 0
    //For the first kind of pass, the player in possession of the ball passes the ball "forward" to the player with id = ID.
    //For the second kind of pass, the player in possession of the ball passes the ball back to the player who had forwarded the ball to him.
    //In the second kind of pass "0" just means Back Pass.
    //Return the ID of the player who currently possesses the ball.
    //Problem Constraints
    //1 <= A <= 100000
    //1 <= B <= 100000
    //|C| = A
    //Input Format
    //The first argument of the input contains the number A.
    //The second argument of the input contains the number B ( id of the player possessing the ball in the very beginning).
    //The third argument is an array C of size A having (ID/0).
    //Output Format
    //Return the "ID" of the player who possesses the ball after A passes.
    //Example Input
    //Input 1:
    // A = 10
    // B = 23
    // C = [86, 63, 60, 0, 47, 0, 99, 9, 0, 0]
    //Input 2:
    // A = 1
    // B = 1
    // C = [2]
    //Example Output
    //Output 1:
    // 63
    //Output 2:
    // 2
    //Example Explanation
    //Explanation 1:
    // Initially, Player having  id = 23  posses ball.
    // After pass  1,  Player having  id = 86  posses ball.
    // After pass  2,  Player having  id = 63  posses ball.
    // After pass  3,  Player having  id = 60  posses ball.
    // After pass  4,  Player having  id = 63  posses ball.
    // After pass  5,  Player having  id = 47  posses ball.
    // After pass  6,  Player having  id = 63  posses ball.
    // After pass  7,  Player having  id = 99  posses ball.
    // After pass  8,  Player having  id = 9   posses ball.
    // After pass  9,  Player having  id = 99  posses ball.
    // After pass  10, Player having  id = 63   posses ball.
    //Explanation 2:
    // Ball is passed to 2.
    public int passingGame(int A, int B, int[] C) {
        Stack st = new Stack();
        st.push(B);
        int i = 0;
        while(i != A) {
            if(C[i] == 0) {
                st.pop();
            } else {
                st.push(C[i]);
            }
            i++;
        }
        return (int)st.peek();
    }
    // Solution provided for passing game
    // public int solve(int A, int B, int[] C) {
    //        Stack < Integer > st = new Stack < Integer > ();
    //        st.push(B);
    //        for (int x: C) {
    //            // pop from stack
    //            if (x == 0) st.pop();
    //            // push the given ID to stack
    //            else st.push(x);
    //        }
    //        return st.peek();
    //    }


    // Balanced Parenthesis
    // Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.
    //Refer to the examples for more clarity.
    //Problem Constraints
    //1 <= |A| <= 100
    //Input Format
    //The first and the only argument of input contains the string A having the parenthesis sequence.
    //Output Format
    //Return 0 if the parenthesis sequence is not balanced.
    //Return 1 if the parenthesis sequence is balanced.
    //Example Input
    //Input 1:
    // A = {([])}
    //Input 2:
    // A = (){
    //Input 3:
    // A = ()[]
    //Example Output
    //Output 1:
    // 1
    //Output 2:
    // 0
    //Output 3:
    // 1
    //Example Explanation
    //You can clearly see that the first and third case contain valid paranthesis.
    //In the second case, there is no closing bracket for {, thus the paranthesis sequence is invalid.
    public static int balancedParenthesis(String A) {
        Stack<Character> st = new Stack();
        for(int i=0; i<A.length();i++) {
            char c = A.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else {
                if(st.isEmpty()) {
                    return 0;
                }
                char ch = st.pop();
                if(c == ')' && ch != '(') {
                    return 0;
                }
                if(c == '}' && ch != '{') {
                    return 0;
                }
                if(c == ']' && ch != '[') {
                    return 0;
                }
            }
        }
        if(st.isEmpty()) {
            return 1;
        }
        return 0;
    }

    // Evaluate Expression
    // An arithmetic expression is given by a string array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.
    //Valid operators are +, -, *, /. Each string may be an integer or an operator.
    //Note: Reverse Polish Notation is equivalent to Postfix Expression, where operators are written after their operands.
    //Problem Constraints
    //1 <= N <= 105
    //Input Format
    //The only argument given is string array A.
    //Output Format
    //Return the value of arithmetic expression formed using reverse Polish Notation.
    //Example Input
    //Input 1:
    //A =   ["2", "1", "+", "3", "*"]
    //Input 2:
    //A = ["4", "13", "5", "/", "+"]
    //Example Output
    //Output 1:
    //9
    //Output 2:
    //6
    //Example Explanation
    //Explaination 1:
    //starting from backside:
    //    * : () * ()
    //    3 : () * (3)
    //    + : (() + ()) * (3)
    //    1 : (() + (1)) * (3)
    //    2 : ((2) + (1)) * (3)
    //    ((2) + (1)) * (3) = 9
    //Explaination 2:
    //starting from backside:
    //    + : () + ()
    //    / : () + (() / ())
    //    5 : () + (() / (5))
    //    13 : () + ((13) / (5))
    //    4 : (4) + ((13) / (5))
    //    (4) + ((13) / (5)) = 6
    public static int evalRPN(String[] A) {
        Stack <String> st = new Stack();
        for(int i=0;i<A.length;i++) {
            String s = A[i];
            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
                st.push(s);
            } else {
                int x = Integer.parseInt(st.pop());
                int y = Integer.parseInt(st.pop());
                if(s.equals("+")) {
                    int z = x + y;
                    st.push(String.valueOf(z));
                } else if(s.equals("*")) {
                    int z = x * y;
                    st.push(String.valueOf(z));
                } else if(s.equals("-")) {
                    int z = y - x;
                    st.push(String.valueOf(z));
                } else if(s.equals("/")) {
                    int z = y / x;
                    st.push(String.valueOf(z));
                }
            }
        }
        return Integer.parseInt(st.peek());
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","2","/"}));
    }
}
