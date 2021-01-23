package useful.tempconv;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TempConv extends Application {
  @Override
  public void start(Stage primaryStage) {
    // Create and configure required JavaFX nodes
    ComboBox<String> cmbTemperatures1 = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"°C", "°F", "K"}));
    cmbTemperatures1.setValue("°F");
    ComboBox<String> cmbTemperatures2 = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"°C", "°F", "K"}));
    cmbTemperatures2.setValue("°C");
    TextField txfFromTemperature = new TextField();
    txfFromTemperature.setAlignment(Pos.CENTER_RIGHT);
    Button btnConvert = new Button("Convert");
    Label lblToTemperature = new Label();

    // Create panes for nodes
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12, 12, 12, 12));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    // Add nodes to panes
    pane.add(new Label("Starting Temperature"), 0, 0);
    pane.add(txfFromTemperature, 1, 0);
    pane.add(cmbTemperatures1, 2, 0);
    pane.add(new Label("Converted Temperature"), 0, 1);
    pane.add(lblToTemperature, 1, 1);
    GridPane.setHalignment(lblToTemperature, HPos.RIGHT);
    pane.add(cmbTemperatures2, 2, 1);
    pane.add(btnConvert, 1, 2, 2, 1);
    GridPane.setHalignment(btnConvert, HPos.RIGHT);

    // Register handlers
    btnConvert.setOnAction(e -> {
      try {
        String fromTemperatureUnit = (String) cmbTemperatures1.getValue();
        String toTemperatureUnit = (String) cmbTemperatures2.getValue();
        double fromTemperature = Double.parseDouble(txfFromTemperature.getText());

        switch (fromTemperatureUnit) {
          case "°C":
            switch (toTemperatureUnit) {
              case "°C":
                lblToTemperature.setText(Double.toString(fromTemperature));
                break;
              case "°F":
                lblToTemperature.setText(Double.toString(celsiusToFahrenheit(fromTemperature)));
                break;
              case "K":
                lblToTemperature.setText(Double.toString(celsiusToKelvin(fromTemperature)));
                break;
            }
            break;
          case "°F":
            switch (toTemperatureUnit) {
              case "°C":
                lblToTemperature.setText(Double.toString(fahrenheitToCelsius(fromTemperature)));
                break;
              case "°F":
                lblToTemperature.setText(Double.toString(fromTemperature));
                break;
              case "K":
                lblToTemperature.setText(Double.toString(fahrenheitToKelvin(fromTemperature)));
                break;
            }
            break;
          case "K":
            switch (toTemperatureUnit) {
              case "°C":
                lblToTemperature.setText(Double.toString(kelvinToCelsius(fromTemperature)));
                break;
              case "°F":
                lblToTemperature.setText(Double.toString(kelvinToFahrenheit(fromTemperature)));
                break;
              case "K":
                lblToTemperature.setText(Double.toString(fromTemperature));
                break;
            }
            break;
        }
      } catch (NumberFormatException err) {
        lblToTemperature.setText("ERROR");
      }
    });

    // Create a scene and place it in primaryStage
    Scene scene = new Scene(pane, 450, 120);
    primaryStage.setTitle("Temperature Converter");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  // Conversions for the application.

  public double celsiusToFahrenheit(double celsius) {
    return celsius * (9.0 / 5) + 32;
  }

  public double celsiusToKelvin(double celsius) {
    return celsius + 273.15;
  }

  public double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) * (5.0 / 9);
  }

  public double fahrenheitToKelvin(double fahrenheit) {
    return (fahrenheit + 459.67) * (5.0 / 9);
  }

  public double kelvinToCelsius(double kelvin) {
    return kelvin - 273.15;
  }

  public double kelvinToFahrenheit(double kelvin) {
    return kelvin * (9.0 / 5) - 459.67;
  }
}