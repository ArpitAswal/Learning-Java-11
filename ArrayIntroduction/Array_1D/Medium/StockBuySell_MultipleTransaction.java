/*
Given an array prices[] representing stock prices, find the maximum total profit that can be earned by buying and selling the stock any number of times.

Note: We can only sell a stock which we have bought earlier and we cannot hold multiple stocks on any day.

Examples:

Input: prices[] = [100, 180, 260, 310, 40, 535, 695]
Output: 865
Explanation: Buy the stock on day 0 and sell it on day 3 = 310 - 100 = 210 and Buy the stock on day 4 and sell it on day 6 = 695 - 40 = 655 so the Maximum Profit  is = 210 + 655 = 865.

Input: prices[] = [4, 2]
Output: 0
Explanation: Stock prices keep decreasing, there is no chance to sell at a higher price after buying, so no profit can be made.

Solution 1: [Naive Approach] By Trying All Possibility - O(2n) Time and O(n) Space
The idea is to use recursion to simulate all choices of buying and selling. For each day, you can either skip it or buy on that day. If you buy at day i, then you try all possible selling days j > i where price[j] > price[i].

Solution 2: [Better Approach] Using Local Minima and Maxima - O(n) Time and O(1) Space
The idea is to traverse the array from left to right and Find local minima (where price starts rising) and then a local maxima (where price stops rising). Compute the difference between two and add to the result. 

Solution 3: [Expected Approach] By Accumulating Profit - O(n) Time and O(1) Space
The idea is that profit only comes when prices rise. If the price goes up from one day to the next, we can think of it as buying yesterday and selling today. 
Instead of waiting for the exact bottom and top, we simply grab every small upward move. Adding these small gains together is the same as if we had bought at each valley and sold at each peak because every rise between them gets counted.
*/

public class StockBuySell_MultipleTransaction {

    // Recursive function to find max profit
    static int maxProfitRec(int[] price, int start, int end) {
        int res = 0;

        // Try every possible pair of buy (i) and sell (j)
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
               
                // Valid transaction if selling price > buying price
                if (price[j] > price[i]) {
               
                    // Current profit + profit from left and right parts
                    int curr = (price[j] - price[i]) +
                               maxProfitRec(price, start, i - 1) +
                               maxProfitRec(price, j + 1, end);
                    res = Math.max(res, curr);
                }
            }
        }
        return res;
    }

    static int maxProfit1(int[] prices) {
        return maxProfitRec(prices, 0, prices.length - 1);
    }

    static int maxProfit2(int[] prices) {
        int n = prices.length;
        int lMin = prices[0];  
        int lMax = prices[0];  
        int res = 0;

        int i = 0;
        while (i < n - 1) {
          
            // Find local minima
            while (i < n - 1 && prices[i] >= prices[i + 1]) { i++; }
            lMin = prices[i];
           
            // Local Maxima
            while (i < n - 1 && prices[i] <= prices[i + 1]) { i++; }
            lMax = prices[i];
          
            // Add current profit
            res += (lMax - lMin);
        }
          return res;
    }

    static int maxProfit3(int[] prices) {
        int res = 0;

        // Keep on adding the difference between
        // adjacent when the prices a
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) 
                res += prices[i] - prices[i - 1];
        }
        
        return res;
    }


    public static void main(String[] args) {
        int[] prices = {100, 180, 260, 310, 40, 535, 695};
        System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }
}



