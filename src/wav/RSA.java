package wav;

import java.io.DataInputStream;
import java.math.BigInteger;
import java.util.Random;

public class RSA {

    // == private fields ==
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitLenght = 1024;
    private Random random;

    // == constructors ==
    public RSA() {
        random = new Random();
        p = BigInteger.probablePrime(bitLenght, random);
        q = BigInteger.probablePrime(bitLenght, random);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLenght / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    // == functions ==
    private static String bytesToString (byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }

    public byte[] encrypt (byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    public byte[] decrypt (byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);
        String testString;
        System.out.println("Enter the plain text: ");
        testString = in.readLine();
        System.out.println("Enctrypting string: " + testString);
        System.out.println("String in Bytes: " + bytesToString(testString.getBytes()));
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
        System.out.println(rsa.p);
        System.out.println(rsa.q);

//        RSA rsa = new RSA();
//
//        try {
//            WavHeader header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/Yamaha-V50-Rock-Beat-120bpm.wav");
//            WavHeaderReader reader = new WavHeaderReader(header);
//            reader.read();
//            reader.getDataByteArray();
//            byte[] toEncrypt = header.getDataChunkBytes();
//            byte[] encrypted = rsa.encrypt(toEncrypt);
//            byte[] decrypted = rsa.decrypt(encrypted);
//            System.out.println("DecrBytes: " + bytesToString(decrypted));
//            System.out.println("DecrStr: " + new String(decrypted));
//        } catch (Exception e) {
//            throw new IOException(e);
//        }
    }
}
