package com.pyrerune.ClamShell;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class App extends Application {
    public int maxCol = 20;
    public int maxRow = 30;
    Screens screens = new Screens();
    @Override
    public void start(Stage stage) throws InterruptedException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("README"));
        try {
            StringBuilder sb = new StringBuilder();
            String lastLine = "";
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;

            }
            System.out.println(lastLine);
            if(!lastLine.equals("Do You Agree: YES")) {
                System.exit(0);

            } else {

                screens.mainScr.Start(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    public static void main(String args[]){
        launch(args);
    }
}