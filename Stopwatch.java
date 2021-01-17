package useful.stopwatch;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Stopwatch extends Application {
  boolean start = true;
  long startTime;
  long lastTime;
  long lapNum = 1;
  
  @Override
  public void start(Stage primaryStage) {
    // Create and configure required JavaFX nodes
    TextArea txaOutput = new TextArea();
    txaOutput.setPrefColumnCount(20);
    txaOutput.setEditable(false);
    Button btnReset = new Button("Reset");
    Button btnLap = new Button("Start");

    // Create panes for nodes
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12, 12, 12, 12));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    VBox buttons = new VBox();
    buttons.setAlignment(Pos.CENTER);
    buttons.setPadding(new Insets(12, 12, 12, 12));

    // Add nodes to panes
    VBox.setMargin(btnLap, new Insets(0, 0, 15, 0));
    buttons.getChildren().addAll(btnLap);
    VBox.setMargin(btnReset, new Insets(0, 0, 15, 0));
    buttons.getChildren().addAll(btnReset);
    
    pane.add(txaOutput, 0, 0);
    pane.add(buttons, 1, 0);

    // Register handlers
    btnLap.setOnAction(e -> {
      if (start) {
        startTime = System.currentTimeMillis();
        lastTime = startTime;
        btnLap.setText("Lap");
        start = false;
      } else {
        long lapTime = System.currentTimeMillis() - lastTime;
        long totalTime = System.currentTimeMillis() - startTime;
        txaOutput.appendText(String.format("Lap #%d: %f (%f)\n", lapNum, lapTime / 1000.0, totalTime / 1000.0));
        lapNum++;
        lastTime = System.currentTimeMillis();
      }
    });

    btnReset.setOnAction(e -> {
      startTime = 0;
      lastTime = 0;
      lapNum = 1;
      txaOutput.setText("");
      btnLap.setText("Start");
      start = true;
    });

    // Create a scene and place it in primaryStage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("Stopwatch");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}