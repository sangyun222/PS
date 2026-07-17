class Solution {
    public String maskPII(String s) {
        if (s.contains("@")) {
            String[] tmp = s.toLowerCase().split("@");
            StringBuilder sb = new StringBuilder();

            sb.append(tmp[0].charAt(0)).append("*****").append(tmp[0].charAt(tmp[0].length() - 1)).append("@").append(tmp[1]);
            return sb.toString();
        }
        else {
            int digit_cnt = 0, idx = s.length() - 1;
            StringBuilder sb = new StringBuilder();

            while (idx >= 0) {
                char tmp = s.charAt(idx);
                if (Character.isDigit(tmp)) {
                    digit_cnt++;

                    if (digit_cnt <= 4) sb.append(tmp);
                }
                idx--;
            }

            sb.append("-***-***");
            if (digit_cnt > 10) {
                sb.append("-");
                for (int i = 0; i < digit_cnt - 10; i++) sb.append("*");
                sb.append("+");
            }
            return sb.reverse().toString();
        }
    }
}