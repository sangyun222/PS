class Solution {
    long res = 0;
    public long countMajoritySubarrays(int[] nums, int target) {
        boolean isNeverTarget = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                nums[i] = 1;
                isNeverTarget = false;
            }
            else nums[i] = -1;
        }
        if (isNeverTarget) return 0;

        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        merge_sort(prefix);
        return res;
    }
    private void merge_sort(int[] prefix) {
        merge_sort(prefix, 0, prefix.length - 1);
    }
    private void merge_sort(int[] prefix, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        merge_sort(prefix, left, mid);
        merge_sort(prefix, mid + 1, right);

        merge(prefix, left, mid, right);
    }
    private void merge(int[] prefix, int left, int mid, int right) {
        int l = left, r = mid + 1, idx = left;

        while (l <= mid && r <= right) {
            if (prefix[l] < prefix[r]) {
                res += right - r + 1;
                l++;
            }
            else r++;
        }

        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (prefix[i] <= prefix[j]) tmp[k++] = prefix[i++];
            else tmp[k++] = prefix[j++];
        }

        while (i <= mid) tmp[k++] = prefix[i++];
        while (j <= right) tmp[k++] = prefix[j++];

        System.arraycopy(tmp, 0, prefix, left, tmp.length);
    }
}