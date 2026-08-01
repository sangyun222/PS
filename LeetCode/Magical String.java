class Solution {
    public int magicalString(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(122);
        int prev = 1;

        int idx = 2;
        while (sb.length() < n) {
            int cnt = sb.charAt(idx) - '0';
            for (int j = 0; j < cnt; j++) sb.append(prev);

            idx++;
            prev = (prev & 1) + 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') res++;
        }

        return res;
    }
}