class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if (tmp == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }
                else if (tmp < 0) left++;
                else if (tmp > 0) right--;
            }
        }


        return ans;
    }
}