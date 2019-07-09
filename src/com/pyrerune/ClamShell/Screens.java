package com.pyrerune.ClamShell;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Screens {
    public Loading loadingScr = new Loading();
    public Main mainScr = new Main();
}
class Loading {
    public void Start(Stage stage) {
        Text title = new Text("ClamShell\n\n" + "An Unofficial ClamAv GUI");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(Color.LIGHTBLUE);
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(title, 0, 0);
        Scene scene = new Scene(gridPane, Color.BLACK);
        stage.setScene(scene);
        stage.show();

    }

}
class Main {

    public void Start(Stage stage) {
        CMDExec cmdExec = new CMDExec();
        Label title = new Label("       CLAMSHELL\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        Label label = new Label("           Directory Name");
        Label spacer1 = new Label("        ");
        Label spacer2 = new Label("\n\n\n\n\n\n\n\n\n\n\n\n");

        Label spacer = new Label("                                    ");
        Button Run = new Button("Run");
        Button Exit = new Button("Exit");
        TextField dir = new TextField();
        dir.setAlignment(Pos.CENTER);
        Exit.setAlignment(Pos.BOTTOM_RIGHT);
        Run.setAlignment(Pos.BOTTOM_LEFT);
        Run.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage.close();
                String dirString;
                dirString = dir.getCharacters().toString();
                stage.close();

                cmdExec.scan(dirString, stage);

            }
        });
        Exit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
                System.exit(0);
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.BOTTOM_LEFT);
        gridPane.add(Run, 0, 4);
        gridPane.add(spacer, 1, 4);
        gridPane.add(Exit, 2, 4);
        gridPane.add(spacer2, 0, 3);
        gridPane.add(spacer1, 0, 2);
        gridPane.add(dir, 1, 2);
        gridPane.add(title, 1, 0);
        gridPane.add(label, 1, 1);
        title.setStyle("-fx-font: title bold 20px 'serif' ");
        Run.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        Exit.setStyle("-fx-background-color: #e80202; -fx-text-fill: #ffffff; ");
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}


class CMDExec {
    public void scan(String dir, Stage stage) {
        try{
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("clamscan " + dir);
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            String fullString = "";
            Out out = new Out();

            while ((line = b.readLine()) != null) {
                //System.out.println(line);
                fullString += line + ";";

            }
            out.open(stage, fullString);
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Out {
    public void open(Stage stage, String lines) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        Text text = new Text(10, 10, "  ");

        String full = null;
        String [] arr = lines.split(";");
        for(int x = 0; x < arr.length; x++) {
            full = full + "\n";
            full = full + arr[x];
        }
        arr = full.split("----------- SCAN SUMMARY -----------");
        //System.out.println(arr[1]);
        //System.out.println(full);
        text.setText(arr[1]);
        root.getChildren().add(text);
        stage.setScene(scene);


        stage.show();

    }

}