import javax.sound.sampled.AudioInputStream;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            WavHeader header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/Yamaha-V50-Rock-Beat-120bpm.wav");
            WavHeaderReader reader = new WavHeaderReader(header);
            reader.read();
        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
