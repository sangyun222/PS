class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int a = -1, b = -1, c = -1;

        int res = 0;
        for (int i = 0; i < n; i++) {
            char tmp = s.charAt(i);

            if (tmp == 'a') a = i;
            else if (tmp == 'b') b = i;
            else c = i;

            if (a == -1 || b == -1 || c == -1) continue;

            res += Math.min(a, Math.min(b, c)) + 1;
        }

        return res;
    }
}