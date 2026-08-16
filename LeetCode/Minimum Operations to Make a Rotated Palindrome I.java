class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int cost = i;
            int left = 0, right = n - 1;

            while (left <= right) {
                char c1 = s.charAt((left + i) % n);
                char c2 = s.charAt((right + i) % n);

                if (c1 != c2) {
                    int tmp = Math.abs(c1 - c2);
                    cost += Math.min(tmp, 26 - tmp);
                }

                left++;
                right--;
            }

            res = Math.min(res, cost);
        }

        return res;
    }
}