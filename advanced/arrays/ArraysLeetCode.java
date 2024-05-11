package advanced.arrays;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// import javax.swing.tree.TreeNode;

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

public class ArraysLeetCode {

    // 1894. Find the Student that Will Replace the Chalk
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // There are n students in a class numbered from 0 to n - 1. The teacher will give each student a problem starting with the student number 0, then the student number 1, and so on until the teacher reaches the student number n - 1. After that, the teacher will restart the process, starting with the student number 0 again.

    // You are given a 0-indexed integer array chalk and an integer k. There are initially k pieces of chalk. When the student number i is given a problem to solve, they will use chalk[i] pieces of chalk to solve that problem. However, if the current number of chalk pieces is strictly less than chalk[i], then the student number i will be asked to replace the chalk.

    // Return the index of the student that will replace the chalk pieces.

    

    // Example 1:

    // Input: chalk = [5,1,5], k = 22
    // Output: 0
    // Explanation: The students go in turns as follows:
    // - Student number 0 uses 5 chalk, so k = 17.
    // - Student number 1 uses 1 chalk, so k = 16.
    // - Student number 2 uses 5 chalk, so k = 11.
    // - Student number 0 uses 5 chalk, so k = 6.
    // - Student number 1 uses 1 chalk, so k = 5.
    // - Student number 2 uses 5 chalk, so k = 0.
    // Student number 0 does not have enough chalk, so they will have to replace it.
    // Example 2:

    // Input: chalk = [3,4,1,2], k = 25
    // Output: 1
    // Explanation: The students go in turns as follows:
    // - Student number 0 uses 3 chalk so k = 22.
    // - Student number 1 uses 4 chalk so k = 18.
    // - Student number 2 uses 1 chalk so k = 17.
    // - Student number 3 uses 2 chalk so k = 15.
    // - Student number 0 uses 3 chalk so k = 12.
    // - Student number 1 uses 4 chalk so k = 8.
    // - Student number 2 uses 1 chalk so k = 7.
    // - Student number 3 uses 2 chalk so k = 5.
    // - Student number 0 uses 3 chalk so k = 2.
    // Student number 1 does not have enough chalk, so they will have to replace it.
    

    // Constraints:

    // chalk.length == n
    // 1 <= n <= 105
    // 1 <= chalk[i] <= 105
    // 1 <= k <= 109


    // Solutionby me - 2 ms
    public int chalkReplacer(int[] chalk, int k) {
        // Step 1: Calculate the total chalk needed for one full round
        long totalChalk = 0;
        for (int c : chalk) {
            totalChalk += c;
        }

        // Step 2: Reduce k using modulo to handle full rounds efficiently
        k %= totalChalk;

        // Step 3: Find the student who will run out of chalk
        for (int i = 0; i < chalk.length; i++) {
            if (k < chalk[i]) {
                return i;
            }
            k -= chalk[i];
        }

        // This line should never be reached if the input constraints are met
        return -1;
    }

    // Solution fastest - 2 ms

    public int chalkReplacerFastest(int[] chalk, int p) {
        long k = (long)(p);
        long sum = 0;
        for (int i = 0; i < chalk.length; i++) {
            sum += chalk[i];
        }
        if(k % sum == 0)return 0;
        long value = 0;
        long count = 0;
        while (sum + value < k) {
            value = (sum) + value;
            count++;
        }
       if(k - (sum * count) > 0){
            k = k - (sum * count);
       }
        

        for(int i = 0; i < chalk.length; i++){
            if(k - chalk[i] > -1){
                 k = k - chalk[i];
            }else {
                return i;
            }
           
        }
        return 0;
    }


    // 874. Walking Robot Simulation
    // Solved
    // Medium
    // Topics
    // Companies
    // A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:

    // -2: Turn left 90 degrees.
    // -1: Turn right 90 degrees.
    // 1 <= k <= 9: Move forward k units, one unit at a time.
    // Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.

    // Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).

    // Note:

    // North means +Y direction.
    // East means +X direction.
    // South means -Y direction.
    // West means -X direction.
    // There can be obstacle in [0,0].
    

