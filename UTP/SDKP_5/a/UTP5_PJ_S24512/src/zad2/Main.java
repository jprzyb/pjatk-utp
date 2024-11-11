package zad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

public class Main extends JFrame {
  private ExecutorService executor;
  private DefaultListModel<Future<String>> taskListModel;
  private JList<Future<String>> taskList;

  public Main() {
    setTitle("Task List Demo");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    executor = Executors.newFixedThreadPool(5); // Creating a thread pool

    taskListModel = new DefaultListModel<>(); // Task list model
    taskList = new JList<>(taskListModel);

    JButton startButton = new JButton("Add Task");
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        submitTask();
      }
    });

    JButton cancelButton = new JButton("Cancel Task");
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cancelTask();
      }
    });

    JButton getResultsButton = new JButton("Show Results");
    getResultsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showResults();
      }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(startButton);
    buttonPanel.add(cancelButton);
    buttonPanel.add(getResultsButton);

    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JScrollPane(taskList), BorderLayout.CENTER);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(null);
  }

  private void submitTask() {
    Callable<String> task = new Callable<String>() {
      @Override
      public String call() throws Exception {
        // Simulating task execution
        Thread.sleep(2000);
        return "Task Result";
      }
    };

    Future<String> future = executor.submit(task); // Executing the task and getting the Future
    taskListModel.addElement(future); // Adding the Future to the task list
  }

  private void cancelTask() {
    int selectedIndex = taskList.getSelectedIndex();
    if (selectedIndex != -1) {
      Future<String> selectedTask = taskListModel.getElementAt(selectedIndex);
      selectedTask.cancel(true);
      taskListModel.remove(selectedIndex);
    }
  }

  private void showResults() {
    int selectedIndex = taskList.getSelectedIndex();
    if (selectedIndex != -1) {
      Future<String> selectedTask = taskListModel.getElementAt(selectedIndex);
      if (selectedTask.isDone()) {
        try {
          String result = selectedTask.get();
          JOptionPane.showMessageDialog(this, "Task Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(this, "Task is not yet completed.");
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Main main = new Main();
        main.setVisible(true);
      }
    });
  }
}
