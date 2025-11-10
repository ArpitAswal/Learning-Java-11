/*
 Given an array arr[], the task is to reverse the array. Reversing an array means rearranging the elements such that the first element becomes the last, the second element becomes second last and so on.

Examples:

Input: arr[] = {1, 4, 3, 2, 6, 5}
Output: {5, 6, 2, 3, 4, 1}
Explanation: The first element 1 moves to last position, the second element 4 moves to second-last and so on.

Input: arr[] = {4, 5, 1, 2}
Output: {2, 1, 5, 4}
Explanation: The first element 4 moves to last position, the second element 5 moves to second last and so on.
 */

/*
Solution1: [Naive Approach] Using a temporary array - O(n) Time and O(n) Space
The idea is to use a temporary array to store the reverse of the array.

Create a temporary array of same size as the original array.
Now, copy all elements from original array to the temporary array in reverse order.
Finally, copy all the elements from temporary array back to the original array.

Solution2:  Using Two Pointers - O(n) Time and O(1) Space
The idea is to maintain two pointers: left and right, such that left points at the beginning of the array and right points to the end of the array.
While left pointer is less than the right pointer, swap the elements at these two positions. After each swap, increment the left pointer and decrement the right pointer to move towards the center of array. This will swap all the elements in the first half with their corresponding element in the second half.

Solution3: Using Recursion - O(n) Time and O(n) Space
The idea is to use recursion and define a recursive function that takes a range of array elements as input and reverses it. Inside the recursive function,

Swap the first and last element.
Recursively call the function with the remaining subarray.
*/

// Java Program to reverse an array using temporary array

class ArrayReverse {

    // function to reverse an array
    static void reverseArray(int[] arr) {
        int n = arr.length;

        // Temporary array to store elements in reversed order
        int[] temp = new int[n];

        // Copy elements from original array to temp in reverse order
        for (int i = 0; i < n; i++)
            temp[i] = arr[n - i - 1];

        // Copy elements back to original array
        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }

    static void reverseArray2(int[] arr) {

        // Initialize left to the beginning and right to the end
        int left = 0, right = arr.length - 1;

        // Iterate till left is less than right
        while (left < right) {

            // Swap the elements at left and right position
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Increment the left pointer
            left++;

            // Decrement the right pointer
            right--;
        }
    }

    static void reverseArray3(int[] arr) {
        int n = arr.length;

        // Iterate over the first half and for every index i,
        // swap arr[i] with arr[n - i - 1]
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 3, 2, 6, 5 };

        reverseArray(arr);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

        /*
         * Time Complexity: O(n), Copying elements to a new array is a linear operation.
         * Auxiliary Space: O(n), as we are using an extra array to store the reversed
         * array.
         */

        System.out.println();

        int[] arr2 = { 1, 4, 3, 2, 6, 5 };

        reverseArray(arr2);

        for (int i = 0; i < arr2.length; i++)
            System.out.print(arr2[i] + " ");

        /*
         * Time Complexity: O(n), as we are visiting each element exactly once.
         * Auxiliary Space: O(1)
         */

        System.out.println();

        int[] arr3 = { 1, 4, 3, 2, 6, 5 };

        reverseArray(arr3);

        for (int i = 0; i < arr3.length; i++)
            System.out.print(arr3[i] + " ");

        /*
         * Time Complexity: O(n), the recurrence relation will be T(n) = T(n - 2) +
         * O(1), which can be simplified to O(n).
         * Auxiliary Space: O(n), as we are using recursion stack.
         */
    }
}