    // Example 1:

    // Input: commands = [4,-1,3], obstacles = []
    // Output: 25
    // Explanation: The robot starts at (0, 0):
    // 1. Move north 4 units to (0, 4).
    // 2. Turn right.
    // 3. Move east 3 units to (3, 4).
    // The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
    // Example 2:

    // Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
    // Output: 65
    // Explanation: The robot starts at (0, 0):
    // 1. Move north 4 units to (0, 4).
    // 2. Turn right.
    // 3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
    // 4. Turn left.
    // 5. Move north 4 units to (1, 8).
    // The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
    // Example 3:

    // Input: commands = [6,-1,-1,6], obstacles = []
    // Output: 36
    // Explanation: The robot starts at (0, 0):
    // 1. Move north 6 units to (0, 6).
    // 2. Turn right.
    // 3. Turn right.
    // 4. Move south 6 units to (0, 0).
    // The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
    

    // Constraints:

    // 1 <= commands.length <= 104
    // commands[i] is either -2, -1, or an integer in the range [1, 9].
    // 0 <= obstacles.length <= 104
    // -3 * 104 <= xi, yi <= 3 * 104
    // The answer is guaranteed to be less than 231.


    // Solution by me - 36 ms O(N)

    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: North, East, South, West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0; // Starts facing North
        
