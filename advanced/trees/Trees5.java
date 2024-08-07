package advanced.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class TreeLinkNode {
        int val;
     TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

public class Trees5 {
    // Sum binary tree or not

    // Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.

    // Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes present in its left subtree and right subtree.

    // An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.

    // Return 1 if it sum-binary tree else return 0.

    // Problem Constraints
    // 1 <= length of the array <= 100000

    // 0 <= node values <= 50

    // Input Format
    // The only argument given is the root node of tree A.

    // Output Format
    // Return 1 if it is sum-binary tree else return 0.

    // Example Input
    // Input 1:

    //     26
    //     /    \
    //     10     3
    // /  \     \
    // 4   6      3
    // Input 2:

    //     26
    //     /    \
    //     10     3
    // /  \     \
    // 4   6      4

    // Example Output
    // Output 1:

    // 1
    // Output 2:

    // 0

    // Example Explanation
    // Explanation 1:

    // All leaf nodes are considered as SumTree. 
    // Value of Node 10 = 4 + 6.
    // Value of Node 3 = 0 + 3 
    // Value of Node 26 = (10 + 4 + 6) + 6 
    // Explanation 2:

    // Sum of left subtree and right subtree is 27 which is not equal to the value of root node which is 26.

    public class Solution {
        int isSumBinaryTree = 1;
        public int sumBinaryTree(TreeNode root) {
            if(root == null) return 0;
            if(root.left == null && root.right == null) return root.val;
            int leftSum = sumBinaryTree(root.left);
            int rightSum = sumBinaryTree(root.right);
            if(root.val != (leftSum+rightSum)) {
                isSumBinaryTree = 0;
            }
            return root.val + leftSum + rightSum;
        }
        public int solve(TreeNode A) {
            sumBinaryTree(A);
            return isSumBinaryTree;
        }
    }
    // Solution by team

    public class Solution1 {
        public int solve(TreeNode A) {
            return checksumtree(A);
        }
        public static int isLeaf(TreeNode node) {
            if (node == null)
                return 0;
            if (node.left == null && node.right == null)
                return 1;
            return 0;
        }
        public static int isSumTree(TreeNode node) {
            int ls, rs;
            if (node == null || isLeaf(node) == 1)
                return 1;
    
            if (isSumTree(node.left) == 1 && isSumTree(node.right) == 1) {
                if (node.left == null)
                    ls = 0;
                else if (isLeaf(node.left) == 1)
                    ls = node.left.val;
                else
                    ls = 2 * (node.left.val);
                if (node.right == null)
                    rs = 0;
                else if (isLeaf(node.right) == 1)
                    rs = node.right.val;
                else
                    rs = 2 * (node.right.val);
                if (node.val == ls + rs)
                    return 1;
                else
                    return 0;
            }
            return 0;
        }
    
        public static int checksumtree(TreeNode root) {
            if (isSumTree(root) == 1)
                return 1;
            return 0;
        }
    }



    // Identical Binary Trees

    // Given two binary trees, check if they are equal or not.

    // Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

    // Problem Constraints
    // 1 <= number of nodes <= 105

    // Input Format
    // The first argument is a root node of the first tree, A.

    // The second argument is a root node of the second tree, B.

    // Output Format
    // Return 0 / 1 ( 0 for false, 1 for true ) for this problem.

    // Example Input
    // Input 1:

    // 1       1
    // / \     / \
    // 2   3   2   3
    // Input 2:

    // 1       1
    // / \     / \
    // 2   3   3   3

    // Example Output
    // Output 1:

    // 1
    // Output 2:

    // 0

    // Example Explanation
    // Explanation 1:

    // Both trees are structurally identical and the nodes have the same value.
    // Explanation 2:

    // Values of the left child of the root node of the trees are different.

    public int isSameTree(TreeNode A, TreeNode B) {
        if(A == null && B == null)  return 1;
        if(A == null && B != null) return 0;
        if(A != null && B == null) return 0;
        if(A.val != B.val) return 0;
        if(isSameTree(A.left, B.left) == 0 || isSameTree(A.right, B.right) == 0) {
            return 0;
        }
        return 1;
    }

    // Solution by team
    /**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int isSameTree(TreeNode A, TreeNode B) {
        return sameTree(A, B);
    }

    public int sameTree(TreeNode A, TreeNode B) {
        if (A == null && B == null)
            return 1;
        if (A == null || B == null)
            return 0;
        if (A.val != B.val)
            return 0;

        int num = 1;
        num = num & sameTree(A.left, B.left) & sameTree(A.right, B.right);
        return num;
    }
}


    // Invert binart tree

    // Given a binary tree A, invert the binary tree and return it.

    // Inverting refers to making the left child the right child and vice versa.

    // Problem Constraints
    // 1 <= size of tree <= 100000

    // Input Format
    // First and only argument is the head of the tree A.

    // Output Format
    // Return the head of the inverted tree.

    // Example Input
    // Input 1:
    
    //     1
    // /   \
    // 2     3
    // Input 2:

    //     1
    // /   \
    // 2     3
    // / \   / \
    // 4   5 6   7

    // Example Output
    // Output 1:
    
    //     1
    // /   \
    // 3     2
    // Output 2:

    //     1
    // /   \
    // 3     2
    // / \   / \
    // 7   6 5   4

    // Example Explanation
    // Explanation 1:

    // Tree has been inverted.
    // Explanation 2:

    // Tree has been inverted.
    public TreeNode invertTree(TreeNode A) {
        if(A == null) return null;
        // if(A.left == null && A.right == null) return A;

        TreeNode inv_left = invertTree(A.left);
        TreeNode inv_right = invertTree(A.right);

        A.left = inv_right;
        A.right = inv_left;

        return A;
    }

    // Solution by team
    /**
     * Definition for binary tree
     * class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) {
     *      val = x;
     *      left=null;
     *      right=null;
     *     }
     * }
     */
    public class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = invertTree(root.right);
            TreeNode left = invertTree(root.left);
            root.left = right;
            root.right = left;
            return root;
        }
    }


    // Next Pointer Binary tree

    // Given a binary tree,
    // Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    // Initially, all next pointers are set to NULL.
    // Assume perfect binary tree.
    // Problem Constraints
    // 1 <= Number of nodes in binary tree <= 100000
    // 0 <= node values <= 10^9
    // Input Format
    // First and only argument is head of the binary tree A.
    // Output Format
    // Return the head of the binary tree after the changes are made.
    // Example Input
    // Input 1:
    //     1
    //     /  \
    // 2    3
    // Input 2:
    //         1
    //     /  \
    //     2    5
    //     / \  / \
    //     3  4  6  7
    // Example Output
    // Output 1:
    //         1 -> NULL
    //     /  \
    //     2 -> 3 -> NULL
    // Output 2:
    //         1 -> NULL
    //     /  \
    //     2 -> 5 -> NULL
    //     / \  / \
    //     3->4->6->7 -> NULL
    // Example Explanation
    // Explanation 1:
    // Next pointers are set as given in the output.
    // Explanation 2:

    // Next pointers are set as given in the output.
    public void connect(TreeLinkNode root) {
        TreeLinkNode temp = root;
        TreeLinkNode last = root;
        TreeLinkNode r = root;

        while(r != null) {
            if(r.left != null) {
                temp.next = r.left;
                temp = temp.next;
            }

            if(r.right != null) {
                temp.next = r.right;
                temp = temp.next;
            }

            if(r != last) {
                r = r.next;
            } else {
                r = last.next;
                last.next = null;
                last = temp;
            }
        }
    }
    // Solution by team
    // public class Solution {
    //     public void connect(TreeLinkNode root) {
    //         if (root == null)
    //             return;

    //         Queue < TreeLinkNode > q = new LinkedList < TreeLinkNode > ();
    //         q.offer(root);

    //         while (q.size() > 0) {
    //             int n = q.size();
    //             for (int i = 0; i < n; i++) {
    //                 TreeLinkNode front = q.poll();
    //                 if (i == n - 1) {
    //                     front.next = null;
    //                 } else {
    //                     front.next = q.peek();
    //                 }
    //                 if (front.left != null)
    //                     q.offer(front.left);
    //                 if (front.right != null)
    //                     q.offer(front.right);
    //             }
    //         }
    //         return;
    //     }
    // }

    // Diameter of binary Tree
    
    // Given a Binary Tree A consisting of N integer nodes, you need to find the diameter of the tree.
    // The diameter of a tree is the number of edges on the longest path between two nodes in the tree.
    // Problem Constraints
    // 0 <= N <= 105
    // Input Format
    // First and only Argument represents the root of binary tree A.
    // Output Format
    // Return an single integer denoting the diameter of the tree.
    // Example Input
    // Input 1:
    //         1
    //         /   \
    //         2     3
    //     / \
    //     4   5
    // Input 2:
    //             1
    //         /   \
    //         2     3
    //         / \     \
    //     4   5     6
    // Example Output
    // Output 1:
    // 3
    // Output 2:
    // 4
    // Example Explanation
    // Explanation 1:
    // Longest Path in the tree is 4 -> 2 -> 1 -> 3 and the number of edges in this path is 3 so diameter is 3.
    // Explanation 2:
    // Longest Path in the tree is 4 -> 2 -> 1 -> 3 -> 6 and the number of edges in this path is 4 so diameter is 4.
    int ans = 0;
    public int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = height(root.left);
        int r = height(root.right);
        ans = Math.max(ans, l+r+1);
        return Math.max(l,r)+1;
    }
    public int diameterOfBinaryTree(TreeNode A) {
        height(A);
        return ans-1;
    }
    // Solution by team
    // public class Solution {
    //     static int diameter;
    //     public int solve(TreeNode A) {
    //         diameter = 0;
    //         height(A);
    //         return diameter;
    //     }
    //     public static int height(TreeNode root) {
    //         if (root == null)
    //             return -1;
    //         int leftHeight = height(root.left);
    //         int rightHeight = height(root.right);
    //         diameter = Math.max(diameter, 2 + leftHeight + rightHeight);
    //         return 1 + Math.max(leftHeight, rightHeight);
    //     }
    // }


    // Equal tree Partition

    // Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after removing exactly one edge on the original tree.
    // Problem Constraints
    // 1 <= size of tree <= 100000
    // 0 <= value of node <= 109
    // Input Format
    // First and only argument is head of tree A.
    // Output Format
    // Return 1 if the tree can be partitioned into two trees of equal sum else return 0.
    // Example Input
    // Input 1:
    //                 5
    //             /  \
    //             3    7
    //             / \  / \
    //             4  6  5  6
    // Input 2:
    //                 1
    //             / \
    //             2   10
    //                 / \
    //                 20  2
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 0
    // Example Explanation
    // Explanation 1:
    // Remove edge between 5(root node) and 7: 
    //         Tree 1 =                                               Tree 2 =
    //                         5                                                     7
    //                     /                                                     / \
    //                     3                                                     5   6    
    //                     / \
    //                     4   6
    //         Sum of Tree 1 = Sum of Tree 2 = 18
    // Explanation 2:
    // The given Tree cannot be partitioned.
    public long treeSum(TreeNode root) {
        if(root == null) return 0;
        return root.val + treeSum(root.left) + treeSum(root.right); // Post order
    }

    public boolean check(TreeNode root, long sum) {
        if(root == null) return false;
        if(check(root.left,sum) || check(root.right,sum)) return true;
        long left = treeSum(root.left);
        long right = treeSum(root.right);
        return ((left+right+root.val)==sum);
    }

    public long solve(TreeNode A) {
        long totalSum = treeSum(A);
        if(totalSum % 2 == 1) {
            return 0;
        }
        boolean f = check(A, totalSum/2);
        if(f == true) {
            return 1;
        }
        return 0;
    }
    // Solution by team
    //     public class Solution {
    //     public int solve(TreeNode a) {
    //         Map < Long, Integer > map = new HashMap < > ();
    //         long tot = populate(a, map);
    //         // since total sum can also be zero
    //         if (tot == 0) 
    //             return map.getOrDefault(tot, 0) > 1 ? 1 : 0;
    //         return tot % 2 == 0 && map.containsKey(tot / 2) ? 1 : 0;
    //     }
    //     public long populate(TreeNode a, Map < Long, Integer > map) {
    //         if (a == null) 
    //             return 0;
    //         long sum = a.val + populate(a.left, map) + populate(a.right, map);
    //         map.put(sum, map.getOrDefault(sum, 0) + 1);
    //         return sum;
    //     }
    // }

    // Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
    //Problem Constraints
    //1 <= number of nodes <= 105
    //-100000 <= B, value of nodes <= 100000
    //Input Format
    //First argument is a root node of the binary tree, A.
    //Second argument is an integer B denoting the sum.
    //Output Format
    //Return 1, if there exist root-to-leaf path such that adding up all the values along the path equals the given sum. Else, return 0.
    //Example Input
    //Input 1:
    // Tree:    5
    //         / \
    //        4   8
    //       /   / \
    //      11  13  4
    //     /  \      \
    //    7    2      1
    // B = 22
    //Input 2:
    // Tree:    5
    //         / \
    //        4   8
    //       /   / \
    //     -11 -13  4
    // B = -1
    //Example Output
    //Output 1:
    // 1
    //Output 2:
    // 0
    //Example Explanation
    //Explanation 1:
    // There exist a root-to-leaf path 5 -> 4 -> 11 -> 2 which has sum 22. So, return 1.
    //Explanation 2:
    // There is no path which has sum -1.
    public int pathSum(TreeNode root, int B) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            if(root.val == B) {
                return 1;
            } else {
                return 0;
            }
        }
        if(pathSum(root.left,B-root.val) == 1 || pathSum(root.right,B-root.val) == 1) {
            return 1;
        }
        return 0;
    }
    // Solution provided for path sum
    // public int hasPathSum(TreeNode A, int B) {
    //        boolean status = sum(A, 0, B);
    //        return status ? 1 : 0;
    //    }
    //    public boolean sum(TreeNode A, int curSum, int reqSum) {
    //        if (A == null) {
    //            return false;
    //        }
    //        if (A.left == null && A.right == null) {
    //            curSum += A.val;
    //            if (curSum == reqSum)
    //                return true;
    //            return false;
    //        }
    //        int sum = curSum + A.val;
    //        return sum(A.left, sum, reqSum) || sum(A.right, sum, reqSum);
    //    }
}
