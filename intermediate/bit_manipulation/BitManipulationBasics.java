package intermediate;

public class BitManipulationBasics {
    // Given two binary strings A and B. Return their sum (also a binary string).
    public String addBinary(String A, String B) {
        int carry=0;
        int i=0;
        String ans ="";
        int alen=A.length();
        int blen=B.length();
        while(i<alen||i<blen||carry!=0)
        {
            int x=0;
            if(i<alen && A.charAt(alen-1-i)=='1')
                x=1;

            int y=0;
            if(i<blen && B.charAt(blen-1-i)=='1')
                y=1;

            ans = (x+y+carry)%2+ans;
            carry=(x+y+carry)/2;
            i++;
        }

        return ans;
    }
}