        // Obstacle set
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }
        
        int x = 0, y = 0; // Starting position
        int maxDistanceSquared = 0;

        for (int command : commands) {
            if (command == -2) {
                // Turn left: counter-clockwise turn
                directionIndex = (directionIndex + 3) % 4;
            } else if (command == -1) {
                // Turn right: clockwise turn
                directionIndex = (directionIndex + 1) % 4;
            } else {
                // Move forward in the current direction
                int dx = directions[directionIndex][0];
                int dy = directions[directionIndex][1];

                for (int step = 0; step < command; step++) {
                    int nextX = x + dx;
                    int nextY = y + dy;
                    
                    if (obstacleSet.contains(nextX + "," + nextY)) {
                        // Encounter obstacle, stop moving
                        break;
                    }
                    
                    // Move to the next position
                    x = nextX;
                    y = nextY;
                    
                    // Update the maximum distance squared
                    maxDistanceSquared = Math.max(maxDistanceSquared, x * x + y * y);
                }
            }
        }

        return maxDistanceSquared; 
    }

    //Fastest Solution - 10 ms
    public int robotSimFastest(int[] commands, int[][] obstacles) {
        Robot robot = new Robot();
        Set<Obstacle> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(new Obstacle(obstacle[0], obstacle[1]));
        }
        for (int command : commands) robot.handleCommand(command, obstacleSet);
        return robot.maxDistance;
    }
    private static class Robot {
        public int x = 0, y = 0, dir = 0, maxDistance = 0;
        public Set<Obstacle> obstacles;

        Robot() {}
        private void handleCommand(int command, Set<Obstacle> obstacles) {
            switch (command) {
                case -2:
                    dir = (dir + 3) % 4;
                    return;
                case -1:
                    dir = (dir + 1) % 4;
                    return;
                default: {
                    while (command-- > 0) {
                        int newX = x, newY = y;
                        switch (dir) {
                            case 0: ++newY; break;
                            case 1: ++newX; break;
                            case 2: --newY; break;
                            default: --newX;
                        }
                        if (!obstacles.contains(new Obstacle(newX, newY))) {
                            x = newX;
                            y = newY;
                        }
                    }
                    maxDistance = Math.max(x * x + y * y, maxDistance);
                }
            }
        }
    }
    private static class Obstacle {
        public int x, y;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object other) {
            return x == ((Obstacle) other).x && y == ((Obstacle) other).y;
        }
        public int hashCode() {
            return x + y * 413;
        }
    }



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

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    // Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

    // Example 1:

    // Input: nums = [8,2,4,7], limit = 4
    // Output: 2 
    // Explanation: All subarrays are: 
    // [8] with maximum absolute diff |8-8| = 0 <= 4.
    // [8,2] with maximum absolute diff |8-2| = 6 > 4. 
    // [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
    // [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
    // [2] with maximum absolute diff |2-2| = 0 <= 4.
    // [2,4] with maximum absolute diff |2-4| = 2 <= 4.
    // [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
    // [4] with maximum absolute diff |4-4| = 0 <= 4.
    // [4,7] with maximum absolute diff |4-7| = 3 <= 4.
    // [7] with maximum absolute diff |7-7| = 0 <= 4. 
    // Therefore, the size of the longest subarray is 2.
    // Example 2:

    // Input: nums = [10,1,2,4,7,2], limit = 5
    // Output: 4 
    // Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
    // Example 3:

    // Input: nums = [4,2,2,2,4,4,2,2], limit = 0
    // Output: 3

    // Constraints:

    // 1 <= nums.length <= 105
    // 1 <= nums[i] <= 109
    // 0 <= limit <= 109
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Maintain max deque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);
            
            // Maintain min deque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);
            
            // Shrink the window if the current window is invalid
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                left++;
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
            }
            
            // Update the maximum length of the valid subarray
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }



    // 1248. Count Number of Nice Subarrays
    // Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

    // Return the number of nice sub-arrays.
    // Example 1:

    // Input: nums = [1,1,2,1,1], k = 3
    // Output: 2
    // Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
    // Example 2:

    // Input: nums = [2,4,6], k = 1
    // Output: 0
    // Explanation: There are no odd numbers in the array.
    // Example 3:

    // Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
    // Output: 16

    // Constraints:

    // 1 <= nums.length <= 50000
    // 1 <= nums[i] <= 10^5
    // 1 <= k <= nums.length
    public int numberOfSubarrays(int[] nums, int goal) {
            
            for(int i=0;i<nums.length;i++)
            {
                nums[i]=nums[i] & 1;
            }
        HashMap<Integer, Integer> sum = new HashMap<>();
        sum.put(0, 1);
        int total = 0;
        int ans = 0;
        for (int n : nums) {
            total += n;
            if (sum.containsKey(total - goal)) {
                int nooftimes = sum.get(Math.abs(total - goal));
                ans += nooftimes;

            }
            sum.put(total, sum.getOrDefault(total, 0) + 1);

        }
        return ans;

        }

        // or with less tc
        // more optimized solution
        // public int numberOfSubarrays(int[] nums, int k) {
        //     for (int i = 0; i < nums.length; i++) {
        //         nums[i] %= 2;
        //     }
            
        //     int[] prefixCount = new int[nums.length + 1];
        //     prefixCount[0] = 1;
        //     int s = 0;
        //     int ans = 0;
            
        //     for (int num : nums) {
        //         s += num;
        //         if (s >= k) {
        //             ans += prefixCount[s - k];
        //         }
        //         prefixCount[s]++;
        //     }
            
        //     return ans;
        // }




    // 1052. Grumpy Bookstore Owner - Sliding Window
    // There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store. You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute and all those customers leave after the end of that minute.
    // On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
    // When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
    // The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.
    // Return the maximum number of customers that can be satisfied throughout the day.
    // Example 1:
    // Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
    // Output: 16
    // Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
    // The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
    // Example 2:
    // Input: customers = [1], grumpy = [0], minutes = 1
    // Output: 1
    // Constraints:
    // n == customers.length == grumpy.length
    // 1 <= minutes <= n <= 2 * 104
    // 0 <= customers[i] <= 1000
    // grumpy[i] is either 0 or 1.

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = grumpy.length;
        int j = 0;
        int curWin = 0;
        int maxWin = 0;

        for(int i=0;j<n;j++) {
            if(grumpy[j] == 1) {
                curWin+=customers[j];
            }
            maxWin = Math.max(curWin, maxWin);
            if(j >= minutes - 1 ) {
                if(grumpy[i] == 1) {
                curWin -= customers[i];
                }
                i++;
            }
        }
        int res = maxWin;
        for(int i=0;i<grumpy.length;i++) {
            if(grumpy[i] == 0) {
                res+=customers[i];
            }
        }
        return res;

    }
}
