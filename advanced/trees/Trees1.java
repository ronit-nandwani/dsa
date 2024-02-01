package advanced.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

// Definition of binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Trees1 {

    // Binary tree from inorder and postorder
    // Given the inorder and postorder traversal of a tree, construct the binary tree.
    // NOTE: You may assume that duplicates do not exist in the tree.
    // Problem Constraints
    // 1 <= number of nodes <= 105
    // Input Format
    // First argument is an integer array A denoting the inorder traversal of the tree.
    // Second argument is an integer array B denoting the postorder traversal of the tree.
    // Output Format
    // Return the root node of the binary tree.
    // Example Input
    // Input 1:
    // A = [2, 1, 3]
    // B = [2, 3, 1]
    // Input 2:
    // A = [6, 1, 3, 2]
    // B = [6, 3, 2, 1]
    // Example Output
    // Output 1:
    // 1
    // / \
    // 2   3
    // Output 2:
    // 1  
    // / \
    // 6   2
    //     /
    // 3
    // Example Explanation
    // Explanation 1:
    // Create the binary tree and return the root node of the tree.
    HashMap<Integer, Integer> hm = new HashMap<>();
    public TreeNode build(int[] inOT, int[] postOT, int inL, int inR, int postR) {
        if(inL > inR) return null;

        TreeNode root = new TreeNode(postOT[postR]);
        int index = hm.get(root.val);
        int cntR = inR - index;
        root.left = build(inOT, postOT, inL, index-1,postR-cntR-1);
        root.right = build(inOT, postOT, index+1, inR,postR-1);
        return root;
    }
    public TreeNode buildTree(int[] A, int[] B) {
        for(int i=0;i<A.length;i++) {
            hm.put(A[i],i);
        }
        TreeNode root = build(A,B,0,A.length-1,B.length-1);
        return root;
    }

    // InOrder Traversal
    // Given a binary tree, return the inorder traversal of its nodes' values.
    // Problem Constraints
    // 1 <= number of nodes <= 105
    // Input Format
    // First and only argument is root node of the binary tree, A.
    // Output Format
    // Return an integer array denoting the inorder traversal of the given binary tree.
    // Example Input
    // Input 1:
    // 1
    //     \
    //     2
    //     /
    // 3
    // Input 2:
    // 1
    // / \
    // 6   2
    //     /
    // 3
    // Example Output
    // Output 1:
    // [1, 3, 2]
    // Output 2:
    // [6, 1, 3, 2]
    // Example Explanation
    // Explanation 1:
    // The Inorder Traversal of the given tree is [1, 3, 2].
    // Explanation 2:
    // The Inorder Traversal of the given tree is [6, 1, 3, 2].

    ArrayList<Integer> inArr = new ArrayList<Integer>();
    public void inOrder(TreeNode A) {
        if(A == null) return;
        inOrder(A.left);
        inArr.add(A.val);
        inOrder(A.right);
    }
    public ArrayList<Integer> inorderTraversal(TreeNode A) {
        inOrder(A);
        return inArr;
    }


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
