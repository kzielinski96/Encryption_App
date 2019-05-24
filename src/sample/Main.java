package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wav.WavHeader;
import wav.WavHeaderReader;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller controller = new FXMLLoader(getClass().getResource("sample.fxml")).getController();
//        controller.setStage(stage);
        stage.setTitle("Encryption App");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
//        try {
//            WavHeader header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/Yamaha-V50-Rock-Beat-120bpm.wav");
//            WavHeaderReader reader = new WavHeaderReader(header);
//            reader.read();
//        } catch (Exception e) {
//            throw new IOException(e);
//        }
        launch(args);
    }
}