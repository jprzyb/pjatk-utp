/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad4;


public class Main {
    public static void main(String[] args) {
        Author autor = new Author(args);
        new Thread(autor).start();
        new Thread(new Writer(autor)).start();
    }
}
