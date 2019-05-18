import javax.sound.sampled.AudioInputStream;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            WavHeader header = new WavHeader("");
            WavHeaderReader reader = new WavHeaderReader(header);
            reader.read();
        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
