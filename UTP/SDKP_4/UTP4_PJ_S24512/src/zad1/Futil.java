package zad1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Futil{

    private static List<String> resoult = new ArrayList<>();

    public static void processDir(String dirName, String resultFileName) {

        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                    if(file.toAbsolutePath().toString().endsWith(".txt")){
                        getTextFromFile(file.toAbsolutePath());
                    }
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException{
                    return CONTINUE;
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileWriter out = new FileWriter(resultFileName);
            for(String l : resoult) out.write(l+"\n");
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getTextFromFile(Path path){
        try {

            List<String> l = new ArrayList<>();
            l=Files.readAllLines(path);
            for (String a : l){
                resoult.add(a);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
