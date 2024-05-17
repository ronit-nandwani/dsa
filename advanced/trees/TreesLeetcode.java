package advanced.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class TreesLeetcode {

    // 145. Binary Tree Postorder Traversal
    // Solved
    // Easy
    // Topics
    // Companies
    // Given the root of a binary tree, return the postorder traversal of its nodes' values.
    

    // Example 1:

    // Input: root = [1,null,2,3]

    // Output: [3,2,1]

    // Explanation:

    // Example 2:

    // Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

    // Output: [4,6,7,5,2,9,8,3,1]

    // Explanation:

    // Example 3:

    // Input: root = []

    // Output: []

    // Example 4:

    // Input: root = [1]

    // Output: [1]

    // Constraints:

    // The number of the nodes in the tree is in the range [0, 100].
    // -100 <= Node.val <= 100
    

    // Follow up: Recursive solution is trivial, could you do it iteratively?

    // Solution by me - 0 ms
    List<Integer> list;
    public void postOrder(TreeNode root) {
        if(root == null) return;
        
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        list = new ArrayList<Integer>();
        postOrder(root);
        return list;
    }

    // Solution other
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> al= new ArrayList<>();
        Stack<TreeNode> stack1 =new Stack<>();
        Stack<TreeNode> stack2 =new Stack<>();
        TreeNode temp=root;
        if(root!=null)stack1.push(root);
        while(!stack1.isEmpty()){
            temp=stack1.pop();
            stack2.push(temp);
            if(temp.left!=null) stack1.push(temp.left);
            if(temp.right!=null) stack1.push(temp.right);
        }
        while(!stack2.isEmpty()){
            al.add(stack2.pop().val);
        }
        return al;
    }




    // ---------------------------------------------------------------


    // 590. N-ary Tree Postorder Traversal
    // Solved
    // Easy
    // Topics
    // Companies
    // Given the root of an n-ary tree, return the postorder traversal of its nodes' values.

    // Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)

    

    // Example 1:


    // Input: root = [1,null,3,2,4,null,5,6]
    // Output: [5,6,3,2,4,1]
    // Example 2:


    // Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    // Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
    

    // Constraints:

    // The number of nodes in the tree is in the range [0, 104].
    // 0 <= Node.val <= 104
    // The height of the n-ary tree is less than or equal to 1000.
    

    // Follow up: Recursive solution is trivial, could you do it iteratively?

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Solution by me - 0 ms
    public List<Integer> postorder(Node root) {
        // Recursize - fast
        List<Integer> result = new ArrayList<>();
        postorderTraversal(root, result);
        return result;
        
        
       // // Iterative - slow
        //LinkedList<Integer> result = new LinkedList<>();
        //if (root == null) {
        //    return result;
        //}

        //Stack<Node> stack = new Stack<>();
        //stack.push(root);

       // while (!stack.isEmpty()) {
         //   Node node = stack.pop();
           // // Instead of adding at the end, we add at the beginning
            //result.addFirst(node.val);
            
            //// Add all children to the stack
            //for (Node child : node.children) {
                //stack.push(child);
            //}
        //}

        //return result;
    }
    private void postorderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        // Recurse on each child
        for (Node child : node.children) {
            postorderTraversal(child, result);
        }
        
        // Add the node's value after visiting all children
        result.add(node.val);
    }


    // --------------------------------------------


    // 1382. Balance a Binary Search Tree
    // Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

    // A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

    

    // Example 1:


    // Input: root = [1,null,2,null,3,null,4,null,null]
    // Output: [2,1,3,null,null,null,4]
    // Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
    // Example 2:


    // Input: root = [2,1,3]
    // Output: [2,1,3]
    

    // Constraints:

    // The number of nodes in the tree is in the range [1, 104].
    // 1 <= Node.val <= 105
    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return bst(0, ordered.size() - 1);
    }
    private TreeNode bst(int start, int end) {
        if (start > end) return null;
        else if (start == end) {
            TreeNode ret = ordered.get(start);
            ret.left = null;
            ret.right = null;
            return ret;
        } else {
            int mid = start + (end - start) / 2;
            TreeNode ret = ordered.get(mid);
            ret.left = bst(start, mid - 1);
            ret.right = bst(mid + 1, end);
            return ret;
        }
    }
    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        ordered.add(node);
        inOrder(node.right);
    }
    private List<TreeNode> ordered = new ArrayList<>();


    // 1038. Binary Search Tree to Greater Sum Tree
    // Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
    // As a reminder, a binary search tree is a tree that satisfies these constraints:
    // The left subtree of a node contains only nodes with keys less than the node's key.
    // The right subtree of a node contains only nodes with keys greater than the node's key.
    // Both the left and right subtrees must also be binary search trees.

    // Example 1:

    // Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
    // Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    // Example 2:

    // Input: root = [0,null,1]
    // Output: [1,null,1]
    
    // Constraints:

    // The number of nodes in the tree is in the range [1, 100].
    // 0 <= Node.val <= 100
    // All the values in the tree are unique.
    
    // Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        
        // Traverse right subtree
        traverse(node.right);
        
        // Update the current node's value with the running sum
        sum += node.val;
        node.val = sum;
        
        // Traverse left subtree
        traverse(node.left);
    }
}
