package useful.tempconv;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import useful.templates.GuiProject;

public class TempConv extends GuiProject {
  private JComboBox<String> jcbTemperatures1 = new JComboBox<String>(new String[] {"Celsius",
      "Fahrenheit", "Kelvin"});
  private JComboBox<String> jcbTemperatures2 = new JComboBox<String>(new String[] {"Celsius",
      "Fahrenheit", "Kelvin"});
  private JButton jbtSwitch = new JButton("\u2194");
  private JTextField jtfFromTemperature = new JTextField();
  private JButton jbtConvert = new JButton("\u2192");
  private JLabel jlblToTemperature = new JLabel();

  /** Add required Swing elements when constructed. */
  public TempConv() {
    // Add necessary Swing components
    jcbTemperatures1.setSelectedItem("Fahrenheit");
    jbtConvert.setMnemonic('C');
    jbtSwitch.setMnemonic('S');

    JPanel p = new JPanel(new GridLayout(2, 3, 5, 5));
    p.add(jcbTemperatures1);
    p.add(jbtSwitch);
    p.add(jcbTemperatures2);
    p.add(jtfFromTemperature);
    p.add(jbtConvert);
    p.add(jlblToTemperature);
    p.setBorder(new TitledBorder("Choose temperatures, enter temperature, and convert."));

    add(p, BorderLayout.CENTER);

    // Add Listeners
    jbtConvert.addActionListener(new ConvertListener());
    jbtSwitch.addActionListener(new SwitchListener());
  }

  /** Listen for when jbtConvert is pressed and update jlblToTemperature. */
  private class ConvertListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
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
      } catch (NumberFormatException err) {
        jlblToTemperature.setText("ERROR");
      }
    }
  }

  /** Listen for when jbtSwitch is pressed and switch the temperature units. */
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
    return (fahrenheit + 459.67) * (5.0 / 9);
  }

  public double kelvinToCelsius(double kelvin) {
    return kelvin - 273.15;
  }

  public double kelvinToFahrenheit(double kelvin) {
    return kelvin * (9.0 / 5) - 459.67;
  }
}
