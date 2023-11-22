package advanced.recursion;

public class Recursion2 {
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
