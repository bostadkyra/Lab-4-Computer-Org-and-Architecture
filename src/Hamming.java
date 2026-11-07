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

    static String encode(String data){
        validate(data, K, "Data");
        int[] codeword = new int[N+1]; //not using index 0
        for (int i = 0; i < K; i++){
            codeword[DATA_POS[i]] = data.charAt(i) - '0'; // -'0' converts characters '0' and '1' to their numerical values
        }
        for (int p: PARITY_POS){
            int sum = 0;
            for (int i = 1; i <= N; i++){
                if ((i & p) != 0 && i !=p){
                    sum += codeword[i];
                }
            codeword[p] = sum % 2; //will give a 0 or 1
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++){
            sb.append(codeword[i]);
        }
        return sb.toString(); //converts codeword to a String
    }

    static int syndrome (String word){
        int s = 0;
        for (int i = 1; i <= word.length(); i++){
            if (word.charAt(i-1) == '1'){
                s ^= i;
            }
        }
        return  s;
    }

    static String decode(String codeword){
        StringBuilder sb = new StringBuilder();
        for (int p: DATA_POS){
            sb.append(codeword.charAt(p-1));
        }
        return sb.toString();
    }
}
