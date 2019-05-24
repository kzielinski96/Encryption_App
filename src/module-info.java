module Encryption.App {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.commons.io;
    requires java.desktop;
    requires javafx.swing;

    exports sample;
    opens sample;
}