/*
 Given an array of positive integers arr[] of size n, the task is to find second largest distinct element in the array.

Note: If the second largest element does not exist, return -1.

Examples:

Input: arr[] = [12, 35, 1, 10, 34, 1]
Output: 34
Explanation: The largest element of the array is 35 and the second largest element is 34.

Input: arr[] = [10, 10, 10]
Output: -1
Explanation: The largest element of the array is 10 there is no second largest element.
 */

/*
Solution1: Using Sorting
The idea is to sort the array in non-decreasing order. Now, we know that the largest element will be at index n - 1. So, starting from index (n - 2), traverse the remaining array in reverse order.

Solution2: [Better Approach] Two Pass Search
The approach is to traverse the array twice. In the first traversal, find the maximum element. In the second traversal, find the maximum element ignoring the one we found in the first traversal.

Solution3: [Expected Approach] One Pass Search
The idea is to keep track of the largest and second largest element while traversing the array. Initialize largest and secondLargest with -1. Now, for any index i,

If arr[i] > largest, update secondLargest with largest and largest with arr[i].
Else If arr[i] < largest and arr[i] > secondLargest, update secondLargest with arr[i].
*/

import java.util.Arrays;

class SecondLargest {

    // function to find the second largest element
    static int getSecondLargest(int[] arr) {
        int n = arr.length;

        // Sort the array in non-decreasing order
        Arrays.sort(arr);

        // start from second last element as last element is the largest
        for (int i = n - 2; i >= 0; i--) {

            // return the first element which is not equal to the
            // largest element
            if (arr[i] != arr[n - 1]) {
                return arr[i];
            }
        }

        // If no second largest element was found, return -1
        return -1;
    }

    static int getSecondLargest2(int[] arr) {
        int n = arr.length;

        int largest = -1, secondLargest = -1;

        // Finding the largest element
        for (int i = 0; i < n; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }

        // Finding the second largest element
        for (int i = 0; i < n; i++) {

            // Update second largest if the current element is greater
            // than second largest and not equal to the largest
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    static int getSecondLargest3(int[] arr) {
        int n = arr.length;

        int largest = -1, secondLargest = -1;

        // finding the second largest element
        for (int i = 0; i < n; i++) {

            // If arr[i] > largest, update second largest with
            // largest and largest with arr[i]
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            }

            // If arr[i] < largest and arr[i] > second largest,
            // update second largest with arr[i]
            else if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static void main(String[] args) {
        int[] arr = { 12, 35, 1, 10, 34, 1 };
        System.out.println(getSecondLargest(arr));
        /*
         * Time Complexity: O(n*log(n)), as sorting the array takes O(n*log(n)) time and
         * traversing the array can take O(n) time in the worst case, so total time
         * complexity = (n*log(n) + n) = O(n*log(n)).
         * Auxiliary space: O(1), as no extra space is required.
         */
        int[] arr2 = { 12, 35, 1, 10, 34, 1 };
        System.out.println(getSecondLargest2(arr2));

        /*
         * Time Complexity: O(n) + O(n) = O(n), as we are traversing the array two
         * times.
         * Auxiliary space: O(1), as no extra space is required.
         */
        int[] arr3 = { 12, 35, 1, 10, 34, 1 };
        System.out.println(getSecondLargest3(arr3));

        /*
         * Time Complexity: O(n), as we are traversing the array only once.
         * Auxiliary space: O(1)
         */
    }
}
