package advanced.recursion;

public class Recursion2 {
    // On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
    // Given row number A and index B, return the Bth indexed symbol in row A. (The values of B are 0-indexed.).
    public int findKthSymbol(int A, long B) {
        //base case
        if(A==1){
            return 0;
        }
        //find the val of previus row ,B/2 value (in A row B is based on A-1 row B/2 index)
        int val = findKthSymbol(A-1,B/2);

        if(B%2==0){
            return val;
        }
        return 1-val;
    }

    
    // Tower of Hanoi In the classic problem of the Towers of Hanoi, you have 3 towers numbered from 1 to 3 (left to right) and A disks numbered from 1 to A (top to bottom) of different sizes which can slide onto any tower.
    //The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on top of an even larger one).
    //You have the following constraints:
    //
    //Only one disk can be moved at a time.
    //A disk is slid off the top of one tower onto another tower.
    //A disk cannot be placed on top of a smaller disk.
    //You have to find the solution to the Tower of Hanoi problem.
    //You have to return a 2D array of dimensions M x 3, where M is the minimum number of moves needed to solve the problem.
    //In each row, there should be 3 integers (disk, start, end), where:
    //
    //disk - number of the disk being moved
    //start - number of the tower from which the disk is being moved
    //end - number of the tower to which the disk is being moved
    // Example Input
    //Input 1:
    //A = 2
    //Input 2:
    //
    //A = 3
    //
    //
    //Example Output
    //Output 1:
    //[1 1 2 ] [2 1 3 ] [1 2 3 ]
    //Output 2:
    //
    //[1 1 3 ] [2 1 2 ] [1 3 2 ] [3 1 3 ] [1 2 1 ] [2 2 3 ] [1 1 3 ]
    int ind = 0;
    public void moveDisks(int n, int ori,int buf,int des, int[][] arr) {
        // Base case
        if (n <= 0) return;
        // move top n - 1 disks from origin to buffer, using destination as a buffer.

        // m++;
        moveDisks(n - 1, ori, des, buf, arr);
        // move top from origin to destination
        arr[ind][0] = n;
        arr[ind][1] = ori;
        arr[ind][2] = des;
        ind++;
        // move top n - 1 disks from buffer to destination, using origin as a buffer.
        moveDisks(n - 1, buf, ori, des, arr);
    }

    public int[][] towerOfHanoi(int A) {
        int m = (int)Math.pow(2,A);
        int[][] arr = new int[m-1][3];
        moveDisks(A,1,2,3,arr);
        return arr;
    }
}
