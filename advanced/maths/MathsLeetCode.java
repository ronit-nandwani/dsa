package advanced.maths;

public class MathsLeetCode {
    // 1518. Water Bottles
    // Solved
    // Easy
    // Topics
    // Companies
    // Hint
    // There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.

    // The operation of drinking a full water bottle turns it into an empty bottle.

    // Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.

    // Example 1:


    // Input: numBottles = 9, numExchange = 3
    // Output: 13
    // Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
    // Number of water bottles you can drink: 9 + 3 + 1 = 13.
    // Example 2:


    // Input: numBottles = 15, numExchange = 4
    // Output: 19
    // Explanation: You can exchange 4 empty bottles to get 1 full water bottle. 
    // Number of water bottles you can drink: 15 + 3 + 1 = 19.
    

    // Constraints:

    // 1 <= numBottles <= 100
    // 2 <= numExchange <= 100


    // Solution by me - 0 ms
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        int emptyBottles = numBottles;
        while(emptyBottles>=numExchange) {
            int v = emptyBottles/numExchange;
            ans += v;
            emptyBottles = v + (emptyBottles%numExchange);
        }
        return ans;
    }



    // -----------------------------------------------------------

    // 2582. Pass the Pillow
    // Solved
    // Easy
    // Topics
    // Companies
    // Hint
    // There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.

    // For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th person and so on.
    // Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.

    // Example 1:

    // Input: n = 4, time = 5
    // Output: 2
    // Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
    // After five seconds, the 2nd person is holding the pillow.
    // Example 2:

    // Input: n = 3, time = 2
    // Output: 3
    // Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
    // After two seconds, the 3rd person is holding the pillow.
    

    // Constraints:

    // 2 <= n <= 1000
    // 1 <= time <= 1000
    

    // Note: This question is the same as 3178: Find the Child Who Has the Ball After K Seconds.

    // Solution by me - 0 ms
    public int passThePillow(int n, int time) {
        boolean odd = false;
        if((time / (n-1)) %2 != 0) odd = true;
        return odd ? (n - time%(n-1)) : (time%(n-1) +1);
    }

    // Othr solution - 0 ms
    public int passThePillowFaster(int n, int time) {
        int round = time / (n - 1);
        int idx = time % (n - 1);
        if (round % 2 == 0){
            return idx + 1;
        }else{
            return n - idx;
        }
    }

}
