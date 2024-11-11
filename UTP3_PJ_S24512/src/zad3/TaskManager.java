package zad3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.*;

public class TaskManager extends JFrame {
    private final DefaultListModel<Future<String>> taskListModel;
    private final JList<Future<String>> taskList;
    private final ExecutorService executor;

    public TaskManager() {
        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add task");
        JButton cancelButton = new JButton("Cancel task");
        JButton getResultsButton = new JButton("Show task");
        addButton.addActionListener(e -> addTask());
        cancelButton.addActionListener(e -> cancelTask());
        getResultsButton.addActionListener(e -> showResults());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(getResultsButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        executor = Executors.newCachedThreadPool();
    }

    private void addTask() {

        Callable<String> task = () -> {
            double wait = new Random().nextInt(8001) + 1000;
            Thread.sleep((int)(wait));
            return "Task done in "+(wait/1000)+"s!";
        };

        Future<String> future = executor.submit(task);
        taskListModel.addElement(future);
    }

    private void cancelTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Future<String> selectedTask = taskListModel.getElementAt(selectedIndex);
            if(!selectedTask.isDone() && !selectedTask.isCancelled()) selectedTask.cancel(true);
        }
    }

    private void showResults() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            try {
                Future<String> selectedTask = taskListModel.getElementAt(selectedIndex);
                if (selectedTask.isCancelled()) {
                    JOptionPane.showMessageDialog(this, "Cancelled!", "Wynik zadania", JOptionPane.ERROR_MESSAGE);
                }else if (selectedTask. isDone()){
                    JOptionPane.showMessageDialog(this, selectedTask.get(), "Wynik zadania", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Running.", "Wynik zadania", JOptionPane.WARNING_MESSAGE);
                }
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
    }
}
