package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {
    List<Thread> threads;
    public Letters (String letters){
        threads = new ArrayList<>();
        for(int i=0 ; i<letters.length() ; i++){
            int x = i;
            threads.add(new Thread(()->{
                while(!threads.get(x).isInterrupted()) System.out.print(letters.charAt(x));
            }, "Thread "+letters.charAt(i)));
        }
    }
    public List<Thread> getThreads(){
        return threads;
    }
}
