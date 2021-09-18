import java.util.Arrays;

class Solution1 {
    public double average(int[] salary) {
        Arrays.sort(salary);
        int result = 0;
        for (int i = 1; i < salary.length - 1; i++) {
            result += salary[i];
        }
        return result / (salary.length - 2);
    }
}