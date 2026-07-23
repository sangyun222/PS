class Solution {
    public int longestSubarray(int[] nums) {
        boolean isAllZero = true, isAllOne = true;
        for (int n : nums) {
            if (n == 0) isAllOne = false;
            else isAllZero = false;
        }
        if (isAllZero) return 0;
        if (isAllOne) return nums.length - 1;

        int mx = 0, cnt = 0, tmp = 0;
        for (int n : nums) {
            if (n == 1) mx = Math.max(mx, ++cnt + tmp);
            else {
                tmp = cnt;
                cnt = 0;
            }
        }

        return mx;
    }
}