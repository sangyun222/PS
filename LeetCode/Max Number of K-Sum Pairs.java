class Solution {
    public int maxOperations(int[] nums, int k) {
        if (nums.length == 1) return 0;
        Arrays.sort(nums);

        int res = 0;
        int i = 0, j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum > k) j--;
            else if (sum < k) i++;
            else {
                res++;
                i++;
                j--;
            }
        }

        return res;
    }
}