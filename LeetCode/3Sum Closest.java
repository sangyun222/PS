class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int res = nums[0] + nums[1] + nums[2];
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - tmp) < Math.abs(target - res)) res = tmp;

                if (tmp < target) left++;
                else if (tmp > target) right--;
                else return tmp;
            }
        }

        return res;
    }
}