class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);

        int n = prices.length;
        int m = discounts.length;
        double res = 0.0;

        int idx = Math.min(n, m);
        for (int i = 0; i < idx; i++) {
            int p = prices[n - 1 - i];
            int d = discounts[m - 1 - i];

            res += p * (100.0 - d) / 100.0;
        }

        if (n > m) {
            for (int i = m; i < n; i++) {
                res += prices[n - 1 - i];
            }
        }

        return res;
    }
}