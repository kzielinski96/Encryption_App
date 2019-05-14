import java.util.Arrays;

public class WavHeader {

    // == private fields ==
    private byte[] chunkID = new byte[4];
    private int chunkSize;
    private byte[] format = new byte[4];
    private byte[] subChunk1ID = new byte[4];
    private int subChunk1Size;
    private short audioFormat;
    private short numChannels;
    private int sampleRate;
    private int byteRate;
    private short blockAlign;
    private short bitsPerSample;
    private byte[] subChunk2ID = new byte[4];
    private int subChunk2Size;

    // == pubic methods ==
    public byte[] getChunkID() {
        return chunkID;
    }

    public void setChunkID(byte[] chunkID) {
        this.chunkID = chunkID;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] getFormat() {
        return format;
    }

    public void setFormat(byte[] format) {
        this.format = format;
    }

    public byte[] getSubChunk1ID() {
        return subChunk1ID;
    }

    public void setSubChunk1ID(byte[] subChunk1ID) {
        this.subChunk1ID = subChunk1ID;
    }

    public int getSubChunk1Size() {
        return subChunk1Size;
    }

    public void setSubChunk1Size(int subChunk1Size) {
        this.subChunk1Size = subChunk1Size;
    }

    public short getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(short audioFormat) {
        this.audioFormat = audioFormat;
    }

    public short getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(short numChannels) {
        this.numChannels = numChannels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getByteRate() {
        return byteRate;
    }

    public void setByteRate(int byteRate) {
        this.byteRate = byteRate;
    }

    public short getBlockAlign() {
        return blockAlign;
    }

    public void setBlockAlign(short blockAlign) {
        this.blockAlign = blockAlign;
    }

    public short getBitsPerSample() {
        return bitsPerSample;
    }

    public void setBitsPerSample(short bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public byte[] getSubChunk2ID() {
        return subChunk2ID;
    }

    public void setSubChunk2ID(byte[] subChunk2ID) {
        this.subChunk2ID = subChunk2ID;
    }

    public int getSubChunk2Size() {
        return subChunk2Size;
    }

    public void setSubChunk2Size(int subChunk2Size) {
        this.subChunk2Size = subChunk2Size;
    }

    @Override
    public String toString() {
        return "WavHeader{" +
                "chunkID=" + Arrays.toString(chunkID) +
                ", chunkSize=" + chunkSize +
                ", format=" + Arrays.toString(format) +
                ", subChunk1ID=" + Arrays.toString(subChunk1ID) +
                ", subChunk1Size=" + subChunk1Size +
                ", audioFormat=" + audioFormat +
                ", numChannels=" + numChannels +
                ", sampleRate=" + sampleRate +
                ", byteRate=" + byteRate +
                ", blockAlign=" + blockAlign +
                ", bitsPerSample=" + bitsPerSample +
                ", subChunk2ID=" + Arrays.toString(subChunk2ID) +
                ", subChunk2Size=" + subChunk2Size +
                '}';
    }
}
