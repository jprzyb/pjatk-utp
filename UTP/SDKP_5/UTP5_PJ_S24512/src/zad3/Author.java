/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Author implements Runnable {

    Queue<String> queue = new LinkedList<>();
    List<String> list = new ArrayList<>();

    Author(String[] message){
        for (String m :message) list.add(m);
    }

    @Override
    public void run() {
        for (int i=0;i<list.size();i++) {
            try {
                Thread.sleep(1000); // Oczekiwanie przez 1 sekundę
               synchronized (queue){
                   queue.add(list.get(i)); // Dodawanie "a" do kolejki
                   queue.notify();
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
