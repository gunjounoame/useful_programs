package useful.dayofweek;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import useful.templates.GuiProject;

public class DayOfWeek extends GuiProject {
  private Calendar calendar = new GregorianCalendar();
  private JSpinner jspnYear = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.YEAR),
      Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
  private JSpinner jspnMonth = new JSpinner(new SpinnerListModel(new String[] {"January",
      "February", "March", "April", "May", "June", "July", "August", "September", "October",
      "November", "December"}));
  private JSpinner jspnDay = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
  private JLabel jlblDayOfWeek = new JLabel();
  private JButton jbtComputeDayOfWeek = new JButton("Compute Day of Week");

  /** Add required Swing elements when constructed. */
  public DayOfWeek() {
    // Customize JSpinners
    jspnYear.setEditor(new JSpinner.NumberEditor(jspnYear, "#"));
    jspnMonth.setValue(parseMonthInt(calendar.get(Calendar.MONTH)));
    jspnDay.setValue(calendar.get(Calendar.DATE));
    
    // Add necessary Swing components
    JPanel p1 = new JPanel(new GridLayout(4, 2));
    p1.add(new JLabel("Year:"));
    p1.add(jspnYear);
    p1.add(new JLabel("Month:"));
    p1.add(jspnMonth);
    p1.add(new JLabel("Day:"));
    p1.add(jspnDay);
    p1.add(new JLabel("Day of week:"));
    p1.add(jlblDayOfWeek);
    p1.setBorder(new TitledBorder("Enter year, month, and day."));

    jbtComputeDayOfWeek.setMnemonic('C');
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    p2.add(jbtComputeDayOfWeek);

    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    // Add Listeners
    jbtComputeDayOfWeek.addActionListener(new ComputeListener());
  }

  private class ComputeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        int year = (int) jspnYear.getValue();
        int month = parseMonth(jspnMonth.getValue().toString());
        int dayOfMonth = (int) jspnDay.getValue();
        String dayOfWeek = calculateDayOfWeek(year, month, dayOfMonth);
        jlblDayOfWeek.setText(dayOfWeek);
      } catch (NumberFormatException err) {
        jlblDayOfWeek.setText("ERROR");
      }
    }
  }

  /**
   * Calculate the day of the week as a String based on arguments, or "ERROR" if an error occurs.
   */
  public static String calculateDayOfWeek(int year_, int month_, int dayOfMonth_) {
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

  /** Main method. */
  public static void main(String[] args) {
    DayOfWeek frame = new DayOfWeek();
    frame.setSize(200, 200);
    frame.setTitle("Day of week calculator");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
