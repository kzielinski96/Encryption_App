package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Encryption App");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}