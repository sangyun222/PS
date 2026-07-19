class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();

        int prev_start = intervals[0][0], prev_end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (prev_start <= start && start <= prev_end) {
                prev_end = Math.max(prev_end, end);
            }
            else {
                res.add(new int[]{prev_start, prev_end});
                prev_start = start;
                prev_end = end;
            }
        }
        res.add(new int[]{prev_start, prev_end});
        return res.toArray(new int[res.size()][]);
    }
}