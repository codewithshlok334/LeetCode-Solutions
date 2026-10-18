import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private static long[][] table;

    static {
        table = new long[1001][1001];
        for (int i = 0; i <= 1000; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }
    }

    public int numOfWays(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) ((getWays(list) - 1 + MOD) % MOD);
    }

    private long getWays(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            int val = nums.get(i);
            if (val < root) {
                leftSubtree.add(val);
            } else {
                rightSubtree.add(val);
            }
        }

        long leftWays = getWays(leftSubtree);
        long rightWays = getWays(rightSubtree);

        long combinations = table[n - 1][leftSubtree.size()];

        return (((combinations * leftWays) % MOD) * rightWays) % MOD;
    }
}