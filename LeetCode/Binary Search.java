class Solution {
    private int BSearch(int[] nums, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        if (nums[mid] < target) return BSearch(nums, target, mid + 1, right);
        else if (nums[mid] > target) return BSearch(nums, target, left, mid - 1);
        else return mid;
    }

    public int search(int[] nums, int target) {
        return BSearch(nums, target, 0, nums.length - 1);
    }
}