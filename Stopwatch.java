package useful.stopwatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import useful.templates.GuiProject;

public class Stopwatch extends GuiProject {
  private JTextArea jtaOutput = new JTextArea(33, 100);
  private JButton jbtReset = new JButton("Reset");
  private JButton jbtLap = new JButton("Start");
  private boolean start = true;
  private long startTime;
  private long lastTime;
  private long lapNum = 1;
  
  /** Add required Swing elements when constructed. */
  public Stopwatch() {
    // Add necessary Swing components
    JPanel p1 = new JPanel(new BorderLayout());
    jtaOutput.setEditable(false);
    p1.add(new JScrollPane(jtaOutput), BorderLayout.CENTER);

    JPanel p2 = new JPanel(new GridLayout(1, 2, 25, 25));
    jbtReset.setMnemonic('R');
    jbtLap.setMnemonic('S');
    p2.add(jbtReset);
    p2.add(jbtLap);

    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    // Add Listeners
    jbtLap.addActionListener(new LapListener());
    jbtReset.addActionListener(new ResetListener());
  }

  /** 
   * Listen for when jbtLap is pressed and start the stopwatch if one hasn't started yet, otherwise
   * log a line of output representing the lap to jtaOutput.
   */
  class LapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (start) {
        startTime = System.currentTimeMillis();
        lastTime = startTime;
        jbtLap.setText("Lap");
        jbtLap.setMnemonic('L');
        start = false;
      } else {
        long lapTime = System.currentTimeMillis() - lastTime;
        long totalTime = System.currentTimeMillis() - startTime;
        jtaOutput.append(String.format("Lap #%d: %f (%f)\n", lapNum, lapTime / 1000.0,
            totalTime / 1000.0));
        lapNum++;
        lastTime = System.currentTimeMillis();
      }
    }
  }

  /** Listen for when jbtReset is pressed and reset the program to its defaults. */
  class ResetListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      startTime = 0;
      lastTime = 0;
      lapNum = 1;
      jtaOutput.setText("");
      jbtLap.setText("Start");
      jbtLap.setMnemonic('S');
      start = true;
    }
  }
  
  /** Main method. */
  public static void main(String[] args) {
    Stopwatch frame = new Stopwatch();
    frame.setSize(350, 200);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Stopwatch");
    frame.setVisible(true);
  }
}
