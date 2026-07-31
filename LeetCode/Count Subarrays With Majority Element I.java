class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) nums[i] = 1;
            else nums[i] = -1;
        }

        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int res = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= nums.length - i; j++) {
                if (prefix[j + i] - prefix[j] > 0) res++;
            }
        }

        return res;
    }
}