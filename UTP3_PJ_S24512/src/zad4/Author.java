/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad4;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Author implements Runnable {
    private final String[] args;
    private final BlockingQueue<String> queue;
    private int alreadySent;
    public Author(String[] args) {
        alreadySent = 0;
        this.args = args;
        queue = new ArrayBlockingQueue<>(this.args.length);
    }
    @Override
    public void run() {
        for(String msg : args){
            queue.add(msg);
            alreadySent ++;
            try {
                if(!isDone()) Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getMsgFromQueue(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isDone(){
        return alreadySent == args.length;
    }
}
