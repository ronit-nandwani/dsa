package advanced.bit_manipulation;

public class BitManipulation1 {
    // Given an integer A. Unset B bits from the right of A in binary.
    // For example, if A = 93 and B = 4, the binary representation of A is 1011101.
    // If we unset the rightmost 4 bits, we get the binary number 1010000, which is equal to the decimal value 80.
    public long unsetXBitsFromRight(long A, int B) {
        for(long i=0;i<B;i++) {
            if((A & (1<<i)) > 0) {
                A = A ^ (1<<i);
            }
        }
        return A;
    }

    // Alex and Sam are good friends. Alex is doing a lot of programming these days. He has set a target score of A for himself.
    //Initially, Alex's score was zero. Alex can double his score by doing a question, or Alex can seek help from Sam for doing questions that will contribute 1 to Alex's score. Alex wants his score to be precisely A. Also, he does not want to take much help from Sam.
    //Find and return the minimum number of times Alex needs to take help from Sam to achieve a score of A.
    // Example for A = 5
    // Initial score : 0
    //Takes help from Sam, score : 1
    //Alex solves a question, score : 2
    //Alex solves a question, score : 4
    //Takes help from Sam, score: 5
    public int helpFromSam(int A) {

        // Optimized Approach with TC O(Log N)
        // int count = 0;
        // while(A > 0) {
        //     if((A & 1) == 1) {
        //         count++;
        //     }
        //     A = A >> 1;
        // }
        // return count;

        // Normal Approach with TC O(1)
        int count = 0;
        for(int i=0;i<31;i++) {
            if((A & (1 << i)) > 0) {
                count++;
            }
        }
        return count;
    }

    // Write a function that takes an integer and returns the number of 1 bits present in its binary representation.
    public int countSetBits(int A) {

        // Optimized Approach with TC O(Log N)
        // int count = 0;
        // while(A > 0) {
        //     if((A & 1) == 1) {
        //         count++;
        //     }
        //     A = A >> 1;
        // }
        // return count;

        // Normal Approach with TC O(1)
        int count = 0;
        for(int i=0;i<31;i++) {
            if((A & (1 << i)) > 0) {
                count++;
            }
        }
        return count;
    }

    // You are given two integers A and B.
    //Set the A-th bit and B-th bit in 0, and return output in decimal Number System.
    //Note:
    //The bit positions are 0-indexed, which means that the least significant bit (LSB) has index 0.
    public int setAthAndBthBit(int A, int B) {
        if (A!=B) {
            return ((1<<A)^(1<<B));
        } else {
            return (1<<A);
        }
    }

    // You are given two integers A and B.
    //If B-th bit in A is set, make it unset.
    //If B-th bit in A is unset, leave as it is.
    //Return the updated A value.
    //Note:
    //The bit position is 0-indexed, which means that the least significant bit (LSB) has index 0.
    public int unsetIthBit(int A, int B) {
        if((A & (1 << B)) == 0) {
            return A;
        } else {
            return (A ^ (1 << B));
        }
    }

    // You are given two integers A and B.
    //Return 1 if B-th bit in A is set
    //Return 0 if B-th bit in A is unset
    public int checkIfBitIsSet(int A, int B) {
        if((A & (1 << B)) > 0) {
            return 1;
        }
        return 0;
    }
}
