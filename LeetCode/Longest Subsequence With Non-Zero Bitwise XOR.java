class Solution {
    public int longestSubsequence(int[] nums) {
        boolean isAllZero = true;

        int res = nums[0];
        if (res != 0) isAllZero = false;

        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
            if (nums[i] != 0) isAllZero = false;
        }

        if (isAllZero) return 0;
        return res == 0 ? nums.length - 1 : nums.length;
    }
}