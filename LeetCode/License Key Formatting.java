class Solution {
    public String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase();
        StringBuilder res = new StringBuilder();

        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char tmp = s.charAt(i);
            if (tmp == '-') continue;

            if (cnt == k) {
                res.append("-");
                cnt = 0;
            }

            res.append(tmp);
            cnt++;
        }

        return res.reverse().toString();
    }
}