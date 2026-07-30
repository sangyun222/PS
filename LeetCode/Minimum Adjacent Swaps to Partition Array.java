class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        int MOD = 1000000007;

        long ans = 0, cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > b) cnt2++;
            else if (nums[i] >= a) {
                ans = (ans + cnt2) % MOD;
                cnt1++;
            }
            else {
                ans = (ans + cnt1 + cnt2) % MOD;
            }
        }

        return (int)(ans % MOD);
    }
}