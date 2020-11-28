package useful.tempconv;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import useful.templates.GuiProject;

public class TempConv extends GuiProject {
  private String[] temperaturesList1 = {"Celsius", "Fahrenheit", "Kelvin"};
  private JComboBox<String> jcbTemperatures1 = new JComboBox<String>(temperaturesList1);
  private String[] temperaturesList2 = {"Celsius", "Fahrenheit", "Kelvin"};
  private JComboBox<String> jcbTemperatures2 = new JComboBox<String>(temperaturesList2);
  private JButton jbtnSwitch = new JButton("\u2194");
  private JTextField jtfFromTemperature = new JTextField();
  private JButton jbtnConvert = new JButton("\u2192");
  private JLabel jlblToTemperature = new JLabel();

  public TempConv() {
    jcbTemperatures1.setSelectedItem("Fahrenheit");
    jbtnConvert.setMnemonic('C');
    jbtnSwitch.setMnemonic('S');

    JPanel p = new JPanel(new GridLayout(2, 3, 5, 5));
    p.add(jcbTemperatures1);
    p.add(jbtnSwitch);
    p.add(jcbTemperatures2);
    p.add(jtfFromTemperature);
    p.add(jbtnConvert);
    p.add(jlblToTemperature);
    p.setBorder(new TitledBorder("Choose temperatures, enter temperature, and convert."));

    add(p, BorderLayout.CENTER);

    jbtnConvert.addActionListener(new ConvertListener());
    jbtnSwitch.addActionListener(new SwitchListener());
  }

  /** Handle the convert button. */
  private class ConvertListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        convertTemperature();
      } catch (NumberFormatException err) {
        jlblToTemperature.setText("ERROR");
      }
    }
  }

  private void convertTemperature() throws NumberFormatException {
    String fromTemperatureUnit = (String) jcbTemperatures1.getSelectedItem();
    String toTemperatureUnit = (String) jcbTemperatures2.getSelectedItem();
    double fromTemperature = Double.parseDouble(jtfFromTemperature.getText());

    switch (fromTemperatureUnit) {
      case "Celsius":
        switch (toTemperatureUnit) {
          case "Celsius":
            jlblToTemperature.setText(Double.toString(fromTemperature));
            break;
          case "Fahrenheit":
            jlblToTemperature.setText(Double.toString(celsiusToFahrenheit(fromTemperature)));
            break;
          case "Kelvin":
            jlblToTemperature.setText(Double.toString(celsiusToKelvin(fromTemperature)));
            break;
        }
        break;
      case "Fahrenheit":
        switch (toTemperatureUnit) {
          case "Celsius":
            jlblToTemperature.setText(Double.toString(fahrenheitToCelsius(fromTemperature)));
            break;
          case "Fahrenheit":
            jlblToTemperature.setText(Double.toString(fromTemperature));
            break;
          case "Kelvin":
            jlblToTemperature.setText(Double.toString(fahrenheitToKelvin(fromTemperature)));
            break;
        }
        break;
      case "Kelvin":
        switch (toTemperatureUnit) {
          case "Celsius":
            jlblToTemperature.setText(Double.toString(kelvinToCelsius(fromTemperature)));
            break;
          case "Fahrenheit":
            jlblToTemperature.setText(Double.toString(kelvinToFahrenheit(fromTemperature)));
            break;
          case "Kelvin":
            jlblToTemperature.setText(Double.toString(fromTemperature));
            break;
        }
        break;
    }
  }

  private class SwitchListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String tmp = (String) jcbTemperatures1.getSelectedItem();
      jcbTemperatures1.setSelectedItem(jcbTemperatures2.getSelectedItem());
      jcbTemperatures2.setSelectedItem(tmp);
    }
  }

  /** Main method. */
  public static void main(String[] args) {
    TempConv frame = new TempConv();
    frame.setSize(350, 110);
    // frame.pack();
    frame.setTitle("Temperature Converter");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
    return celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
  }

  public double kelvinToCelsius(double kelvin) {
    return kelvin - 273.15;
  }

  public double kelvinToFahrenheit(double kelvin) {
    return celsiusToFahrenheit(kelvinToCelsius(kelvin));
  }
}
