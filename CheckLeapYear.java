package useful.leapyear;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckLeapYear extends Application {
  private Calendar calendar = new GregorianCalendar();

  @Override
  public void start(Stage primaryStage) {
    // Create and configure required JavaFX nodes
    Spinner spnYear = new Spinner(Integer.MIN_VALUE, Integer.MAX_VALUE,
        calendar.get(Calendar.YEAR), 1);
    Label lblIsLeapYear = new Label(checkLeapYear((int) spnYear.getValue()) ? "Yes" : "No");
    Button btnCheck = new Button("Check");

    // Create panes for nodes
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12, 12, 12, 12));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    // Add nodes to panes
    pane.add(new Label("Year:"), 0, 0);
    pane.add(spnYear, 1, 0);
    pane.add(new Label("Leap Year?"), 0, 1);
    pane.add(lblIsLeapYear, 1, 1);
    pane.add(btnCheck, 1, 2);
    GridPane.setHalignment(btnCheck, HPos.RIGHT);

    // Register handlers
    btnCheck.setOnAction(e -> {
      boolean isLeapYear = checkLeapYear((int) spnYear.getValue());
      lblIsLeapYear.setText(isLeapYear ? "Yes" : "No");
    });
    
    // Create a scene and place it in primaryStage
    Scene scene = new Scene(pane, 300, 150);
    primaryStage.setTitle("Leap Year Checker");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /** Check if the given year is a leap year. */
  private static boolean checkLeapYear(int year) {
    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    return isLeapYear;
  }
}
