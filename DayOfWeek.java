package useful.dayofweek;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DayOfWeek extends Application {
  private Calendar calendar = new GregorianCalendar();
  private String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
      "August", "September", "October", "November", "December"};

  @Override
  public void start(Stage primaryStage) {
    // Create and configure required JavaFX nodes
    Spinner spnYear = new Spinner(Integer.MIN_VALUE, Integer.MAX_VALUE,
        calendar.get(Calendar.YEAR), 1);
    Spinner<String> spnMonth = new Spinner<String>(FXCollections.observableArrayList(monthNames));
    spnMonth.getValueFactory().setValue(parseMonthInt(calendar.get(Calendar.MONTH)));
    Spinner spnDay = new Spinner(1, 31, calendar.get(Calendar.DATE), 1);
    Label lblDayOfWeek = new Label(calculateDayOfWeek(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)));
    Button btnComputeDayOfWeek = new Button("Compute");

    // Create panes for nodes
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12, 12, 12, 12));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    // Add nodes to panes
    pane.add(new Label("Year:"), 0, 0);
    pane.add(spnYear, 1, 0);
    pane.add(new Label("Month:"), 0, 1);
    pane.add(spnMonth, 1, 1);
    pane.add(new Label("Day:"), 0, 2);
    pane.add(spnDay, 1, 2);
    pane.add(new Label("Day of week:"), 0, 3);
    pane.add(lblDayOfWeek, 1, 3);
    pane.add(btnComputeDayOfWeek, 1, 4);
    GridPane.setHalignment(btnComputeDayOfWeek, HPos.RIGHT);

    // Register handlers
    btnComputeDayOfWeek.setOnAction(e -> {
      int year = (int) spnYear.getValue();
      int month = parseMonth(spnMonth.getValue().toString());
      int dayOfMonth = (int) spnDay.getValue();
      String dayOfWeek = calculateDayOfWeek(year, month, dayOfMonth);
      lblDayOfWeek.setText(dayOfWeek);
    });

    // Create a scene and place it in primaryStage
    Scene scene = new Scene(pane, 320, 200);
    primaryStage.setTitle("Day of week calculator");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /** 
   * Calculate the day of the week as a String based on arguments, or "ERROR" if an error occurs.
   */
  private static String calculateDayOfWeek(int year_, int month_, int dayOfMonth_) {
    int year = year_;
    int month = month_;
    int dayOfMonth = dayOfMonth_;
    switch (month) {
      case 1:
        month = 13;
        year--;
        break;
      case 2:
        month = 14;
        year--;
    }
    int century = year / 100;
    int yearOfCentury = year % 100;
    int dayOfWeek = (dayOfMonth + (13 * (month + 1)) / 5 + yearOfCentury + (yearOfCentury) / 4
        + century / 4 + 5 * century) % 7;
    
    switch (dayOfWeek) {
      case 0:
        return "Saturday";
      case 1:
        return "Sunday";
      case 2:
        return "Monday";
      case 3:
        return "Tuesday";
      case 4:
        return "Wednesday";
      case 5:
        return "Thursday";
      case 6:
        return "Friday";
      default:
        return "ERROR";
    }
  }

  /** Return the corresponding Integer to the month, or 0 if an error occurs. */
  public static int parseMonth(String monthString) {
    switch (monthString) {
      case "January":
        return 1;
      case "February":
        return 2;
      case "March":
        return 3;
      case "April":
        return 4;
      case "May":
        return 5;
      case "June":
        return 6;
      case "July":
        return 7;
      case "August":
        return 8;
      case "September":
        return 9;
      case "October":
        return 10;
      case "November":
        return 11;
      case "December":
        return 12;
      default:
        return 0;
    }
  }

  /** 
   * Return the corresponding month name as a String to the month as an Integer, or "January" if an
   * error occurs.
   */
  public static String parseMonthInt(int monthInt) {
    switch (monthInt) {
      case 0:
        return "January";
      case 1:
        return "February";
      case 2:
        return "March";
      case 3:
        return "April";
      case 4:
        return "May";
      case 5:
        return "June";
      case 6:
        return "July";
      case 7:
        return "August";
      case 8:
        return "September";
      case 9:
        return "October";
      case 10:
        return "November";
      case 11:
        return "December";
      default:
        return "January";
    }
  }
}
