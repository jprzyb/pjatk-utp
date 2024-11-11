import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = System.getProperty("user.home")+"\\Towary.txt";;
        List<String> list = new ArrayList<>();
        FileWriter out = new FileWriter(path);
        Random r = new Random();

        for (int i = 0;i < 20000; i++) {
            StringBuilder s = new StringBuilder(i+" "+r.nextDouble(0,1000)+"\n");
            list.add(String.valueOf(s));
        }
        out.write(list.toString().replace("[","").replace("]","").replace(", ",""));
        out.close();
    }
}