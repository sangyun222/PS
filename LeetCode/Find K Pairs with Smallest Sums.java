class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < Math.min(nums1.length, k); i++) heap.offer(new int[]{nums1[i] + nums2[0], i, 0});

        while (k > 0 && !heap.isEmpty()) {
            int[] tmp = heap.poll();
            res.add(Arrays.asList(nums1[tmp[1]], nums2[tmp[2]]));

            if (tmp[2] + 1 < nums2.length) heap.offer(new int[]{nums1[tmp[1]] + nums2[tmp[2] + 1], tmp[1], tmp[2] + 1});
            k--;
        }

        return res;
    }
}