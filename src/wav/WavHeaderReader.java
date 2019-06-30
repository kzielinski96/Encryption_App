package wav;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.Arrays;

public class WavHeaderReader {

    // == fields ==
    WavHeader header;

    // == constructors ==
    public WavHeaderReader(WavHeader header) {
        this.header = header;
    }

    // == public methods ==
    public void read() throws IOException {
        DataInputStream file = null;
        header.setDataChunkDouble(null);

        byte[] tmpLong = new byte[4];
        byte[] tmpInt = new byte[2];

        try {
            file = new DataInputStream(new FileInputStream(header.getPath()));
            String tmpRiff = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setRiffChunkID(tmpRiff);

            file.read(tmpLong);
            header.setRiffChunkSize(byteArrayToLong(tmpLong));

            String tmpFormatStr = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setFormat(tmpFormatStr);

            String tmpFmtSubChunkID = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setFmtSubChunkID(tmpFmtSubChunkID);

            file.read(tmpLong);
            header.setFmtSubChunkSize(byteArrayToLong(tmpLong));

            file.read(tmpInt);
            header.setAudioFormat(byteArrayToInt(tmpInt));

            file.read(tmpInt);
            header.setNumChannels(byteArrayToInt(tmpInt));

            file.read(tmpLong);
            header.setSampleRate(byteArrayToLong(tmpLong));

            file.read(tmpLong);
            header.setByteRate(byteArrayToLong(tmpLong));

            file.read(tmpInt);
            header.setBlockAlign(byteArrayToInt(tmpInt));

            file.read(tmpInt);
            header.setBitsPerSample(byteArrayToInt(tmpInt));

            String tmpDataChunkID = "" + (char)file.readByte() + (char)file.readByte() +
                    (char)file.readByte() + (char)file.readByte();
            header.setDataChunkID(tmpDataChunkID);

            file.read(tmpLong);
            header.setDataSize(byteArrayToLong(tmpLong));

            double[] data = getDataByteArray();
            header.setDataChunkDouble(data);
            file.close();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public double[] getDataByteArray() throws Exception {
        DataInputStream file = null;
        byte[] wavFile = null;
        int totalLength;
        int newLength;
        double[] dataMono;

        try {
            file = new DataInputStream(new FileInputStream(header.getPath()));
            wavFile = IOUtils.toByteArray(file);
            byte[] dataRaw = Arrays.copyOfRange(wavFile, 44, wavFile.length);
            header.setDataChunkBytes(dataRaw);
            totalLength = dataRaw.length;
            newLength = totalLength/4;
            dataMono = new double[newLength];

            for (int i = 0; 4*i+3 < totalLength; i++) {
                double left = ((dataRaw[4*i+1] & 0xff) << 8) | (dataRaw[4*i] & 0xff);
                double right = ((dataRaw[4*i+3] & 0xff) << 8) | (dataRaw[4*i+2] & 0xff);
                dataMono[i] = (left+right)/2.0;
            }
        } catch (Exception e) {
            throw new IOException();
        }
        return dataMono;
    }

//    public double[] getFromDataByteArray(int length) {
//        double[] data = new double[length];
//        double[] dataRaw = header.getDataChunkDouble();
//        for (int i = 0; i < length; i++) {
//            data[i] = dataRaw[i];
//        }
//        return data;
//    }

    public void getSpectro() throws Exception {
        try {
            double rawData[] = getDataByteArray();
//            double rawData[] = getFromDataByteArray(1024);
            int length = rawData.length;

            int windowSize = 2048;
            int overlapFactor = 8;
            int windowStep = windowSize / overlapFactor;

            double threshold = 1.0;

            int nX = (length - windowSize) / windowStep;
            int nY = windowSize/2 + 1;
            double[][] plotData = new double[nX][nY];

            double maxAmp = Double.MIN_VALUE;
            double minAmp = Double.MAX_VALUE;

            double ampSquare;
            double[] inputImg = new double[length];

            for (int i = 0; i < nX; i++) {
                Arrays.fill(inputImg, 0.0);
                double[] WS_array = FFT.fft(Arrays.copyOfRange(rawData, i * windowStep, i * windowStep + windowSize),
                        inputImg, true);
                for (int j = 0; j < nY; j++) {
                    ampSquare = (WS_array[2 * j] * WS_array[2 * j]) + (WS_array[2 * j + 1] * WS_array[2 * j + 1]);
                    if (ampSquare == 0.0) {
                        plotData[i][j] = ampSquare;
                    } else {
                        plotData[i][nY-j-1] = 10 * Math.log10(Math.max(ampSquare, threshold));
                    }

                    if (plotData[i][j] > maxAmp) {
                        maxAmp = plotData[i][j];
                    } else if (plotData[i][j] < minAmp) {
                        minAmp = plotData[i][j];
                    }
                }
            }

            double diff = maxAmp - minAmp;
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    plotData[i][j] = (plotData[i][j] - minAmp) / diff;
                }
            }

            BufferedImage spectroImg = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
            double ratio;
            for (int x = 0; x < nX; x++) {
                for (int y = 0; y < nY; y++) {
                    ratio = plotData[x][y];

                    Color newColor = getColor(1.0-ratio);
                    spectroImg.setRGB(x, y, newColor.getRGB());
                }
            }

            File outputImg = new File("spectro.png");
            ImageIO.write(spectroImg, "png", outputImg);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    public static Color getColor(double power) {
        double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
        double S = 1.0; // Saturation
        double B = 1.0; // Brightness

        return Color.getHSBColor((float)H, (float)S, (float)B);
    }

    public void saveWav(String wavName, byte[] dataTmp) {
        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(wavName + ".wav"));
            outFile.writeBytes(header.getRiffChunkID());
            outFile.write(intToByteArray((int)header.getRiffChunkSize()), 0, 4);
            outFile.writeBytes(header.getFormat());
            outFile.writeBytes(header.getFmtSubChunkID());
            outFile.write(intToByteArray((int)header.getFmtSubChunkSize()), 0, 4);
            outFile.write(shortToByteArray((short)header.getAudioFormat()), 0, 2);
            outFile.write(shortToByteArray((short)header.getNumChannels()), 0, 2);
            outFile.write(intToByteArray((int)header.getSampleRate()), 0,4);
            outFile.write(intToByteArray((int)header.getByteRate()), 0, 4);
            outFile.write(shortToByteArray((short)header.getBlockAlign()), 0, 2);
            outFile.write(shortToByteArray((short)header.getBitsPerSample()), 0 ,2);
            outFile.writeBytes(header.getDataChunkID());
            outFile.write(intToByteArray((int)header.getDataSize()), 0, 4);
            outFile.write(dataTmp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        for ( int shiftBy = 0; shiftBy < 32; shiftBy += 8 ) {
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