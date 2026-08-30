public class Solution1423 {

    public int maxScore(int[] cardPoints, int k) {

        long totalSum = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            totalSum += cardPoints[i];
        }

        long currSum = 0L;
        int res = 0;
        for (int i = 0; i < cardPoints.length-k; i++) {
            currSum += cardPoints[i];
        }
        res = (int) (totalSum-currSum);

        for (int i = cardPoints.length-k; i < cardPoints.length; i++) {
            currSum -= cardPoints[i -(cardPoints.length-k)];
            currSum += cardPoints[i];
            res = (int) Math.max(res, totalSum-currSum);
        }
        return res;

    }
}
