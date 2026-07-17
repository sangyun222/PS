class Solution {
    public String maskPII(String s) {
        int atIdx = s.indexOf('@');

        if (atIdx != -1) {
            s = s.toLowerCase();
            return s.charAt(0) + "*****" + s.charAt(atIdx - 1) + s.substring(atIdx);
        }
        else {
            int digit_cnt = 0;
            StringBuilder sb = new StringBuilder();

            for (int i = s.length() - 1; i >= 0; i--) {
                char tmp = s.charAt(i);
                if (Character.isDigit(tmp)) {
                    digit_cnt++;

                    if (digit_cnt <= 4) sb.append(tmp);
                }
            }

            sb.append("-***-***");
            if (digit_cnt > 10) sb.append("-").append("*".repeat(digit_cnt - 10)).append("+");

            return sb.reverse().toString();
        }
    }
}