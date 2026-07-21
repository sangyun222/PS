class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = 0;

        while (true) {
            if (i >= j) break;

            if (height[i] <= height[j]) {
                res = Math.max(res, (j - i) * height[i]);
                i++;
            }
            else {
                res = Math.max(res, (j - i) * height[j]);
                j--;
            }
        }

        return res;
    }
}