import java.util.Arrays;

class theMaximumLengthofValidSum {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k];
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1); // each element alone can start subsequence of length 1

            for (int j = 0; j < i; j++) {
                int modulo = (nums[j] + nums[i]) % k;
                if (dp[j][modulo] + 1 > dp[i][modulo]) {
                    dp[i][modulo] = dp[j][modulo] + 1;
                    maxLen = Math.max(maxLen, dp[i][modulo]);
                }
            }
        }

        return maxLen;
    }
}
