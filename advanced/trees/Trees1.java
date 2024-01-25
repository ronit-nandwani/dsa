package advanced.trees;

import java.util.ArrayList;
import java.util.Stack;

// Definition of binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Trees1 {


    // Pre Order Traversal
    // Given a binary tree, return the preorder traversal of its nodes values.
    //Problem Constraints
    //1 <= number of nodes <= 105
    //Input Format
    //First and only argument is root node of the binary tree, A.
    //Output Format
    //Return an integer array denoting the preorder traversal of the given binary tree.
    //Example Input
    //Input 1:
    //
    //   1
    //    \
    //     2
    //    /
    //   3
    //Input 2:
    //
    //   1
    //  / \
    // 6   2
    //    /
    //   3
    //Example Output
    //Output 1:
    // [1, 2, 3]
    //Output 2:
    // [1, 6, 2, 3]
    //Example Explanation
    //Explanation 1:
    // The Preoder Traversal of the given tree is [1, 2, 3].
    //Explanation 2:
    // The Preoder Traversal of the given tree is [1, 6, 2, 3].
    ArrayList<Integer> arr = new ArrayList<Integer>();
    public void preOrder(TreeNode A) {
        if(A == null) return;
        arr.add(A.val);
        preOrder(A.left);
        preOrder(A.right);
    }
    // Recursive Solution
    public ArrayList<Integer> preorderTraversalRecursion(TreeNode A) {
        preOrder(A);
        return arr;
    }

    // Iterative Solution
    public ArrayList < Integer > preorderTraversalIteration(TreeNode A) {
        ArrayList < Integer > res = new ArrayList < Integer > ();
        Stack< TreeNode > stack = new Stack < > ();
        if (A == null)
            return res;
        stack.push(A);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            res.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return res;
    }
}
