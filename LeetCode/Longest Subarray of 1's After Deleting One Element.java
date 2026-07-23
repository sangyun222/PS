class Solution {
    public int longestSubarray(int[] nums) {
        int mx = 0, cnt = 0, tmp = 0;
        for (int n : nums) {
            if (n == 1) mx = Math.max(mx, ++cnt + tmp);
            else {
                tmp = cnt;
                cnt = 0;
            }
        }

        return mx == nums.length ? mx - 1 : mx;
    }
}