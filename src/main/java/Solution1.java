import java.util.Arrays;


class Solution {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums)
                .parallel()
                .sorted()
                .reduce((n1, n2) -> n1 ^ n2)
                .getAsInt();
    }
}