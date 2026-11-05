public class Hamming {
    static final int N = 15; //total bits
    static final int K = 11; //total data bits
    static final int[] PARITY_POS = {1,2,4,8};
    static final int[] DATA_POS = {3,5,6,7,9,10,11,12,13,14,15};

    //ensures correct input length and binary format
    static void validate(String s,int length, String name){
        if (s.length() != length || !s.matches("[01]+")){ //[01]+ checks for at least one 0 or 1, but can be more
            throw new IllegalArgumentException(name + " must be exactly " +
                    length + " binary digits, got \"" + s + "\"");
        }
    }
}
