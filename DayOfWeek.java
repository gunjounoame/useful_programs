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

  public DayOfWeek() {
    jspnYear.setEditor(new JSpinner.NumberEditor(jspnYear, "#"));
    
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

    jbtComputeDayOfWeek.addActionListener(new ButtonListener());
  }

  /** Calculate the day of the week. */
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        updateDayOfWeek();
      } catch (NumberFormatException err) {
        jlblDayOfWeek.setText("ERROR");
      }
    }
  }

  private void updateDayOfWeek() throws NumberFormatException {
    int year = (int)jspnYear.getValue();
    int month = parseMonth(jspnMonth.getValue().toString());
    int dayOfMonth = (int)jspnDay.getValue();
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
        jlblDayOfWeek.setText("Saturday");
        break;
      case 1:
        jlblDayOfWeek.setText("Sunday");
        break;
      case 2:
        jlblDayOfWeek.setText("Monday");
        break;
      case 3:
        jlblDayOfWeek.setText("Tuesday");
        break;
      case 4:
        jlblDayOfWeek.setText("Wednesday");
        break;
      case 5:
        jlblDayOfWeek.setText("Thursday");
        break;
      case 6:
        jlblDayOfWeek.setText("Friday");
    }
  }

  private int parseMonth(String monthString) {
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
