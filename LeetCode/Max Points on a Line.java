class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int res = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            HashMap<Double, Integer> map = new HashMap<>();

            for (int j = 0; j < n; j++) {
                double m = (double)(points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                map.put(m, map.getOrDefault(m, 0) + 1);
                res = Math.max(res, map.get(m));
            }
        }

        return res + 1;
    }
}