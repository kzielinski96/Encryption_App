package wav;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class WavHeaderReader {

    // == fields ==
    WavHeader header = new WavHeader();
    FastFourierTransformer fastFourierTransformer = new FastFourierTransformer(DftNormalization.STANDARD);
    public double[] dataBytes = new double[1024];
    public Complex[] freqs;

    public Complex[] getFreqs() {
        return freqs;
    }

    public void setFreqs(Complex[] freqs) {
        this.freqs = freqs;
    }


    // == constructors ==
    public WavHeaderReader(WavHeader header) {
        this.header = header;
    }

    // == public methods ==
    public void read() throws IOException {
        DataInputStream file = null;
        header.setDataChunk(null);
        byte[] tmpLong = new byte[4];
        byte[] tmpInt = new byte[2];

        try {
            file = new DataInputStream(new FileInputStream(header.getPath()));
            String tmpRiff = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setRiffChunkID(tmpRiff);
            System.out.println("ChunkID: " + header.getRiffChunkID());

            file.read(tmpLong);
            header.setRiffChunkSize(byteArrayToLong(tmpLong));
            System.out.println("ChunkSize: " + header.getRiffChunkSize());

            String tmpFormatStr = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setFormat(tmpFormatStr);
            System.out.println("Format: " + header.getFormat());

            String tmpFmtSubChunkID = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setFmtSubChunkID(tmpFmtSubChunkID);
            System.out.println("FmtSubchunkID: " + header.getFmtSubChunkID());

            file.read(tmpLong);
            header.setFmtSubChunkSize(byteArrayToLong(tmpLong));
            System.out.println("FmtSubChunkSize: " + header.getFmtSubChunkSize());

            file.read(tmpInt);
            header.setAudioFormat(byteArrayToInt(tmpInt));
            System.out.println("AudioFormat: " + header.getAudioFormat());

            file.read(tmpInt);
            header.setNumChannels(byteArrayToInt(tmpInt));
            System.out.println("NumChannels: " + header.getNumChannels());

            file.read(tmpLong);
            header.setSampleRate(byteArrayToLong(tmpLong));
            System.out.println("SampleRate: " + header.getSampleRate());

            file.read(tmpLong);
            header.setByteRate(byteArrayToLong(tmpLong));
            System.out.println("ByteRate: " + header.getByteRate());

            file.read(tmpInt);
            header.setBlockAlign(byteArrayToInt(tmpInt));
            System.out.println("BlockAlign: " + header.getBlockAlign());

            file.read(tmpInt);
            header.setBitsPerSample(byteArrayToInt(tmpInt));
            System.out.println("BitsPerSample: " + header.getBitsPerSample());

            String tmpDataChunkID = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setDataChunkID(tmpDataChunkID);
            System.out.println("DataChunkID: " + header.getDataChunkID());

            file.read(tmpLong);
            header.setDataSize(byteArrayToLong(tmpLong));
            System.out.println("DataSize: " + header.getDataSize());

            System.out.println("===== DATA CHUNK =====");

            for (int i = 0; i < dataBytes.length; i++) {
                file.read(tmpLong);
                long sample = byteArrayToLong(tmpLong);
                dataBytes[i] = (double) sample;
            }
            setFreqs(fastFourierTransformer.transform(dataBytes, TransformType.FORWARD));

            file.close();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    // == private methods ==
    private int byteArrayToInt(byte[] b) {
        int start = 0;
        int low = b[start] & 0xff;
        int high = b[start+1] & 0xff;
        return (int)(high << 8 | low);
    }

    private long byteArrayToLong(byte[] b) {
        int start = 0;
        int i = 0;
        int len = 4;
        int cnt = 0;
        byte[] tmp = new byte[len];
        for (i = start; i < (start + len); i++) {
            tmp[cnt] = b[i];
            cnt++;
        }
        long accum = 0;
        i = 0;
        for ( int shiftBy = 0; shiftBy < 32; shiftBy += 8 )
        {
            accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy;
            i++;
        }
        return accum;
    }

    private byte[] intToByteArray(int i) {
        byte[] b = new byte[4];
        b[0] = (byte) (i & 0xff);
        b[1] = (byte) ((i >> 8) & 0x000000FF);
        b[2] = (byte) ((i >> 16) & 0x000000FF);
        b[3] = (byte) ((i >> 24) & 0x000000FF);
        return b;
    }

    private byte[] shortToByteArray (short data) {
        return new byte[]{(byte)(data & 0xff),(byte)((data >>> 8) & 0xff)};
    }
}