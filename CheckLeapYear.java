package useful.leapyear;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import useful.templates.GuiProject;

public class CheckLeapYear extends GuiProject {
  private Calendar calendar = new GregorianCalendar();
  private JSpinner jspnYear = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.YEAR),
      Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
  private JLabel jlblLeapYear = new JLabel();
  private JButton jbtCheck = new JButton("Check");

  /** Add required Swing elements when constructed. */
  public CheckLeapYear() {
    // Customize JSpinners
    jspnYear.setEditor(new JSpinner.NumberEditor(jspnYear, "#"));
    
    // Add necessary Swing components
    JPanel p1 = new JPanel(new GridLayout(2, 2));
    p1.add(new JLabel("Year:"));
    p1.add(jspnYear);
    p1.add(new JLabel("Leap Year?"));
    p1.add(jlblLeapYear);
    p1.setBorder(new TitledBorder("Enter year."));

    jbtCheck.setMnemonic('C');
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    p2.add(jbtCheck);

    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    // Add Listeners
    jbtCheck.addActionListener(new CheckListener());
  }

  /** Listen for when jbtCheck is pressed and update jlblLeapYear. */
  private class CheckListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        boolean isLeapYear = checkLeapYear((int)jspnYear.getValue());
        jlblLeapYear.setText(isLeapYear ? "Yes" : "No");
      } catch (NumberFormatException err) {
        jlblLeapYear.setText("ERROR");
      }
    }
  }

  /** Check if the given year is a leap year. */
  public static boolean checkLeapYear(int year) {
    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    return isLeapYear;
  }

  /** Main method. */
  public static void main(String[] args) {
    CheckLeapYear frame = new CheckLeapYear();
    frame.setSize(200, 150);
    frame.setTitle("Leap Year Checker");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
