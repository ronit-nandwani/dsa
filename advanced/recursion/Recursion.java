package advanced.recursion;

public class Recursion {
    // You are given an integer A, print 1 to A using using recursion.
    // Note :- After printing all the numbers from 1 to A, print a new line.
    public void print1toA(int A) {
        if(A == 0) {
            return;
        } else {
            // Print 1-A
            System.out.print(A + " ");
            print1toA(A-1);
        }
    }
    // You are given an integer A, print A to 1 using using recursion.
    // Note :- After printing all the numbers from A to 1, print a new line.
    public void printAto1(int A) {
        if(A == 0) {
            return;
        } else {
            // Print A-1
            System.out.print(A + " ");
            printAto1(A-1);
        }
    }
    public void solve(int A) {
        // printAto1(A);
        // print1toA(A);
        System.out.println();
    }

    // Write a program to find the factorial of the given number A using recursion.
    //Note: The factorial of a number N is defined as the product of the numbers from 1 to N.
    public int factorial(int A) {
        if(A == 1) {
            return 1;
        } else {
            return factorial(A-1) * A;
        }
    }

    // The Fibonacci numbers are the numbers in the following integer sequence.
    //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……..
    //In mathematical terms, the sequence Fn of Fibonacci numbers is defined by the recurrence relation:
    //Fn = Fn-1 + Fn-2
    //Given a number A, find and return the Ath Fibonacci Number using recursion.
    //Given that F0 = 0 and F1 = 1.
    public int findAthFibonacci(int A) {
        if(A == 1 || A == 0) {
            return A;
        } else {
            return findAthFibonacci(A-1) + findAthFibonacci(A-2);
        }
    }
}
