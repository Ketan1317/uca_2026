public class Cards{
    // 0-12 - spade
    // 13-25 - clubs
    // 26-38 - hearts
    // 39-51 - diamond
    public static void main(String[] args) {
        
    }

    public int highest(int c1,int c2){
        if(c1%13 > c2%13){
            return 1;
        }
        else if(c1%13 < c2%13){
            return -1;
        }
        else{
            return 0;
        }
    }

    public boolean sameSuit(int c1,int c2){
        return c1/13 == c2/13;
    }
}