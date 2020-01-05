import java.util.Scanner;

/**
 * 注意在线提交的时候将类名改为Main
 * @author boluo
 */
public class Poj1088HuaXue {

    /**
     * 题目链接： http://poj.org/problem?id=1088
     * 题目描述：
     * 滑雪
     * Time Limit: 1000MS		Memory Limit: 65536K
     * Total Submissions: 116673		Accepted: 44685
     * Description
     *
     * Michael喜欢滑雪百这并不奇怪， 因为滑雪的确很刺激。
     * 可是为了获得速度，滑的区域必须向下倾斜，而且当你滑到坡底，你不得不再次走上坡或者等待升降机来载你。
     * Michael想知道载一个区域中最长底滑坡。区域由一个二维数组给出。数组的每个数字代表点的高度。
     * 下面是一个例子
     *  1  2  3  4 5
     *
     * 16 17 18 19 6
     *
     * 15 24 25 20 7
     *
     * 14 23 22 21 8
     *
     * 13 12 11 10 9
     *
     * 一个人可以从某个点滑向上下左右相邻四个点之一，当且仅当高度减小。
     * 在上面的例子中，一条可滑行的滑坡为24-17-16-1。当然25-24-23-...-3-2-1更长。
     * 事实上，这是最长的一条。
     * Input
     *
     * 输入的第一行表示区域的行数R和列数C(1 <= R,C <= 100)。
     * 下面是R行，每行有C个整数，代表高度h，0<=h<=10000。
     * Output
     *
     * 输出最长区域的长度。
     * Sample Input
     *
     * 5 5
     * 1 2 3 4 5
     * 16 17 18 19 6
     * 15 24 25 20 7
     * 14 23 22 21 8
     * 13 12 11 10 9
     * Sample Output
     *
     * 25
     */


    /**
     * 解题思路： 动态规划
     */

    // 数组版本
    private static void huaxue() {
        Scanner scanner = new Scanner(System.in);
        int RowNum = scanner.nextInt();
        int ColNum = scanner.nextInt();

        int[][] height = new int[RowNum][ColNum];
        int[][] maxLength = new int[RowNum][ColNum];
        for (int i = 0; i < RowNum; i++) {
            height[i] = new int[ColNum];
            maxLength[i] = new int[ColNum];
            for (int j = 0; j < ColNum; j++) {
                height[i][j] = scanner.nextInt();
                maxLength[i][j] = -1; // -1表示从这个点出发的最长路径还没有计算
            }
        }

        int res = 0;
        for (int i = 0; i < RowNum; i++) {
            for (int j = 0; j < ColNum; j++) {
                res = Math.max(res, getMaxLength(i, j, height, maxLength));
            }
        }
        System.out.println(res);
    }

    private static int getMaxLength(int i, int j, int[][] height, int[][] maxLength) {
        int maxLength_i_j = maxLength[i][j];
        if (maxLength_i_j >= 0) {
            return maxLength_i_j;
        }

        maxLength_i_j = 1; // 如果从这个点出发，上下左右都比这个点高，那这个点出发的最长路径就是1.

        if (j - 1 >= 0 && height[i][j - 1] < height[i][j]) {
            maxLength_i_j = Math.max(1 + getMaxLength(i, j - 1, height, maxLength), maxLength_i_j);
        }
        if (j + 1 < height[i].length && height[i][j + 1] < height[i][j]) {
            maxLength_i_j = Math.max(1 + getMaxLength(i, j + 1, height, maxLength), maxLength_i_j);
        }
        if (i - 1 >= 0 && height[i - 1][j] < height[i][j]) {
            maxLength_i_j = Math.max(1 + getMaxLength(i - 1, j, height, maxLength), maxLength_i_j);
        }
        if (i + 1 < height.length && height[i + 1][j] < height[i][j]) {
            maxLength_i_j = Math.max(1 + getMaxLength(i + 1, j, height, maxLength), maxLength_i_j);
        }

        maxLength[i][j] = maxLength_i_j;

        return maxLength_i_j;
    }

    public static void main(String[] args) {
        // input
        /*
            5 5
            1 2 3 4 5
            16 17 18 19 6
            15 24 25 20 7
            14 23 22 21 8
            13 12 11 10 9
        */
        huaxue();
    }
}
