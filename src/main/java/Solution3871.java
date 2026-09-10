public class Solution3871 {

    public long countCommas(long n) {
        if(n<1000){
            return 0;
        }
        String s = String.valueOf(n);
        int size = (int)Math.floor((double) s.length() /3);

        long v = (long)(Math.pow(10,3*size));
        if(v>n) {
            size--;
        }
        long k = (long)(Math.pow(10,3*size));

        long t = ((k -999L) + size-1);

        // System.out.println(size);
        //s += 2000-999;
        // 742752114
        // 1229256342
        t += (n-k)*size;
        ///System.out.println(t);
        if(size>2) {
            for(int i = size-1 ;i>1;i--){
                long p = (long)(Math.pow(10,3*(i)));
                t += k-p;
            }
        }
        return t;
    }
}
