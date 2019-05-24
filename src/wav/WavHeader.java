package wav;

import java.util.ArrayList;

public class WavHeader {
    private String path;
    private String riffChunkID;
    private long riffChunkSize;
    private String format;
    private String fmtSubChunkID;
    private long fmtSubChunkSize;
    private int audioFormat;
    private long numChannels;
    private long sampleRate;
    private long byteRate;
    private int blockAlign;
    private int bitsPerSample;
    private String dataChunkID;
    private long dataSize;
    public double[] dataChunkDouble;
    public byte[] dataChunkBytes;

    // == constructors ==
    public WavHeader(String path) {
        this.path = path;
    }

    public WavHeader() {
    }

    // == public methods ==
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRiffChunkID() {
        return riffChunkID;
    }

    public void setRiffChunkID(String riffChunkID) {
        this.riffChunkID = riffChunkID;
    }

    public long getRiffChunkSize() {
        return riffChunkSize;
    }

    public void setRiffChunkSize(long riffChunkSize) {
        this.riffChunkSize = riffChunkSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFmtSubChunkID() {
        return fmtSubChunkID;
    }

    public void setFmtSubChunkID(String fmtSubChunkID) {
        this.fmtSubChunkID = fmtSubChunkID;
    }

    public long getFmtSubChunkSize() {
        return fmtSubChunkSize;
    }

    public void setFmtSubChunkSize(long fmtSubChunkSize) {
        this.fmtSubChunkSize = fmtSubChunkSize;
    }

    public int getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(int audioFormat) {
        this.audioFormat = audioFormat;
    }

    public long getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(long numChannels) {
        this.numChannels = numChannels;
    }

    public long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(long sampleRate) {
        this.sampleRate = sampleRate;
    }

    public long getByteRate() {
        return byteRate;
    }

    public void setByteRate(long byteRate) {
        this.byteRate = byteRate;
    }

    public int getBlockAlign() {
        return blockAlign;
    }

    public void setBlockAlign(int blockAlign) {
        this.blockAlign = blockAlign;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public void setBitsPerSample(int bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public String getDataChunkID() {
        return dataChunkID;
    }

    public void setDataChunkID(String dataChunkID) {
        this.dataChunkID = dataChunkID;
    }

    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public double[] getDataChunkDouble() {
        return dataChunkDouble;
    }

    public void setDataChunkDouble(double[] dataChunkDouble) {
        this.dataChunkDouble = dataChunkDouble;
    }

    public byte[] getDataChunkBytes() {
        return dataChunkBytes;
    }

    public void setDataChunkBytes(byte[] dataChunkBytes) {
        this.dataChunkBytes = dataChunkBytes;
    }
}