class Solution {
    long[] tmp;
    long res;
    public long countRatioSubarrays(int[] nums, int a, int b) {
        long[] prefix = new long[nums.length + 1];
        prefix[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) prefix[i + 1] = prefix[i] - b;
            else prefix[i + 1] = prefix[i] + a;
        }

        res = 0;
        merge_sort(prefix);
        return res;
    }
    private void merge_sort(long[] prefix) {
        tmp = new long[prefix.length];
        merge_sort(prefix, 0, prefix.length - 1);
    }
    private void merge_sort(long[] prefix, int left, int right) {
        if (left == right) return;

        int mid = (left + right) / 2;
        merge_sort(prefix, left, mid);
        merge_sort(prefix, mid + 1, right);

        merge(prefix, left, mid, right);
    }
    private void merge(long[] prefix, int left, int mid, int right) {
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && prefix[j] < prefix[i]) j++;
            res += right - j + 1;
        }

        int i = left;
        j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (prefix[i] <= prefix[j]) tmp[k++] = prefix[i++];
            else tmp[k++] = prefix[j++];
        }

        while (i <= mid) tmp[k++] = prefix[i++];
        while (j <= right) tmp[k++] = prefix[j++];

        System.arraycopy(tmp, left, prefix, left, right - left + 1);
    }
}