class Solution {
    public int maximumLengthSubstring(String s) {
        char[] counter = new char['z' - 'a' + 1];
        int left = 0, right = 0;

        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            counter[c - 'a']++;

            while (counter[c - 'a'] > 2) {
                counter[s.charAt(left) - 'a']--;
                left++;
            }
            right++;

            res = Math.max(res, right - left);
        }

        return res;
    }
}