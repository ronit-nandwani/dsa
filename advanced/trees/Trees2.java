package advanced.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

class Pair {
    TreeNode node;
    int distance;

    public Pair(TreeNode node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class Trees2 {
    // Vertical Order Traversal
    //     Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.
    // NOTE: If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.
    // Problem Constraints
    // 0 <= number of nodes <= 105
    // Input Format
    // First and only arument is a pointer to the root node of binary tree, A.
    // Output Format
    // Return a 2D array denoting the vertical order traversal of tree as shown.
    // Example Input
    // Input 1:
    //     6
    //     /   \
    // 3     7
    // / \     \
    // 2   5     9
    // Input 2:
    //     1
    //     /   \
    // 3     7
    // /       \
    // 2         9
    // Example Output
    // Output 1:
    // [
    //     [2],
    //     [3],
    //     [6, 5],
    //     [7],
    //     [9]
    // ]
    // Output 2:
    // [
    //     [2],
    //     [3],
    //     [1],
    //     [7],
    //     [9]
    // ]
    // Example Explanation
    // Explanation 1:
    // First row represent the verical line 1 and so on.
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
        TreeMap<Integer,ArrayList<TreeNode>> tm = new TreeMap<Integer,ArrayList<TreeNode>>();
        Deque<Pair> q = new ArrayDeque<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if(root == null) return ans;

        q.add(new Pair(root, 0));
        while(q.size() != 0) {
            Pair x = q.removeFirst();
            if(tm.containsKey(x.distance)) {
                ArrayList<TreeNode> list = tm.get(x.distance);
                list.add(x.node);
                tm.put(x.distance, list);
            } else {
                ArrayList<TreeNode> list = new ArrayList<>();
                list.add(x.node);
                tm.put(x.distance, list);
            }
            if(x.node.left != null) {
                q.addLast(new Pair(x.node.left,x.distance-1));
            }
            if(x.node.right != null) {
                q.addLast(new Pair(x.node.right,x.distance+1));
            }
        }

        for(int key : tm.keySet()) {
            ArrayList<Integer> nodeList = new ArrayList<>();
            for (TreeNode node : tm.get(key)) {
                nodeList.add(node.val);
            }
            ans.add(nodeList);
        }
        return ans;
    }
    // Solution by them
//     public ArrayList < ArrayList < Integer >> verticalOrderTraversal(TreeNode root) {
//     ArrayList < ArrayList < Integer >> ans = new ArrayList < ArrayList < Integer >> ();

//     ArrayList < Integer > ar = new ArrayList < Integer > ();
//     if (root == null) {
//       return ans;
//     }

//     TreeMap < Integer, ArrayList < Integer >> tm = new TreeMap < > ();
//     Queue < Pair > q = new LinkedList < Pair > ();
//     q.add(new Pair(root, 0));

//     while (!q.isEmpty()) {
//       Pair p = q.poll();
//       int v = p.t.val;
//       int x = p.x;
//       ar = new ArrayList < Integer > ();
//       if (tm.containsKey(x)) {
//         ar = tm.get(x);
//         ar.add(v);
//       } else {
//         ar.add(v);
//       }

//       tm.put(x, ar);
//       if (p.t.left != null) {
//         q.add(new Pair(p.t.left, x - 1));
//       }
//       if (p.t.right != null) {
//         q.add(new Pair(p.t.right, x + 1));
//       }
//     }
//     for (int x: tm.keySet()) {
//       ArrayList < Integer > f = tm.get(x);
//       ans.add(f);
//     }
//     return ans;
//   }


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
