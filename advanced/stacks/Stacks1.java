package advanced.stacks;

import java.util.Stack;

public class Stacks1 {
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
