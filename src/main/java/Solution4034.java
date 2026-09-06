public class Solution4034 {

    public int minBishopMoves(int[] source, int[] target) {

        if(source[0] == target[1] && source[1] == target[0]) return 1;
        if(source[1]- source[0] == target[1]-target[0]) return 1;

        if((source[0]+source[1])%2 == (target[0]+target[1])%2) {
            if((source[0] + source[1]) == (target[0] + target[1])) return 1;
            else return 2;
        }

        return -1;

    }
}
