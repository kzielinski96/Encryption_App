//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class WavHeader {
//
//    // == private fields ==
//    private byte[] chunkID = new byte[4];
//    private int chunkSize;
//    private byte[] format = new byte[4];
//    private byte[] subChunk1ID = new byte[4];
//    private int subChunk1Size;
//    private short audioFormat;
//    private short numChannels;
//    private int sampleRate;
//    private int byteRate;
//    private short blockAlign;
//    private short bitsPerSample;
//    private byte[] subChunk2ID = new byte[4];
//    private int subChunk2Size;
//    private List<Short> dataChunk = new ArrayList<>();
//
//    // == pubic methods ==
//    public byte[] getChunkID() {
//        return chunkID;
//    }
//
//    public void setChunkID(byte[] chunkID) {
//        this.chunkID = chunkID;
//    }
//
//    public int getChunkSize() {
//        return chunkSize;
//    }
//
//    public void setChunkSize(int chunkSize) {
//        this.chunkSize = chunkSize;
//    }
//
//    public byte[] getFormat() {
//        return format;
//    }
//
//    public void setFormat(byte[] format) {
//        this.format = format;
//    }
//
//    public byte[] getSubChunk1ID() {
//        return subChunk1ID;
//    }
//
//    public void setSubChunk1ID(byte[] subChunk1ID) {
//        this.subChunk1ID = subChunk1ID;
//    }
//
//    public int getSubChunk1Size() {
//        return subChunk1Size;
//    }
//
//    public void setSubChunk1Size(int subChunk1Size) {
//        this.subChunk1Size = subChunk1Size;
//    }
//
//    public short getAudioFormat() {
//        return audioFormat;
//    }
//
//    public void setAudioFormat(short audioFormat) {
//        this.audioFormat = audioFormat;
//    }
//
//    public short getNumChannels() {
//        return numChannels;
//    }
//
//    public void setNumChannels(short numChannels) {
//        this.numChannels = numChannels;
//    }
//
//    public int getSampleRate() {
//        return sampleRate;
//    }
//
//    public void setSampleRate(int sampleRate) {
//        this.sampleRate = sampleRate;
//    }
//
//    public int getByteRate() {
//        return byteRate;
//    }
//
//    public void setByteRate(int byteRate) {
//        this.byteRate = byteRate;
//    }
//
//    public short getBlockAlign() {
//        return blockAlign;
//    }
//
//    public void setBlockAlign(short blockAlign) {
//        this.blockAlign = blockAlign;
//    }
//
//    public short getBitsPerSample() {
//        return bitsPerSample;
//    }
//
//    public void setBitsPerSample(short bitsPerSample) {
//        this.bitsPerSample = bitsPerSample;
//    }
//
//    public byte[] getSubChunk2ID() {
//        return subChunk2ID;
//    }
//
//    public void setSubChunk2ID(byte[] subChunk2ID) {
//        this.subChunk2ID = subChunk2ID;
//    }
//
//    public int getSubChunk2Size() {
//        return subChunk2Size;
//    }
//
//    public void setSubChunk2Size(int subChunk2Size) {
//        this.subChunk2Size = subChunk2Size;
//    }
//
//    public List<Short> getDataChunk() {
//        return dataChunk;
//    }
//
//    public void setDataChunk(List<Short> dataChunk) {
//        this.dataChunk = dataChunk;
//    }
//
//    public String toString(String header) {
//        return "WavHeader: " + header +"\n" +
//                "chunkSize=" + this.getChunkSize() +
//                "\nformat=" + new String(this.getFormat()) +
//                "\nfmtSubChunk=" + new String(this.getSubChunk1ID()) +
//                "\nfmtSubChunkSize=" + this.getSubChunk1Size() +
//                "\naudioFormat=" + this.getAudioFormat() +
//                "\nnumChannels=" + this.getNumChannels() +
//                "\nsampleRate=" + this.getSampleRate() +
//                "\nbyteRate=" + this.getByteRate() +
//                "\nblockAlign=" + this.getBlockAlign() +
//                "\nbitsPerSample=" + this.getBitsPerSample() +
//                "\ndataSubChunk=" + new String(this.getSubChunk2ID()) +
//                "\ndataSubChunkSize=" + this.getSubChunk2Size() +
//                "\nwavData= " + this.getDataChunk();
//    }
//}
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
    public byte[] dataChunk;

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

    public byte[] getDataChunk() {
        return dataChunk;
    }

    public void setDataChunk(byte[] dataChunk) {
        this.dataChunk = dataChunk;
    }

    public WavHeader(String path) {
        this.path = path;
    }

    public WavHeader() {
    }
}