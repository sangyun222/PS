class Solution {
    public int countValidPrefixes(String s) {
        int cnt0 = 0, cnt1 = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') cnt0++;
            else cnt1++;

            if (Math.abs(cnt0 - cnt1) <= 1) res++;
        }

        return res;
    }
}