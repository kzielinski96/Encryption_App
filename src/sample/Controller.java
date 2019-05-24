package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wav.WavHeader;
import wav.WavHeaderReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    FileChooser fileChooser = new FileChooser();
    WavHeader header;
    WavHeaderReader reader;
    int width;
    int height;
    PixelReader pixelReader;
    PixelWriter pixelWriter;
    Image spectro;

    @FXML
    private Button openBtn;

    @FXML
    private ImageView imageView = null;
    @FXML
    Label fileNameLabel = new Label();
    @FXML
    Label chunkIdLabel = new Label();
    @FXML
    Label chunkSizeLabel = new Label();
    @FXML
    Label formatLabel = new Label();
    @FXML
    Label subChunk1IdLabel = new Label();
    @FXML
    Label subChunk1SizeLabel = new Label();
    @FXML
    Label audioFormatLabel = new Label();
    @FXML
    Label numChannelsLabel = new Label();
    @FXML
    Label sampleRateLabel = new Label();
    @FXML
    Label byteRateLabel = new Label();
    @FXML
    Label blockAlignLabel = new Label();
    @FXML
    Label bitsPerSampleLabel = new Label();
    @FXML
    Label subChunk2IdLabel = new Label();
    @FXML
    Label subChunk2SizeLabel = new Label();

    public void initialize() throws FileNotFoundException, IOException {

        openBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file = fileChooser.showOpenDialog(new Stage());
                try {
                    header = new WavHeader(file.getPath());
                    reader = new WavHeaderReader(header);
                    reader.read();

                    spectro = new Image(new FileInputStream("/home/kub/Pulpit/platformy_java/Encryption_App/spectro.png"));
                    width = (int)spectro.getWidth();
                    height = (int)spectro.getHeight();

                    WritableImage writableImage = new WritableImage(width,height);
                    pixelReader = spectro.getPixelReader();
                    pixelWriter = writableImage.getPixelWriter();

                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            Color color = pixelReader.getColor(x,y);
                            pixelWriter.setColor(x,y,color.darker());
                        }
                    }

                    imageView.setImage(writableImage);
                    imageView.setFitWidth(width/2);
                    imageView.setFitHeight(height/2);

                    fileNameLabel.setText("File Name: " + file.getName());
                    fileNameLabel.setWrapText(true);
                    chunkIdLabel.setText("ChunkID: " + header.getRiffChunkID());
                    chunkSizeLabel.setText("ChunkSize: " + header.getDataSize());
                    formatLabel.setText("Format: " + header.getFormat());
                    subChunk1IdLabel.setText("SubChunk1ID: "  + header.getFmtSubChunkID());
                    subChunk1SizeLabel.setText("SubChunk1Size: "  + header.getFmtSubChunkSize());
                    audioFormatLabel.setText("AudioFormat: " + header.getAudioFormat());
                    numChannelsLabel.setText("NumChannels: " + header.getNumChannels());
                    sampleRateLabel.setText("SampleRate: " + header.getSampleRate());
                    byteRateLabel.setText("ByteRate: " + header.getByteRate());
                    blockAlignLabel.setText("BlockAling: " + header.getBlockAlign());
                    bitsPerSampleLabel.setText("BitsPerSample: " + header.getBitsPerSample());
                    subChunk2IdLabel.setText("SubChunk2ID: " + header.getDataChunkID());
                    subChunk2SizeLabel.setText("SubChunk2Size: " + header.getDataSize());

                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        });
    }
}
