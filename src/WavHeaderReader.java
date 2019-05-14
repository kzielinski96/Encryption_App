import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class WavHeaderReader {

    // == private fields ==
    private static final int HEADER_SIZE = 44;     // size of the wav header
    private byte[] buf = new byte[HEADER_SIZE];    // buffer containing bytes of the wav header
    private WavHeader header = new WavHeader();
    private InputStream inputStream;

    // == contructors ==
    public WavHeaderReader() {}

    public WavHeaderReader (String source) throws IOException {
        inputStream = new FileInputStream(source);
    }

    public WavHeaderReader (InputStream inputStream) {
        this.inputStream = inputStream;
    }

    // == public methods ==
    public byte[] getBuf() {
        return buf;
    }

    public WavHeader getHeader() {
        return header;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public WavHeader read() throws IOException {
        int res = inputStream.read(buf);
        if (res != HEADER_SIZE) {
            throw new IOException("Invalid header !");
        }
        header.setChunkSize(Arrays.copyOfRange(buf, 0, 4));
        if (new String(header.getChunkID()).compareTo("RIFF") != 0) {
            throw new IOException("Illegal format !");
        }
        header.setChunkSize(toInt(4, false));
        header.setFormat(Arrays.copyOfRange(buf, 8, 12));
        header.setSubChunk1ID(Arrays.copyOfRange(buf, 12, 16));
        header.setSubChunk1Size(toInt(16, false));
        header.setAudioFormat(toShort(20, false));
        header.setNumChannels(toShort(22, false));
        header.setSampleRate(toInt(24, false));
        header.setByteRate(toInt(28, false));
        header.setBlockAlign(toShort(32, false));
        header.setBitsPerSample(toShort(34, false));
        header.setSubChunk2ID(Arrays.copyOfRange(buf, 36, 40));
        header.setSubChunk2Size(toInt(40, false));
        return header;
    }

    // == private methods ==
    private int toInt (int start, boolean endian) {
        int k = (endian) ? 1 : -1;
        if (!endian) {
            start += 3;
        }
        return (buf[start] << 24) + (buf[start + k * 1] << 16) +
                (buf[start + k * 2] << 8) + buf[start + k * 3];
    }

    private short toShort (int start, boolean endian) {
        short k = (endian) ? (short) 1 : -1;
        if (!endian) {
            start++;
        }
        return (short) ((buf[start] << 8) + (buf[start + k * 1]));
    }
}
