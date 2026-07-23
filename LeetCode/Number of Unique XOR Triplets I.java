class Solution {
    public int uniqueXorTriplets(int[] nums) {
        if (nums.length == 1) return 1;
        else if (nums.length == 2) return nums[0] == nums[1] ? 1 : 2;

        int mx = -1;
        for (int n : nums) mx = Math.max(mx, n);
        int cnt = Integer.toBinaryString(mx).length();

        return 1 << cnt;
    }
}