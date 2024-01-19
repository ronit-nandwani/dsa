package advanced.stacks;

import java.util.Stack;

public class Stacks1 {
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
