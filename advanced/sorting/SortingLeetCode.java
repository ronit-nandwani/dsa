package advanced.sorting;

import java.util.Arrays;

public class SortingLeetCode {
    // 179. Largest Number
    // Solved
    // Medium
    // Topics
    // Companies
    // Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

    // Since the result may be very large, so you need to return a string instead of an integer.

    // Example 1:

    // Input: nums = [10,2]
    // Output: "210"
    // Example 2:

    // Input: nums = [3,30,34,5,9]
    // Output: "9534330"
    

    // Constraints:

    // 1 <= nums.length <= 100
    // 0 <= nums[i] <= 109


    // Solution by me - 5 ms

    public String largestNumber(int[] nums) {
        // Convert the numbers to strings
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // Sort the strings with a custom comparator
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));

        // If the largest number after sorting is "0", the entire number is "0"
        if (strNums[0].equals("0")) {
            return "0";
        }

        // Build the result by concatenating sorted strings
        StringBuilder largestNumber = new StringBuilder();
        for (String num : strNums) {
            largestNumber.append(num);
        }

        return largestNumber.toString();
    }


    // Fastest Solution - 1 ms

    public void merge(int[]arr,int low,int mid,int high)
    {
        int i=low;int j=mid+1;
        int[]temp=new int[high-low+1];
        int k=0;
        while(i<=mid && j<=high)
        {
            long n=10,m=10;
            while(arr[i]>=n)
            {
                n*=10;
            }
            while(arr[j]>=m)
            {
                m*=10;
            }
            long l=arr[i]*m+arr[j];
            long r=arr[j]*n+arr[i];
            if(l>r)
            {
              temp[k++]=arr[i++];
            }
            else
            {
                temp[k++]=arr[j++];
            }
        }
        while(i<=mid)
        {
            temp[k++]=arr[i++];

        }
        while(j<=high)
        {

            temp[k++]=arr[j++];
        }
        k=0;
        int m=low;
        while(m<=high)
        {
            arr[m++]=temp[k++];
        }
    }
    public void mergesort(int[]arr,int low,int high)
    {
        if(low==high)
        {
            return ;
        }
        int mid=low+(high-low)/2;
        mergesort(arr,low,mid);
        mergesort(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    public String largestNumberFastest(int[] nums) {
       mergesort(nums,0,nums.length-1);
       StringBuilder str=new StringBuilder();
       for(int i=0;i<nums.length;i++)
       {
         str.append(String.valueOf(nums[i]));
       }
       return (str.charAt(0)=='0')?"0":str.toString();
    }
}
