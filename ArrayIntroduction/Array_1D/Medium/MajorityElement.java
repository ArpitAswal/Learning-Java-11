/*
Given an array arr[] consisting of n integers, the task is to find all the array elements which occurs more than floor(n/3) times.
Note: The returned array of majority elements should be sorted.

Examples:

Input: arr[] = {2, 2, 3, 1, 3, 2, 1, 1}
Output: {1, 2}
Explanation: The frequency of 1 and 2 is 3, which is more than floor n/3 (8/3 = 2).

Input: arr[] = {-5, 3, -5}
Output: {-5}
Explanation: The frequency of -5 is 2, which is more than floor n/3 (3/3 = 1).

Input: arr[] = {3, 2, 2, 4, 1, 4}
Output: { }
Explanation: There is no majority element.
 */

/*
 Solutions
 1. [Naive Approach] Using Nested Loops - O(n^2) Time and O(1) Space
The idea is to iterate over all elements and count the frequency of the element in the array. If the frequency of the element is greater than floor(n/3), add it to the result. 
To avoid adding duplicate elements into the result, we can check if the element is already present in the result. We can stop the iteration if we have already found two majority elements.
 2. [Better Approach] Using Hash Map or Dictionary - O(n) Time and O(n) Space
The idea is to use a hash map or dictionary to count the frequency of each element in the array. After counting, iterate over the hash map and if the frequency of any element is greater than (n/3), push it into the result. Finally, the majority elements are returned after sorting.
*/

// Java program to find Majority element in an array
// using nested loop

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MajorityElement {

    // Function to find Majority elements in an array
    static List<Integer> findMajority(int[] arr) {
        int n = arr.length;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            // Count the frequency of arr[i]
            int cnt = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] == arr[i])
                    cnt += 1;
            }

            // Check if arr[i] is a majority element
            if (cnt > (n / 3)) {

                // Add arr[i] only if it is not already
                // present in the result
                if (res.size() == 0 || arr[i] != res.get(0)) {
                    res.add(arr[i]);
                }
            }

            // If we have found two majority elements,
            // we can stop our search
            if (res.size() == 2) {
                if (res.get(0) > res.get(1))
                    java.util.Collections.swap(res, 0, 1);
                break;
            }
        }

        return res;
    }

    // Function to find Majority element in an array
    static List<Integer> findMajority2(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        // find frequency of each number
        for (int ele : arr)
            freq.put(ele, freq.getOrDefault(ele, 0) + 1);

        // Iterate over each key-value pair in the hash map
        for (Map.Entry<Integer, Integer> it : freq.entrySet()) {
            int ele = it.getKey();
            int cnt = it.getValue();

            // Add the element to the result, if its frequency
            // if greater than floor(n/3)
            if (cnt > n / 3)
                res.add(ele);
        }

        if (res.size() == 2 && res.get(0) > res.get(1)) {
            int temp = res.get(0);
            res.set(0, res.get(1));
            res.set(1, temp);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 2, 3, 1, 3, 2, 1, 1 };
        List<Integer> res = findMajority(arr);
        for (int ele : res)
            System.out.print(ele + " ");

        System.out.println();

        List<Integer> res2 = findMajority2(arr);
        for (int ele : res2) {
            System.out.print(ele + " ");
        }
    }
}
