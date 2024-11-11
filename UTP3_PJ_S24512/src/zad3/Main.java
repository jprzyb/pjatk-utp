/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad3;

import javax.swing.*;
public class Main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManager taskManager = new TaskManager();
            taskManager.setVisible(true);
        });
    }
}

