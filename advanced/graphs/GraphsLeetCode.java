package advanced.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphsLeetCode {
    // 1905. Count Sub Islands
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

    // An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

    // Return the number of islands in grid2 that are considered sub-islands.

    

    // Example 1:


    // Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
    // Output: 3
    // Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
    // The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
    // Example 2:


    // Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
    // Output: 2 
    // Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
    // The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
    

    // Constraints:

    // m == grid1.length == grid2.length
    // n == grid1[i].length == grid2[i].length
    // 1 <= m, n <= 500
    // grid1[i][j] and grid2[i][j] are either 0 or 1.


    // Solution by me - 20 ms

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int count = 0;
        
        // Traverse through grid2 to find all islands
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an island in grid2
                if (grid2[i][j] == 1) {
                    // Check if this island is a sub-island in grid1
                    if (isSubIsland(grid1, grid2, i, j)) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    // DFS function to check if an island in grid2 is a sub-island in grid1
    private boolean isSubIsland(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid1.length;
        int n = grid1[0].length;
        
        // If out of bounds or water in grid2, stop DFS
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] == 0) {
            return true;
        }
        
        // If this part of grid2 island is not land in grid1, it's not a sub-island
        if (grid1[i][j] == 0) {
            return false;
        }
        
        // Mark this cell as visited in grid2
        grid2[i][j] = 0;
        
        // Initialize isSubIsland to true; any false in neighbors will change it
        boolean isSubIsland = true;
        
        // DFS in 4 directions
        isSubIsland &= isSubIsland(grid1, grid2, i - 1, j);
        isSubIsland &= isSubIsland(grid1, grid2, i + 1, j);
        isSubIsland &= isSubIsland(grid1, grid2, i, j - 1);
        isSubIsland &= isSubIsland(grid1, grid2, i, j + 1);
        
        return isSubIsland;
    }

    // Fastest Solution - 12 ms
    public static int countSubIslandsFastest(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int size = m * n;

        // Initializes union-find structures.
        int[] parents = new int[size];
        for (int x = 1; x < size; x++) {
            parents[x] = x;
        }
        int[] ranks = new int[size];
        int[] areSubislandRoots = new int[size];

        // Process the corner cell.
        int[] firstGridRow1 = grid1[0];
        int[] firstGridRow2 = grid2[0];
        int cornerCell2 = firstGridRow2[0];
        areSubislandRoots[0] = cornerCell2 & firstGridRow1[0];

        // Process the remaining cells in the first row.
        int x = 1;
        int prevCell2 = cornerCell2;
        for (int col = 1; col < n; col++) {
            int cell2 = firstGridRow2[col];
            if (cell2 != 0) {
                areSubislandRoots[x] = firstGridRow1[col];
                if (prevCell2 != 0) {
                    union(x, x - 1, parents, ranks, areSubislandRoots);
                }
            }
            prevCell2 = cell2;
            x++;
        }

        // Process the remaining cells in the first column.
        x = n;
        prevCell2 = cornerCell2;
        for (int row = 1; row < m; row++) {
            int cell2 = grid2[row][0];
            if (cell2 != 0) {
                areSubislandRoots[x] = grid1[row][0];
                if (prevCell2 != 0) {
                    union(x, x - n, parents, ranks, areSubislandRoots);
                }
            }
            prevCell2 = cell2;
            x += n;
        }

        // Process the remaining cells.
        x = n + 1;
        int[] prevGridRow2 = firstGridRow2;
        for (int row = 1; row < m; row++) {
            int[] gridRow1 = grid1[row];
            int[] gridRow2 = grid2[row];
            prevCell2 = gridRow2[0];
            for (int col = 1; col < n; col++) {
                int cell = gridRow2[col];
                if (cell != 0) {
                    areSubislandRoots[x] = gridRow1[col];
                    if (prevCell2 != 0) {
                        union(x, x - 1, parents, ranks, areSubislandRoots);
                    }
                    if (prevGridRow2[col] != 0) {
                        union(x, x - n, parents, ranks, areSubislandRoots);
                    }
                }
                prevCell2 = cell;
                x++;
            }
            prevGridRow2 = gridRow2;
            x++;
        }

        // Count the subislands.
        int numSubislands = 0;
        for (int isSubislandRoot : areSubislandRoots) {
            numSubislands += isSubislandRoot;
        }
        return numSubislands;
    }

    private static void union(int x, int y, int[] parents, int[] ranks, int[] areSubislandRoots) {
        // Find the roots of x and y.
        int xParent = parents[x];
        while (x != xParent) {
            x = xParent;
            xParent = parents[x];
        }
        int yParent = parents[y];
        while (y != yParent) {
            y = yParent;
            yParent = parents[y];
        }

        // Stop if the roots are already the same.
        if (x == y) {
            return;
        }

        // Merge the two roots.
        int rankCmp = ranks[y] - ranks[x];
        int isSubislandRoot = areSubislandRoots[x] & areSubislandRoots[y];
        if (rankCmp > 0) {
            parents[x] = y;
            areSubislandRoots[x] = 0;
            areSubislandRoots[y] = isSubislandRoot;
        } else {
            parents[y] = x;
            areSubislandRoots[y] = 0;
            areSubislandRoots[x] = isSubislandRoot;
            ranks[x] += ~rankCmp >>> 31; // 1 if rankCmp == 0, 0 if rankCmp < 0
        }
    }



    // -----------------------------------------------------------------------


    // 947. Most Stones Removed with Same Row or Column
    // Solved
    // Medium
    // Topics
    // Companies
    // On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

    // A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

    // Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

    

    // Example 1:

    // Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    // Output: 5
    // Explanation: One way to remove 5 stones is as follows:
    // 1. Remove stone [2,2] because it shares the same row as [2,1].
    // 2. Remove stone [2,1] because it shares the same column as [0,1].
    // 3. Remove stone [1,2] because it shares the same row as [1,0].
    // 4. Remove stone [1,0] because it shares the same column as [0,0].
    // 5. Remove stone [0,1] because it shares the same row as [0,0].
    // Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
    // Example 2:

    // Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    // Output: 3
    // Explanation: One way to make 3 moves is as follows:
    // 1. Remove stone [2,2] because it shares the same row as [2,0].
    // 2. Remove stone [2,0] because it shares the same column as [0,0].
    // 3. Remove stone [0,2] because it shares the same row as [0,0].
    // Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
    // Example 3:

    // Input: stones = [[0,0]]
    // Output: 0
    // Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
    

    // Constraints:

    // 1 <= stones.length <= 1000
    // 0 <= xi, yi <= 104
    // No two stones are at the same coordinate point.


    // Solution by me - 15 ms
    private Map<Integer, Integer> parent = new HashMap<>();

    public int removeStones(int[][] stones) {
        int n = stones.length;

        // Union stones based on their row and column
        for (int[] stone : stones) {
            int x = stone[0];
            int y = ~stone[1]; // use bitwise NOT to differentiate columns from rows
            union(x, y);
        }

        // Count unique roots (connected components)
        int numOfComponents = 0;
        for (int key : parent.keySet()) {
            if (find(key) == key) {
                numOfComponents++;
            }
        }

        // The answer is the total number of stones minus the number of connected components
        return n - numOfComponents;
    }

    // Find with path compression
    private int find(int x) {
        if (parent.putIfAbsent(x, x) == null) {
            return x;
        }
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    // Union by assigning one root to another
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent.put(rootX, rootY);
        }
    }

    // Fastest Solution - 3 ms
    private int connectedComponentCount = 0;

    public int removeStonesFastest(int[][] stonePositions) {
        int[] setRepresentatives = new int[20003];
        for (int[] stonePosition : stonePositions) {
            mergeComponents(stonePosition[0] + 1, stonePosition[1] + 10002, setRepresentatives);
        }
        return stonePositions.length - connectedComponentCount;
    }

    private int findRepresentative(int element, int[] setRepresentatives) {
        if (setRepresentatives[element] == 0) {
            setRepresentatives[element] = element;
            connectedComponentCount++;
        }
        return setRepresentatives[element] == element ? element : 
               (setRepresentatives[element] = findRepresentative(setRepresentatives[element], setRepresentatives));
    }

    private void mergeComponents(int elementA, int elementB, int[] setRepresentatives) {
        int repA = findRepresentative(elementA, setRepresentatives);
        int repB = findRepresentative(elementB, setRepresentatives);
        if (repA != repB) {
            setRepresentatives[repB] = repA;
            connectedComponentCount--;
        }
    }

    // --------------------------------------------------------------


    // 2699. Modify Graph Edge Weights
    // Solved
    // Hard
    // Topics
    // Companies
    // Hint
    // You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

    // Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).

    // Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer target. If there are multiple modifications that make the shortest distance between source and destination equal to target, any of them will be considered correct.

    // Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from source to destination equal to target, or an empty array if it's impossible.

    // Note: You are not allowed to modify the weights of edges with initial positive weights.

    

    // Example 1:



    // Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
    // Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
    // Explanation: The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.
    // Example 2:



    // Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
    // Output: []
    // Explanation: The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal to 6 by modifying the edge with weight -1. So, an empty array is returned.
    // Example 3:



    // Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
    // Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
    // Explanation: The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.
    

    // Constraints:

    // 1 <= n <= 100
    // 1 <= edges.length <= n * (n - 1) / 2
    // edges[i].length == 3
    // 0 <= ai, bi < n
    // wi = -1 or 1 <= wi <= 107
    // ai != bi
    // 0 <= source, destination < n
    // source != destination
    // 1 <= target <= 109
    // The graph is connected, and there are no self-loops or repeated edges


    // Solution - 44ms
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<int[]>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int nodeA = edges[i][0], nodeB = edges[i][1];
            adjacencyList[nodeA].add(new int[]{nodeB, i});
            adjacencyList[nodeB].add(new int[]{nodeA, i}); 
        }

        int[][] distances = new int[n][2];
        Arrays.fill(distances[source], 0);
        for (int i = 0; i < n; i++) {
            if (i != source) {
                distances[i][0] = distances[i][1] = Integer.MAX_VALUE;
            }
        }

        runDijkstra(adjacencyList, edges, distances, source, 0, 0);
        int difference = target - distances[destination][0];
        if (difference < 0) return new int[][]{}; 
        runDijkstra(adjacencyList, edges, distances, source, difference, 1);
        if (distances[destination][1] < target) return new int[][]{}; 

        for (int[] edge : edges) {
            if (edge[2] == -1) edge[2] = 1; 
        }
        return edges;
    }

    private void runDijkstra(List<int[]>[] adjacencyList, int[][] edges, int[][] distances, int source, int difference, int run) {
        int n = adjacencyList.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        priorityQueue.add(new int[]{source, 0});
        distances[source][run] = 0;

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentNode][run]) continue;

            for (int[] neighbor : adjacencyList[currentNode]) {
                int nextNode = neighbor[0], edgeIndex = neighbor[1];
                int weight = edges[edgeIndex][2];

                if (weight == -1) weight = 1; // Initially consider -1 as 1

                if (run == 1 && edges[edgeIndex][2] == -1) {
           
                    int newWeight = difference + distances[nextNode][0] - distances[currentNode][1];
                    if (newWeight > weight) {
                        edges[edgeIndex][2] = weight = newWeight; 
                    }
                }

                if (distances[nextNode][run] > distances[currentNode][run] + weight) {
                    distances[nextNode][run] = distances[currentNode][run] + weight;
                    priorityQueue.add(new int[]{nextNode, distances[nextNode][run]});
                }
            }
        }
    }

    // Fastest Solution - 19 ms
    public int[][] modifiedGraphEdgesFastest(int n, int[][] edges, int source, int destination, int target) {
        List<int[]> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            g[x].add(new int[]{y, i});
            g[y].add(new int[]{x, i}); // 建图，额外记录边的编号
        }

        var dis = new int[n][2];
        for (int i = 0; i < n; i++)
            if (i != source)
                dis[i][0] = dis[i][1] = Integer.MAX_VALUE / 2;

        dijkstra(g, edges, destination, dis, 0, 0);
        int delta = target - dis[destination][0];
        if (delta < 0) // -1 全改为 1 时，最短路比 target 还大
            return new int[][]{};

        dijkstra(g, edges, destination, dis, delta, 1);
        if (dis[destination][1] < target) // 最短路无法再变大，无法达到 target
            return new int[][]{};

        for (int[] e : edges)
            if (e[2] == -1) // 剩余没修改的边全部改成 1
                e[2] = 1;
        return edges;
    }

    // 朴素 Dijkstra 算法
    // 这里 k 表示第一次/第二次
    private void dijkstra(List<int[]> g[], int[][] edges, int destination, int[][] dis, int delta, int k) {
        int n = g.length;
        boolean[] vis = new boolean[n];
        for (; ; ) {
            // 找到当前最短路，去更新它的邻居的最短路
            // 根据数学归纳法，dis[x][k] 一定是最短路长度
            int x = -1;
            for (int i = 0; i < n; ++i)
                if (!vis[i] && (x < 0 || dis[i][k] < dis[x][k]))
                    x = i;
            if (x == destination) // 起点 source 到终点 destination 的最短路已确定
                return;
            vis[x] = true; // 标记，在后续的循环中无需反复更新 x 到其余点的最短路长度
            for (int[] e : g[x]) {
                int y = e[0], eid = e[1];
                int wt = edges[eid][2];
                if (wt == -1)
                    wt = 1; // -1 改成 1
                if (k == 1 && edges[eid][2] == -1) {
                    // 第二次 Dijkstra，改成 w
                    int w = delta + dis[y][0] - dis[x][1];
                    if (w > wt)
                        edges[eid][2] = wt = w; // 直接在 edges 上修改
                }
                // 更新最短路
                dis[y][k] = Math.min(dis[y][k], dis[x][k] + wt);
            }
        }
    }

    // ----------------------------------------------------------

    // 1514. Path with Maximum Probability
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
    // Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

    // If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

    // Example 1:

    // Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
    // Output: 0.25000
    // Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
    // Example 2:

    // Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
    // Output: 0.30000
    // Example 3:

    // Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
    // Output: 0.00000
    // Explanation: There is no path between 0 and 2.
    
    // Constraints:

    // 2 <= n <= 10^4
    // 0 <= start, end < n
    // start != end
    // 0 <= a, b < n
    // a != b
    // 0 <= succProb.length == edges.length <= 2*10^4
    // 0 <= succProb[i] <= 1
    // There is at most one edge between every two nodes.

    // Solution by me - 28 ms

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Step 1: Build the graph
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }
        
        // Step 2: Use Dijkstra's algorithm with a max-heap (priority queue)
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
        maxHeap.offer(new Pair(start, 1.0));
        
        double[] maxProb = new double[n];
        maxProb[start] = 1.0;
        
        while (!maxHeap.isEmpty()) {
            Pair current = maxHeap.poll();
            int node = current.node;
            double prob = current.probability;
            
            if (node == end) {
                return prob;
            }
            
            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node;
                double edgeProb = neighbor.probability;
                double newProb = prob * edgeProb;
                
                if (newProb > maxProb[nextNode]) {
                    maxProb[nextNode] = newProb;
                    maxHeap.offer(new Pair(nextNode, newProb));
                }
            }
        }
        
        return 0.0;
    }
    
    // Helper class to store the node and its associated probability
    static class Pair {
        int node;
        double probability;
        
        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }

    // Fastest Solution - 8 ms

    public double maxProbabilityFastest(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Create an array to store the maximum probability for each node
        double[] maxProb = new double[n];
        Arrays.fill(maxProb, 0.0);  // Initialize all probabilities to 0.0
        maxProb[start_node] = 1.0;  // Probability of starting node is 1.0

        // Iterate 9 times (or you can use any number of iterations as needed)
        for (int i = 0; i < 9; ++i) {
            // Traverse all edges
            for (int j = 0; j < edges.length; ++j) {
                int u = edges[j][0];  // First node in the edge
                int v = edges[j][1];  // Second node in the edge
                double prob = succProb[j];  // Success probability for this edge

                // Update maxProb[v] if a better probability is found via u
                if (maxProb[u] * prob > maxProb[v]) {
                    maxProb[v] = maxProb[u] * prob;
                }

                // Update maxProb[u] if a better probability is found via v
                if (maxProb[v] * prob > maxProb[u]) {
                    maxProb[u] = maxProb[v] * prob;
                }
            }
        }

        // Return the maximum probability to reach the end node
        return maxProb[end_node];
    }
}
