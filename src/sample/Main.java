package sample;//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import org.apache.commons.math3.complex.Complex;
//import wav.WavHeader;
//import wav.WavHeaderReader;
//
//import java.io.*;
//
//public class sample.Main {
//    public static void main(String[] args) throws Exception {
//        try {
//            WavHeader header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/Yamaha-V50-Rock-Beat-120bpm.wav");
//            WavHeaderReader reader = new WavHeaderReader(header);
//            reader.read();
//            for (int i = 0; i < reader.freqs.length; i++) {
//                Complex complex = reader.freqs[i];
//                System.out.println("Sample " + (i+1));
//                System.out.print("Re: " + complex.getReal());
//                System.out.print(", Im: " + complex.getImaginary() + "\n");
//            }
//        } catch (Exception e) {
//            throw new IOException(e);
//        }
//    }
//}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;
import wav.WavHeader;
import wav.WavHeaderReader;

import java.io.IOException;


public class Main extends Application {

    public WavHeader header;
    public WavHeaderReader reader;

    @Override public void start(Stage stage) throws Exception {
        try {
            header = new WavHeader("/home/kub/Pulpit/platformy_java/wav samples/Yamaha-V50-Rock-Beat-120bpm.wav");
            reader = new WavHeaderReader(header);

            stage.setTitle("Spectrogram ?");
            //defining the axes
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Number of Month");
            //creating the chart
            final AreaChart<Number,Number> lineChart =
                    new AreaChart<Number,Number>(xAxis,yAxis);

            XYChart.Series series = new XYChart.Series();
            reader.read();

            for (int i = 0; i < reader.getFreqs().length; i++) {
                Complex complex = reader.freqs[i];
                int re = (int) complex.getReal();
                int im = (int) complex.getImaginary();

                series.getData().add(new XYChart.Data(re, im));
            }
            Scene scene  = new Scene(lineChart,800,600);
            lineChart.getData().add(series);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new IOException(e);
        }


    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}