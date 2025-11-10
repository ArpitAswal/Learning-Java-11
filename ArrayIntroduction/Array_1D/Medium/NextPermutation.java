/*
Given an array arr[] of size n, the task is to print the lexicographically next greater permutation of the given array. If there does not exist any greater permutation, then find the lexicographically smallest permutation of the given array.

Let us understand the problem better by writing all permutations of [1, 2, 4] in lexicographical order: [1, 2, 4], [1, 4, 2], [2, 1, 4], [2, 4, 1], [4, 1, 2] and [4, 2, 1]. If we give any of the above (except the last) as input, we need to find the next one in sequence. If we give last as input, we need to return the first one.

Examples:

Input: arr = [2, 4, 1, 7, 5, 0]
Output: [2, 4, 5, 0, 1, 7]
Explanation: The next permutation of the given array is 2 4 5 0 1 7

Input: arr = {3, 2, 1]
Output: [1, 2, 3]
Explanation: As arr[] is the last permutation. So, the next permutation is the lowest one.

Input: arr = [1, 3, 5, 4, 2]
Output: [1, 4, 2, 3, 5]
Explanation: The next permutation of the given array is found by rearranging the elements in the next lexicographical order.
*/

/*
The very basic idea that comes to our mind is that we would first generate all possible permutations of a given array and sort them. Once sorted, we locate the current permutation within this list. The next permutation is simply the next arrangement in the sorted order. If the current arrangement is the last in the list then display the first permutation (smallest permutation).

Note: This approach will work only when there are no duplicated in the input array. 
*/

// Java Program to find the next permutation by generating 
// all permutations

import java.util.*;

class NextPermutation {

    // Find next permutation
    static void nextPermutation(int[] arr) {
        List<int[]> res = new ArrayList<>();

        permutations(res, arr, 0);
        Collections.sort(res, Arrays::compare); // Sort the permutations

        // Traverse to find next permutation
        for (int i = 0; i < res.size(); i++) {

            // Found a match
            if (Arrays.equals(res.get(i), arr)) {

                // Store the next in arr
                if (i < res.size() - 1) {
                    int[] nextPerm = res.get(i + 1);
                    for (int j = 0; j < arr.length; j++)
                        arr[j] = nextPerm[j];
                }

                // If the given permutation is the last
                if (i == res.size() - 1) {
                    int[] nextPerm = res.get(0);
                    for (int j = 0; j < arr.length; j++)
                        arr[j] = nextPerm[j];
                }

                break;
            }
        }
    }

    // Generate permutations recursively
    static void permutations(List<int[]> res, int[] arr, int idx) {

        // Base case: if idx reaches the end of the array
        if (idx == arr.length - 1) {
            res.add(arr.clone());
            return;
        }

        // Permutations made by swapping each element
        // starting from index `idx`
        for (int i = idx; i < arr.length; i++) {

            // Swapping
            swap(arr, idx, i);

            // Recursive call to create permutations
            // for the next element
            permutations(res, arr, idx + 1);

            // Backtracking
            swap(arr, idx, i);
        }
    }

    // Swap function
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void nextPermutation2(int[] arr) {
        int n = arr.length;

        // Find the pivot index
        int pivot = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                pivot = i;
                break;
            }
        }

        // If pivot point does not exist, reverse the whole array
        if (pivot == -1) {
            reverse(arr, 0, n - 1);
            return;
        }

        // Find the element from the right that is greater than pivot
        for (int i = n - 1; i > pivot; i--) {
            if (arr[i] > arr[pivot]) {
                swap(arr, i, pivot);
                break;
            }
        }

        // Reverse the elements from pivot + 1 to the end
        reverse(arr, pivot + 1, n - 1);
    }

    // Helper method to reverse array
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 1 };
        nextPermutation(arr);

        for (int num : arr)
            System.out.print(num + " ");

        /*
         * Time Complexity: O(n!*n*log(n!)), n represents the number of elements present
         * in the input sequence represent all possible permutation.
         * Auxiliary Space: O(n!), for storing the permutations.
         */

        System.out.println();

        int[] arr2 = { 2, 4, 1, 7, 5, 0 };

        nextPermutation2(arr2);
        for (int num : arr2)
            System.out.print(num + " ");

        /*
         * Time Complexity: O(n), where n is the size of the given array.
         * Auxiliary Space: O(1), The algorithm performs in-place operations (modifying
         * the array directly) without using extra space proportional to the input size.
         */
    }
}

/*
 * Observations of Next permutation:
 * 
 * . To get the next permutation we change the number in a position which is as
 * right as possible.
 * . The first number to be moved is the rightmost number smaller than its next.
 * . The number to come in-place is the rightmost greater number on right side
 * of the pivot.
 * 
 * Example: arr = [2, 4, 1, 7, 5, 0]
 * Step 1: Find the rightmost number smaller than its next, that number is the
 * pivot number. In this case, the pivot is 1. (i.e, arr[i] < arr[i + 1]).
 * Step 2: If pivot index does not exist, then the given sequence in the array
 * is the largest as possible. So, reverse the complete array. For example, for
 * [3, 2, 1], the output would be [1, 2, 3].
 * Step 2: Otherwise, Find the rightmost number greater than the pivot number.
 * In this case, the number is 5.
 * Step 3: Swap the pivot number with the rightmost greater number. The array
 * becomes [2, 4, 5, 7, 1, 0]
 * Step 4: Reverse the numbers to the right of the pivot number (piv+1 till n).
 * The array becomes [2, 4, 5, 0, 1, 7]
 * Step 5: Return the array. The resulting array is the next permutation.
 */