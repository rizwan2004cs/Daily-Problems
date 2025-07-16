
class MaxLenOfValidSeq {
    public int maximumLength(int[] nums) {
        int[][] dp = new int[2][2];
        for (int x : nums) {
            int a = x % 2;
            for (int b = 0; b < 2; b++) {
                dp[b][a] = Math.max(dp[b][a], dp[a][b] + 1);
            }
            dp[a][a] = Math.max(dp[a][a], 1);
        }

        int ans = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                ans = Math.max(ans, dp[i][j]);

        return ans;
    }
}
