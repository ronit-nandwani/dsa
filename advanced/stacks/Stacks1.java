package advanced.stacks;

import java.util.ArrayList;
import java.util.Stack;

public class Stacks1 {

    // Infix to postFix
    // Given string A denoting an infix expression. Convert the infix expression into a postfix expression.

    // String A consists of ^, /, *, +, -, (, ) and lowercase English alphabets where lowercase English alphabets are operands and ^, /, *, +, - are operators.

    // Find and return the postfix expression of A.

    // NOTE:

    // ^ has the highest precedence.
    // / and * have equal precedence but greater than + and -.
    // + and - have equal precedence and lowest precedence among given operators.


    // Problem Constraints
    // 1 <= length of the string <= 500000



    // Input Format
    // The only argument given is string A.



    // Output Format
    // Return a string denoting the postfix conversion of A.



    // Example Input
    // Input 1:

    // A = "x^y/(a*z)+b"
    // Input 2:

    // A = "a+b*(c^d-e)^(f+g*h)-i"


    // Example Output
    // Output 1:

    // "xy^az*/b+"
    // Output 2:

    // "abcd^e-fgh*+^*+i-"


    // Example Explanation
    // Explanation 1:

    // Ouput dentotes the postfix expression of the given input.
    public int getPrecedence(char ch) {
        if(ch == '^') {
            return 3;
        } else if(ch == '/' || ch == '*') {
            return 2;
        } else if(ch == '+' || ch == '-') {
            return 1;
        } else {
            return -1;
        }
    }
    public String solve(String A) {
        int n = A.length();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++) {
            char ch = A.charAt(i);
            if(ch !='(' && ch !=')' &&  ch !='/' && ch !='*' && ch !='^' && ch !='+' && ch !='-') {
                sb.append(ch);
            } else {
                if(stack.isEmpty() || ch == '(') {
                    stack.push(ch);
                } else if(ch == ')') {
                    while(stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                } else {
                    while(!stack.isEmpty() && (getPrecedence(ch) <= getPrecedence(stack.peek()))) {
                        sb.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


    // Solution by team
    
        // public class Solution {
        // public String solve(String A) {
        //     return infixToPostfix(A);
        // }
        // public int prec(char c) {
        //     if (c == '^')
        //         return 3;
        //     else if (c == '*' || c == '/')
        //         return 2;
        //     else if (c == '+' || c == '-')
        //         return 1;
        //     else
        //         return -1;
        // }
    // Function to convert infix expression
    //to postfix expression
    //     public String infixToPostfix(String s) {
    //         Stack < Character > st = new Stack < Character > ();
    //         st.push('N');
    //         ArrayList < Character > ns = new ArrayList < Character > ();
    //         for (int i = 0; i < s.length(); i++) {
    //             char C = s.charAt(i);
    //             // If the scanned character is an operand, add it to output string.
    //             if ((C >= 'a' && C <= 'z') || (C >= 'A' && C <= 'Z'))
    //                 ns.add(C);
    //             // If the scanned character is an '(', push it to the stack.
    //             else if (C == '(')
    //                 st.push('(');
    //             // If the scanned character is an ')', pop and to output string from the stack
    //             // until an '(' is encountered.
    //             else if (C == ')') {
    //                 while (st.peek() != 'N' && st.peek() != '(') {
    //                     char c = st.peek();
    //                     st.pop();
    //                     ns.add(c);
    //                 }
    //                 if (st.peek() == '(') {
    //                     char c = st.peek();
    //                     st.pop();
    //                 }
    //             }
    //             //If an operator is scanned
    //             else {
    //                 while (st.peek() != 'N' && prec(C) <= prec(st.peek())) {
    //                     char c = st.peek();
    //                     st.pop();
    //                     ns.add(c);
    //                 }
    //                 st.push(C);
    //             }
    //         }
    //         //Pop all the remaining elements from the stack
    //         while (st.peek() != 'N') {
    //             char c = st.peek();
    //             st.pop();
    //             ns.add(c);
    //         }
    //         StringBuilder result = new StringBuilder(ns.size());
    //         for (Character c: ns) {
    //             result.append(c);
    //         }
    //         return result.toString();
    //     }
    // }


    // Double Character Trouble

    // You have a string, denoted as A.
    // To transform the string, you should perform the following operation repeatedly:
    // Identify the first occurrence of consecutive identical pairs of characters within the string.
    // Remove this pair of identical characters from the string.
    // Repeat steps 1 and 2 until there are no more consecutive identical pairs of characters.
    // The final result will be the transformed string.
    // Problem Constraints
    // 1 <= |A| <= 100000
    // Input Format
    // First and only argument is string A.
    // Output Format
    // Return the final string.
    // Example Input
    // Input 1:
    // A = "abccbc"
    // Input 2:
    // A = "ab"
    // Example Output
    // Output 1:
    // "ac"
    // Output 2:
    // "ab"
    // Example Explanation
    // Explanation 1:
    // The Given string is "abccbc".
    // Remove the first occurrence of consecutive identical pairs of characters "cc".
    // After removing the string will be "abbc".
    // Again Removing the first occurrence of consecutive identical pairs of characters "bb".
    // After remvoing, the string will be "ac".
    // Now, there is no consecutive identical pairs of characters.
    // Therefore the string after this operation will be "ac".
    // Explanation 2:

    // No removals are to be done.
    public String doubleCharacterTrouble(String A) {
        Stack<Character> st = new Stack();
        for(int i=0; i<A.length();i++) {
            if(!st.isEmpty() && st.peek() == A.charAt(i)) {
                st.pop();
            } else {
                st.push(A.charAt(i));
            }
        }
        String s = "";
        while(st.size()>0) {
            s += st.pop();
        }
        String b = new StringBuffer(s).reverse().toString();
        return b;
    }
    // Solution by team
    // public String solve(String A) {
    //     // we maintain a stack for the characters of the string
    //     Stack < Character > st = new Stack < Character > ();
    //     for (int i = 0; i < A.length(); i++) {
    //         // if the current character is equal to the top of the stack then they form 
    //         // a pair of consecutive similar characters therefore we pop from the stack,
    //         // else we push the charcter in the stack
    //         if (!st.empty() && st.peek() == A.charAt(i)) {
    //             st.pop();
    //         } else st.push(A.charAt(i));
    //     }
    //     StringBuilder sb = new StringBuilder();
    //     while (!st.empty()) {
    //         sb.append(st.peek());
    //         st.pop();
    //     }
    //     sb.reverse();
    //     return sb.toString();
    // }

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
