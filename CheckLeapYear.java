package useful.leapyear;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import useful.GuiProject;

public class CheckLeapYear extends GuiProject {
  private JTextField jtfYear = new JTextField(); // TODO: Refactor this into a SpinnerNumberModel
  private JLabel jlblLeapYear = new JLabel();
  private JButton jbtCheck = new JButton("Check");

  public CheckLeapYear() {
    JPanel p1 = new JPanel(new GridLayout(2, 2));
    p1.add(new JLabel("Year:"));
    p1.add(jtfYear);
    p1.add(new JLabel("Leap Year?"));
    p1.add(jlblLeapYear);
    p1.setBorder(new TitledBorder("Enter year."));

    jbtCheck.setMnemonic('C');
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    p2.add(jbtCheck);

    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    jbtCheck.addActionListener(new ButtonListener());
  }

  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        checkLeapYear();
      } catch (NumberFormatException err) {
        jlblLeapYear.setText("ERROR");
      }
    }
  }

  private void checkLeapYear() throws NumberFormatException {
    int year = Short.parseShort(jtfYear.getText());
    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    jlblLeapYear.setText(isLeapYear ? "Yes" : "No");
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
