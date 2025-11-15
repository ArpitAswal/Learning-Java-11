/*
Maximum Product Subarray

Given an array arr[] consisting of positive, negative, and zero values, find the maximum product that can be obtained from any contiguous subarray of arr[].

Examples:

Input: arr[] = [-2, 6, -3, -10, 0, 2]
Output: 180
Explanation: The subarray with maximum product is [6, -3, -10] with product = 6 * (-3) * (-10) = 180.

Input: arr[] = [-1, -3, -10, 0, 6]
Output: 30
Explanation: The subarray with maximum product is [-3, -10] with product = (-3) * (-10) = 30.

Input: arr[] = [2, 3, 4] 
Output: 24 
Explanation: For an array with all positive elements, the result is product of all elements. 
 */

/*
Solution 1: [Naive Approach] Using Two Nested Loops – O(n^2) Time and O(1) Space
The idea is to traverse over every contiguous subarray, find the product of each of these subarrays and return the maximum product among all the subarrays.
 
Solution 2: [Expected Approach - 2] By Traversing in Both Directions - O(n) Time and O(1) Space
We will follow a simple approach that is to traverse from the start and keep track of the running product and if the running product is greater than the max product, then we update the max product. Also, if we encounter '0' then make product of all elements till now equal to 1 because from the next element, we will start a new subarray.

But what is the problem with this approach?

Problem will occur when our array will contain odd no. of negative elements. In that case, we have to reject one negative element so that we can even no. of negative elements and their product can be positive. Now, since subarray should be contiguous so we can't simply reject any one negative element. We have to either reject the first negative element or the last negative element.

Now, if we traverse from start then only the last negative element can be rejected and if we traverse from the last then the first negative element can be rejected. So we will traverse from both ends and find the maximum product subarray. 
 */

class MaximumProductSubArray {
  
    static int maxProduct1(int arr[]) { 

      	int n = arr.length;
      
        // Initializing result
        int maxProd = arr[0];

        for (int i = 0; i < n; i++) {
            int mul = 1;
          
            // traversing in current subarray
            for (int j = i; j < n; j++) {
                mul *= arr[j];
              
                // updating result every time
                // to keep track of the maximum product
                maxProd = Math.max(maxProd, mul);
            }
        }
        return maxProd;
    }

    static int maxProduct2(int[] arr) {

        int n = arr.length;
        int maxProd = Integer.MIN_VALUE;
  
        // leftToRight to store product from left to Right
        int leftToRight = 1;
  
        // rightToLeft to store product from right to left
        int rightToLeft = 1;
  
        for (int i = 0; i < n; i++) {
            if (leftToRight == 0)
                leftToRight = 1;
            if (rightToLeft == 0)
                rightToLeft = 1;
      
            // calculate product from index left to right
            leftToRight *= arr[i];
      
            // calculate product from index right to left
            int j = n - i - 1;
            rightToLeft *= arr[j];
            maxProd = Math.max(leftToRight, 
                           	Math.max(rightToLeft, maxProd));
        }
        return maxProd;
    }

    public static void main(String[] args) {
        int arr[] = { -2, 6, -3, -10, 0, 2 };
        System.out.println(maxProduct1(arr));
        System.out.println(maxProduct2(arr));
    }
}