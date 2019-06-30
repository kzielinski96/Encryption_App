package wav;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    // == private fields ==
    private BigInteger n, d, e;
    private int bitlen = 1024;

    // == constructors ==
    public RSA(BigInteger newn, BigInteger newe) {
        n = newn;
        e = newe;
    }

    public RSA(int bits) {
        bitlen = bits;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }

    // == public methods ==
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public byte[] generateKeyWithRSA(int length) {
        byte[] b = new byte[length];
        byte counter = 0;
        for (int i = 0; i < b.length; i++) {
            BigInteger message = BigInteger.valueOf(counter);
            BigInteger k = encrypt(message);
            b[i]=k.toByteArray()[k.toByteArray().length-1];
            counter++;
        }
        System.out.println("Key generated");
        return b;
    }

    public void encryptWav(String path, String name, byte[] key) throws Exception {
        try {
            WavHeader header = new WavHeader(path);
            WavHeaderReader reader = new WavHeaderReader(header);
            reader.read();
            byte[] dataTmp = header.getDataChunkBytes();
            for (int i = 0; i < dataTmp.length; i++) {
                dataTmp[i]^=key[i];
            }
            reader.saveWav(name, dataTmp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static void main(String[] args) {
        try {
            RSA rsa = new RSA(256);
            WavHeader header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/africa-toto.wav");
            WavHeaderReader reader = new WavHeaderReader(header);
            reader.read();
            byte[] key = rsa.generateKeyWithRSA(header.getDataChunkBytes().length);
            rsa.encryptWav(header.getPath(), "encrypted-toto", key);
            rsa.encryptWav("/home/kub/Pulpit/platformy_java/Encryption_App/encrypted-toto.wav", "decrypted-toto", key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}