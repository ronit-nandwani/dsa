package advanced.recursion;

public class Recursion {

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
