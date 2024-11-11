/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static String path = System.getProperty("user.home")+"\\Towary.txt";
  public static void main(String[] args) throws IOException {

    List<String> collect = new ArrayList<>(Files.readAllLines(Paths.get(path)));
    Queue<Towar> list = new LinkedList<>();
    int fileSize=collect.size();

    Thread A = new Thread(()->{
    synchronized (list){
      for (int i = 0;i <fileSize; i++){
        list.add(new Towar(collect.get(i)));
        list.notify();
        if((i+1)%200==0 && i!=0) System.out.println("utworzono "+(i+1)+" obiektów");
      }
    }
    });

    Thread B = new Thread(()->{
      //System.out.println("B starts");
      double sum = 0;
      int count=0;
      Towar temp= new Towar("0 0");
      while(count<fileSize){
        synchronized (list){

          while(list.isEmpty()){
            try{
              //System.out.println("B waits");
              list.wait();
            }catch (Exception e){
              e.printStackTrace();
            }
          }
          //System.out.println("B process");
          temp=list.poll();
          sum+=temp.getWeight();
          count++;
          if((count+1)%100==0 && count!=0) System.out.println("policzono wage "+(count+1)+" towarów");
        }
      }
    });
    B.start();
    A.start();
  }
}
