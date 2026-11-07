import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String data;
        String encoded;
        String received;
        String corrected;
        int s;

        try{
            Scanner in = new Scanner(System.in);
            if (args.length > 0){
                data = args[0].trim();
            } else {
                System.out.println("Enter 11-bit data: ");
                data = in.nextLine().trim();
            }
            Hamming.validate(data, Hamming.K, "Data");
            encoded = Hamming.encode(data);

            if(args.length > 1){
                received = args[1].trim();
            } else {
                System.out.println("Encoded codeword (15): " + encoded);
                System.out.println("Enter received 15-bit word: ");
                received = in.nextLine().trim();
            }
            Hamming.validate(received, Hamming.N, "Received word");
            s = Hamming.syndrome(received);
            corrected = Hamming.correct(received, s);
        }
    }
}
