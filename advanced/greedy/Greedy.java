package advanced.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Pair {
    int time,profit;
    Pair(int x,int y) {
        time=x;
        profit=y;
    }
}
class JobPair {
    int startTime,endTime;
    JobPair(int x,int y) {
        startTime=x;
        endTime=y;
    }
}
public class Greedy {
    // Flipkart's Challenge in Effective Inventory Management

    // In the recent expansion into grocery delivery, Flipkart faces a crucial challenge in effective inventory management. Each grocery item on the platform carries its own expiration date and profit margin, represented by two arrays, A and B of size N. A[i] denotes the time left before expiration date for the ith item, and B[i] denotes profit margin for the ith item. To mitigate potential losses due to expiring items, Flipkart is seeking a strategic solution.

    // The objective is to identify a method to strategically buy certain items, ensuring they are sold before their expiration date, thereby maximizing overall profit. Can you assist Flipkart in developing an innovative approach to optimize their grocery inventory and enhance profitability?

    // Your task is to find the maximum profit one can earn by buying groceries considering that you can only buy one grocery item at a time.

    // NOTE:

    // You can assume that it takes 1 minute to buy a grocery item, so you can only buy the ith grocery item when the current time <= A[i] - 1.
    // You can start buying from day = 0.
    // Return your answer modulo 109 + 7.


    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] <= 109
    // 0 <= B[i] <= 109

    // Input Format
    // The first argument is an integer array A represents the deadline for buying the grocery items.
    // The second argument is an integer array B represents the profit obtained after buying the grocery items.

    // Output Format
    // Return an integer denoting the maximum profit you can earn.

    // Example Input
    // Input 1:

    // A = [1, 3, 2, 3, 3]
    // B = [5, 6, 1, 3, 9]
    // Input 2:

    // A = [3, 8, 7, 5]
    // B = [3, 1, 7, 19]

    // Example Output
    // Output 1:

    // 20
    // Output 2:

    // 30

    // Example Explanation
    // Explanation 1:

    // At time 0, buy item with profit 5.
    // At time 1, buy item with profit 6.
    // At time 2, buy item with profit 9.
    // At time = 3 or after , you can't buy any item, as there is no item with deadline >= 4.
    // So, total profit that one can earn is 20.
    // Explanation 2:

    // At time 0, buy item with profit 3.
    // At time 1, buy item with profit 1.
    // At time 2, buy item with profit 7.
    // At time 3, buy item with profit 19.
    // We are able to buy all items within their deadline. So, total profit that one can earn is 30.

    public class Solution {
    class Pair {
        int expiryTime;
        int profit;
        Pair(int e, int p) {
            expiryTime = e;
            profit = p;
        }
    }
    public int solve(int[] A, int[] B) {
        int n = A.length;
        Pair[] pairs = new Pair[n];

        for(int i=0;i<n;i++) {
            pairs[i] = new Pair(A[i],B[i]);
        }

        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.expiryTime, o2.expiryTime);
            }
        });


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int t = 0, ans = 0;
        for(int i=0;i<n;i++) {
            if(t < pairs[i].expiryTime) {
                ans = ans%1000000007 + pairs[i].profit;
                pq.add(pairs[i].profit);
                t++;
            } else if(pairs[i].profit > pq.peek()) {
                ans -= pq.remove();
                ans = ans%1000000007 + pairs[i].profit;
                pq.add(pairs[i].profit);
            }
        }
        return ans%1000000007;
    }
}
    // Solution by teamn
    public class Solution1 {
        public int solve(int[] A, int[] B) {
            int mod = (int)1e9 + 7;
            int n = A.length;
            PriorityQueue < Integer > pending = new PriorityQueue<Integer>(Collections.reverseOrder());
        
            ArrayList<pair> v = new ArrayList<pair>();
        
            for (int i = 0; i < n; i++){
                v.add(new pair(A[i], B[i]));
            }
        
            Collections.sort(v);
        
            int ans = 0;
            int tim = v.get(n-1).first - 1;
            for (int i = n - 1; i >= 0; i--) {
                while (tim >= v.get(i).first && pending.size() > 0) {
                    tim--;
                    ans = (ans + pending.poll()) % mod;
                }
                if (pending.size() == 0)
                    tim = v.get(i).first - 1;
                pending.add(v.get(i).second);
            }
            while (tim >= 0 && pending.size() > 0) {
                tim--;
                ans = (ans + pending.poll()) % mod;
            }
            return ans;
        }
        class pair implements Comparable <pair> {
            int first;
            int second;
            public pair(int a, int b){
                this.first = a;
                this.second = b;
            }
            //@Override
            public int compareTo(pair b){
                if(this.first == b.first)
                    return -(b.second - this.second);
                else
                    return -(b.first - this.first);
            }
        }
    }



    // Another coin problem
    // The monetary system in DarkLand is really simple and systematic. The locals-only use coins. The coins come in different values. The values used are:

    // 1, 5, 25, 125, 625, 3125, 15625, ...
    // Formally, for each K >= 0 there are coins worth 5K.

    // Given an integer A denoting the cost of an item, find and return the smallest number of coins necessary to pay exactly the cost of the item (assuming you have a sufficient supply of coins of each of the types you will need).

    // Problem Constraints
    // 1 <= A <= 2×109

    // Input Format
    // The only argument given is integer A.

    // Output Format
    // Return the smallest number of coins necessary to pay exactly the cost of the item.

    // Example Input
    // Input 1:

    // A = 47
    // Input 2:

    // A = 9

    // Example Output
    // Output 1:

    // 7
    // Output 2:

    // 5

    // Example Explanation
    // Explanation 1:

    // Representation of 7 coins will be : (1 + 1 + 5 + 5 + 5 + 5 + 25).
    // Explanation 2:

    // Representation of 5 coins will be : (1 + 1 + 1 + 1 + 5).

    public int anotherCoinProblem(int A) {
        // Generate the coin values which are powers of 5
        ArrayList<Integer> coinValues = new ArrayList<>();
        int value = 1;
        while (value <= A) {
            coinValues.add(value);
            if (value > Integer.MAX_VALUE / 5) {
                break; // Prevent integer overflow
            }
            value *= 5;
        }

        // Reverse the list to start with the largest coin
        Collections.reverse(coinValues);

        int count = 0;
        for (int coin : coinValues) {
            if (A == 0) {
                break;
            }
            count += A / coin; // Use as many coins of this value as possible
            A %= coin;         // Reduce A by the value of coins used
        }
        return count;
    }
    // Solution by team
    public class Solution {
        public int solve(int A) {
            ArrayList < Integer > v = new ArrayList < Integer > ();
    
            int val = 1;
    
            // Storing all denominations of coins
            while (val <= 2000000000) {
                v.add(val);
                val = val * 5;
            }
            // Sort in decreasing order
            Collections.reverse(v);
    
            int ans = 0;
            // Loop from the largest denomination
            for (int i = 0; i < v.size(); i++) {
                ans += A / v.get(i);
                A = A % v.get(i);
            }
            return ans;
        }
    }


    // Finish Maximum Jobs (Scheduling)
    // There are N jobs to be done, but you can do only one job at a time.
    // Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
    // Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
    // Return the maximum number of jobs you can finish.
    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] < B[i] <= 109
    // Input Format
    // The first argument is an integer array A of size N, denoting the start time of the jobs.
    // The second argument is an integer array B of size N, denoting the finish time of the jobs.
    // Output Format
    // Return an integer denoting the maximum number of jobs you can finish.
    // Example Input
    // Input 1:
    // A = [1, 5, 7, 1]
    // B = [7, 8, 8, 8]
    // Input 2:
    // A = [3, 2, 6]
    // B = [9, 8, 9]
    // Example Output
    // Output 1:
    // 2
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // We can finish the job in the period of time: (1, 7) and (7, 8).
    // Explanation 2:
    // Since all three jobs collide with each other. We can do only 1 job.
    
    public int finishMaximumJobs(int[] A, int[] B) {
        ArrayList<JobPair> jobs = new ArrayList<>();
        int n = A.length;
        for(int i = 0;i < n; i++) {
            JobPair p = new JobPair(A[i],B[i]);
            jobs.add(p);
        }
        Collections.sort(jobs,new Comparator<JobPair>(){
            public int compare(JobPair a, JobPair b){
             return a.endTime-b.endTime;
         }
        });
        PriorityQueue <Integer> pq = new PriorityQueue<>();
        int ans = 1;
        int last = jobs.get(0).endTime;
        for(int i=1;i<n;i++) {
            JobPair p = jobs.get(i);
            if(p.startTime>=last) {
                ans++;
                last = p.endTime;
            }
        }
        return ans;
    }
    // Free Cars
    // Given two arrays, A and B of size N. A[i] represents the time by which you can buy the ith car without paying any money.
    //B[i] represents the profit you can earn by buying the ith car. It takes 1 minute to buy a car, so you can only buy the ith car when the current time <= A[i] - 1.
    //Your task is to find the maximum profit one can earn by buying cars considering that you can only buy one car at a time.
    //NOTE:
    //You can start buying from time = 0.
    //Return your answer modulo 109 + 7.
    //Problem Constraints
    //1 <= N <= 105
    //1 <= A[i] <= 109
    //0 <= B[i] <= 109
    //Input Format
    //The first argument is an integer array A represents the deadline for buying the cars.
    //The second argument is an integer array B represents the profit obtained after buying the cars.
    //Output Format
    //Return an integer denoting the maximum profit you can earn.
    //Example Input
    //Input 1:
    // A = [1, 3, 2, 3, 3]
    // B = [5, 6, 1, 3, 9]
    //Input 2:
    // A = [3, 8, 7, 5]
    // B = [3, 1, 7, 19]
    //Example Output
    //Output 1:
    // 20
    //Output 2:
    // 30
    //Example Explanation
    //Explanation 1:
    // At time 0, buy car with profit 5.
    // At time 1, buy car with profit 6.
    // At time 2, buy car with profit 9.
    // At time = 3 or after , you can't buy any car, as there is no car with deadline >= 4.
    // So, total profit that one can earn is 20.
    //Explanation 2:
    // At time 0, buy car with profit 3.
    // At time 1, buy car with profit 1.
    // At time 2, buy car with profit 7.
    // At time 3, buy car with profit 19.
    // We are able to buy all cars within their deadline. So, total profit that one can earn is 30.
    public int freeCars(int[] A, int[] B) {
        ArrayList<Pair> cars = new ArrayList<>();
        int n = A.length;
        for(int i = 0;i < n; i++) {
            Pair p = new Pair(A[i],B[i]);
            cars.add(p);
        }
        Collections.sort(cars,new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.time-b.time;
            }
        });
        PriorityQueue <Integer> pq = new PriorityQueue<>();
        int t=0, ans=0;
        for(int i=0;i<n;i++) {
            Pair p = cars.get(i);
            if(t<p.time) {
                pq.add(p.profit);
                t++;
            } else {
                if(!pq.isEmpty() && p.profit > pq.peek()) {
                    pq.remove();
                    pq.add(p.profit);
                }
            }
        }
        while(!pq.isEmpty()) {
            ans = (ans + pq.poll())%1000000007;
        }
        return ans;
    }

    // Distribute Candy
    // N children are standing in a line. Each child is assigned a rating value.
    //You are giving candies to these children subjected to the following requirements:
    //Each child must have at least one candy.
    //Children with a higher rating get more candies than their neighbors.
    //What is the minimum number of candies you must give?
    //Problem Constraints
    //1 <= N <= 105
    //-109 <= A[i] <= 109
    //Input Format
    //The first and only argument is an integer array A representing the rating of children.
    //Output Format
    //Return an integer representing the minimum candies to be given.
    //Example Input
    //Input 1:
    // A = [1, 2]
    //Input 2:
    // A = [1, 5, 2, 1]
    //Example Output
    //Output 1:
    // 3
    //Output 2:
    // 7
    //Example Explanation
    //Explanation 1:
    // The candidate with 1 rating gets 1 candy and candidate with rating 2 cannot get 1 candy as 1 is its neighbor.
    // So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.
    //Explanation 2:
    // Candies given = [1, 3, 2, 1]
    public int candy(int[] A) {
        int n = A.length;
        int[] c = new int[n];
        for(int i=0;i<n;i++) {
            c[i] = 1;
        }

        for(int i=1;i<n;i++) {
            if(A[i] > A[i-1]) {
                c[i] = c[i-1] + 1;
            }
        }

        int ans = c[n-1];
        for(int i=n-2;i>=0;i--) {
            if(A[i] > A[i+1] && c[i] <= c[i+1]) {
                c[i] = c[i+1] + 1;
            }
            ans += c[i];
        }
        return ans;
    }
}
