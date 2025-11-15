
/*
Maximum Circular Subarray Sum

Given a circular array arr[], find the maximum sum of any non-empty subarray. A circular array allows wrapping from the end back to the beginning.
Note: A subarray may wrap around the end and continue from the beginning, forming a circular segment.

Examples: 

Input: arr[] = [8, -8, 9, -9, 10, -11, 12]
Output: 22
Explanation: The circular subarray [12, 8, -8, 9, -9, 10] gives the maximum sum, which is 22.

Input: arr[] = [4, -1, -2, 3]
Output: 7
Explanation: The circular subarray [3, 4] gives the maximum sum of 7.

Input: arr[] = [5, -2, 3, 4]
Output: 12
Explanation: The circular subarray [3, 4, 5] gives the maximum sum of 12.
 */

/*
[Naive Approach] Considering All Possible Subarrays – O(n^2) Time and O(1) Space
The idea is to consider every element as the beginning of the subarray, and calculate the maximum possible sum, which includes both circular and linear subarrays starting from that element.

[Expected Approach] Using Kadane's Algorithm – O(n) Time and O(1) Space
This approach is similar to the previous one, but the key difference is that we're using Kadane's algorithm to find the circular subarray sum as well. 
The maximum sum of a circular subarray can be defined as the total sum of the array minus the sum of a subarray in the middle. 
So, to maximize the circular subarray sum, we need to minimize the subarray sum. 


. Maximum Circular Subarray Sum = Total Sum - Minimum Subarray Sum.
. If the minimum subarray sum equals the total sum of the array, we return the normal maximum subarray sum, because if all elements are negative, the circular sum would be zero, but the answer will be negative only.
*/

class MaximumCircularSubarraySum {

    static int maxCircularSum1(int[] arr) {

        int n = arr.length;
        int res = arr[0];

        // Subarray that starts with index i
        for (int i = 0; i < n; i++) {
            int currSum = 0;

            // Considering all possible endpoints of the
            // Subarray that begins with index i
            for (int j = 0; j < n; j++) {

                // Circular index
                int idx = (i + j) % n;
                currSum = currSum + arr[idx];
                res = Math.max(res, currSum);
            }
        }

        return res;
    }

    static int maxCircularSum2(int[] arr) {

        int totalSum = 0;
        int currMaxSum = 0, currMinSum = 0;
        int maxSum = arr[0], minSum = arr[0];

        for (int i = 0; i < arr.length; i++) {

            // Kadane's to find maximum sum subarray
            currMaxSum = Math.max(currMaxSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            // Kadane's to find minimum sum subarray
            currMinSum = Math.min(currMinSum + arr[i], arr[i]);
            minSum = Math.min(minSum, currMinSum);

            // Sum of all the elements of input array
            totalSum += arr[i];
        }

        int normalSum = maxSum;
        int circularSum = totalSum - minSum;

        // If the minimum subarray is equal to total Sum
        // then we just need to return normalSum
        if (minSum == totalSum)
            return normalSum;

        return Math.max(normalSum, circularSum);
    }

    public static void main(String[] args) {
        int[] arr = { 8, -8, 9, -9, 10, -11, 12 };
        System.out.println(maxCircularSum1(arr));
        System.out.println(maxCircularSum2(arr));
    }
}