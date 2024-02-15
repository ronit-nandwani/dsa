package advanced.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Trees2 {
    // Height Balanced Tree
    //     Given a root of binary tree A, determine if it is height-balanced.
    // A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    // Problem Constraints
    // 1 <= size of tree <= 100000
    // Input Format
    // First and only argument is the root of the tree A.
    // Output Format
    // Return 0 / 1 ( 0 for false, 1 for true ) for this problem.
    // Example Input
    // Input 1:
    //     1
    // / \
    // 2   3
    // Input 2:
    //     1
    //     /
    //     2
    //     /
    // 3
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 0
    // Example Explanation
    // Explanation 1:
    // It is a complete binary tree.
    // Explanation 2:
    // Because for the root node, left subtree has depth 2 and right subtree has depth 0. 
    // Difference = 2 > 1.
    int isBalanced = 1;
    public int height(TreeNode root) {
        if(root == null) return -1;
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        if(Math.abs(lHeight-rHeight)>1) {
            isBalanced = 0;
        }
        return Math.max(lHeight,rHeight)+1;
    }
    public int isBalanced(TreeNode A) {
        height(A);
        return isBalanced;
    }
    
    // Right View of Binary Tree
    // Problem Description
    //Given a binary tree of integers denoted by root A. Return an array of integers representing the right view of the Binary tree.
    //Right view of a Binary Tree is a set of nodes visible when the tree is visited from Right side.
    //Problem Constraints
    //1 <= Number of nodes in binary tree <= 100000
    //0 <= node values <= 10^9
    //Input Format
    //First and only argument is head of the binary tree A.
    //Output Format
    //Return an array, representing the right view of the binary tree.
    //Example Input
    //Input 1:
    //
    //
    //            1
    //          /   \
    //         2    3
    //        / \  / \
    //       4   5 6  7
    //      /
    //     8
    //Input 2:
    //
    //
    //            1
    //           /  \
    //          2    3
    //           \
    //            4
    //             \
    //              5
    //
    //
    //Example Output
    //Output 1:
    // [1, 3, 7, 8]
    //Output 2:
    // [1, 3, 4, 5]
    //Example Explanation
    //Explanation 1:
    //Right view is described.
    //Explanation 2:
    //Right view is described.
    public ArrayList<Integer> rightViewBinaryTree(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        ArrayList<Integer> a = new ArrayList<Integer>();
        if(root == null) return a;

        q.addLast(root);
        TreeNode last = root;
        while(q.size() != 0) {
            TreeNode x = q.removeFirst();
            if(x.left != null) q.addLast(x.left);
            if(x.right != null) q.addLast(x.right);
            if(x == last) {
                a.add(last.val);
                if(!q.isEmpty()) last = q.peekLast();
            }
        }
        return a;
    }
    
    // Level Order
    // Given a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
    //Problem Constraints
    //1 <= number of nodes <= 105
    //Input Format
    //First and only argument is root node of the binary tree, A.
    //Output Format
    //Return a 2D integer array denoting the level order traversal of the given binary tree.
    //Example Input
    //Input 1:
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //Input 2:
    //
    //   1
    //  / \
    // 6   2
    //    /
    //   3
    //Example Output
    //Output 1:
    //
    // [
    //   [3],
    //   [9, 20],
    //   [15, 7]
    // ]
    //Output 2:
    // [
    //   [1]
    //   [6, 2]
    //   [3]
    // ]
    //Example Explanation
    //Explanation 1:
    // Return the 2D array. Each row denotes the traversal of each level.
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if(root == null) return ans;

        q.addLast(root);
        TreeNode last = root;
        ArrayList<Integer> a = new ArrayList<Integer>();
        while(q.size() != 0) {
            TreeNode x = q.removeFirst();
            a.add(x.val);
            if(x.left != null) q.addLast(x.left);
            if(x.right != null) q.addLast(x.right);
            if(x == last) {
                ans.add(a);
                a = new ArrayList<Integer>();
                if(!q.isEmpty()) last = q.peekLast();
            }
        }
        return ans;
    }
}
