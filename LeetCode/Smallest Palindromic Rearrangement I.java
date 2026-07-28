class Solution {
    public String smallestPalindrome(String s) {
        int[] counter = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) counter[s.charAt(i) - 'a'] += 1;

        StringBuilder sb = new StringBuilder();
        int mid = -1;
        for (int i = 0; i <= 'z' - 'a'; i++) {
            int cnt = counter[i];
            if (cnt == 0) continue;

            while (cnt >= 2) {
                sb.append((char)('a' + i));
                cnt -= 2;
            }
            if (cnt == 1) mid = i;
        }

        StringBuilder tmp = new StringBuilder(sb);
        if (mid != -1) sb.append((char)('a' + mid));
        sb.append(tmp.reverse());

        return sb.toString();
    }
}