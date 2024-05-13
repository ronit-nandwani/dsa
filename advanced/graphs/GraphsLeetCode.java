package advanced.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GraphsLeetCode {
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
