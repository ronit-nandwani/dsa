package advanced.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArraysInterviewProblems {
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * Definition for an interval.
     * public class Interval {
     * int start;
     * int end;
     * Interval() { start = 0; end = 0; }
     * Interval(int s, int e) { start = s; end = e; }
     * }
     */

    // Given an unsorted integer array, A of size N. Find the first missing positive integer.
    //
    //Note: Your algorithm should run in O(n) time and use constant space.
    public static int firstMissingPositive(ArrayList<Integer> A) {
        int n = A.size();
        for(int i=0;i<n;i++) {
            int it = A.get(i);
            if(it <= 0) {
                A.set(i, n+2);
            }
        }
        for(int i=0;i<n;i++) {
            int x = Math.abs(A.get(i));

            if(x >= 1 && x <= n) {
                int index = x-1;
                int elAtIndex = A.get(index);
                if(elAtIndex > 0) {
                    A.set(index, elAtIndex * -1);
                }
            }
        }

        for(int i=0;i<n;i++) {
            if(A.get(i) > 0) {
                return i+1;
            }
        }
        return n+1;
    }

//    public static ArrayList<Interval> mergeNonOverlappingIntervals(ArrayList<Interval> intervals, Interval newInterval) {
//        ArrayList<Interval> inter = new ArrayList<Interval>();
//
//        for(int i=0;i< intervals.size();i++) {
//            Interval ip = intervals.get(i);
//
//        }
//        return inter;
//    }

    public static Interval[] mergeOverlappingIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start < o2.start) {
                    return -1;
                } else if (o1.start > o2.start) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Interval it = intervals[0];
        int s = it.start, e = it.end;
        ArrayList<Interval> inter = new ArrayList<Interval>();
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start <= e) {
                e = Math.max(e, intervals[i].end);
            } else {
                Interval inh = new Interval(s, e);
                inter.add(inh);
                s = intervals[i].start;
                e = intervals[i].end;
            }
        }
        Interval inh = new Interval(s, e);
        inter.add(inh);

        Interval[] array = new Interval[inter.size()];
        for (int i = 0; i < inter.size(); i++) {
            array[i] = inter.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        // A : [ (4, 100), (48, 94), (16, 21), (58, 71), (36, 53), (49, 68), (18, 42), (37, 90), (68, 75), (6, 57), (25, 78), (58, 79), (18, 29), (69, 94), (5, 31), (10, 87), (21, 35), (1, 32), (7, 24), (17, 85), (30, 95), (5, 63), (1, 17), (67, 100), (53, 55), (30, 63), (7, 76), (33, 51), (62, 68), (78, 83), (12, 24), (31, 73), (64, 74), (33, 40), (51, 63), (17, 31), (14, 29), (9, 15), (39, 70), (13, 67), (27, 100), (10, 71), (18, 47), (48, 79), (70, 73), (44, 59), (68, 78), (24, 67), (32, 70), (29, 94), (45, 90), (10, 76), (12, 28), (31, 78), (9, 44), (29, 83), (21, 35), (46, 93), (66, 83), (21, 72), (29, 37), (6, 11), (56, 87), (10, 26), (11, 12), (15, 88), (3, 13), (56, 70), (40, 73), (25, 62), (63, 73), (47, 74), (8, 36) ]
//        Interval[] intervals = new Interval[]{new Interval(1, 3), new Interval(4, 6), new Interval(7, 9)};
//        Interval[] intervals = new Interval[]{new Interval(4, 100), new Interval(48, 94), new Interval(16, 21), new Interval(58, 71), new Interval(36, 53), new Interval(49, 68), new Interval(18, 42), new Interval(37, 90), new Interval(68, 75), new Interval(6, 57)};

        // A : [ (1, 10), (2, 9), (3, 8), (4, 7), (5, 6), (6, 6) ]

//        Interval[] intervals = new Interval[]{new Interval(1, 10), new Interval(2, 9), new Interval(3, 8), new Interval(4, 7), new Interval(5, 6), new Interval(6, 6)};
//        Interval[] iq = mergeOverlappingIntervals(intervals);
//        for (Interval iqp : iq) {
//            System.out.print(iqp.start);
//            System.out.print(" : ");
//            System.out.println(iqp.end);
//        }

        ArrayList<Integer> ar= new ArrayList<Integer>();
        ar.add(1);
        ar.add(7);
        ar.add(2);
        System.out.println(firstMissingPositive(ar));

    }
}
