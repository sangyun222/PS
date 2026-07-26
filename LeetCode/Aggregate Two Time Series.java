class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int N = series1.length, M = series2.length;
        List<List<Integer>> res = new ArrayList<>();

        int i = 0, j = 0;
        while (true) {
            if (i >= N || j >= M) break;

            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 < t2) {
                res.add(Arrays.asList(t1, v1 + v2));
                i++;
            }
            else if (t1 > t2) {
                res.add(Arrays.asList(t2, v1 + v2));
                j++;
            }
            else {
                res.add(Arrays.asList(t1, v1 + v2));
                i++;
                j++;
            }
        }

        while (i < N) {
            res.add(Arrays.asList(series1[i][0], series1[i][1]));
            i++;
        }
        while (j < M) {
            res.add(Arrays.asList(series2[j][0], series2[j][1]));
            j++;
        }

        return res;
    }
}