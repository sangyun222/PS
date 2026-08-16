class Solution {
    public int elevatorRequests(int n, int[] requests) {
        int res = 0;
        int now = 0;

        for (int r : requests) {
            res += Math.abs(now - r);
            now = r;
        }

        return res;
    }
}