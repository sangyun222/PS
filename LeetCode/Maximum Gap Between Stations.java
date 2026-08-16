class Solution {
    public int maximumGap(String skill, String station) {
        int n = skill.length(), m = station.length();
        if (n == 1) return 0;

        int[] right = new int[n];
        int idx = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (station.charAt(idx) != skill.charAt(i)) idx--;
            right[i] = idx--;
        }

        int[] left = new int[n];
        idx = 0;
        for (int i = 0; i < n; i++) {
            while (station.charAt(idx) != skill.charAt(i)) idx++;
            left[i] = idx++;
        }

        int res = 0;
        for (int i = 1; i < n; i++) res = Math.max(res, right[i] - left[i - 1]);

        return res;
    }
}