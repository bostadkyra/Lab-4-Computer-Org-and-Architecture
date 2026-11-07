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
        } catch (IllegalArgumentException | java.util.NoSuchElementException e){
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
            return;
        }

        String synBits = String.format("%4s", Integer.toBinaryString(s)).replace(' ', '0'); //%4s forces format to be a string of width 4
        System.out.println("\n[Hamming]");
        System.out.println("Original data (11): " + data);
        System.out.println("Encoded codeword (15): " + encoded);
        System.out.println("Received (15): " + received);
        System.out.println("Syndrome: " + synBits + " (decimal = " + s + ")");
        if (s !=0){
            System.out.println("Single-bit error detected at position " + s + " (starting at 1 from left to right); corrected.");
        } else {
            System.out.println("No error detected.");
        }
        System.out.println("Corrected codeword (15): " + corrected);
        System.out.println("Decoded data (11): " + Hamming.decode(corrected));

    }
}
