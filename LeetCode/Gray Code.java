class Solution {
    public List<Integer> grayCode(int n) {
        n = 1 << n;

        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) res.add(i ^ (i >> 1));

        return res;
    }
}