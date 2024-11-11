/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad3;


import java.util.concurrent.Executor;

public class Writer implements Runnable {

    Author author;

    Writer(Author author){
        this.author=author;
    }

    @Override
    public void run() {
        boolean predicat = true;
        int count=0;

        while (predicat){
            synchronized (author.queue){
                if(!author.queue.isEmpty()) {
                    System.out.println(author.queue.poll());
                    count++;
                }
            }
            if(count>=author.list.size()) break;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
