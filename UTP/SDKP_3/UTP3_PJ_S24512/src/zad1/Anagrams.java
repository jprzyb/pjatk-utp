/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    String path;

    public Anagrams(String path){
        this.path=path;

    }

    public List<List<String>> getSortedByAnQty() throws IOException {
        List<List<String>> resoult = new ArrayList<>();
        try{
            List<String> lol = Files.readAllLines(Path.of(path));
            List<String> fromFile = lol.stream()
                    .map(line -> line.split(" "))
                    .flatMap(tablica -> Arrays.stream(tablica))
                    .collect(Collectors.toList());


            while (!fromFile.isEmpty()){

                List<String> temp = new ArrayList<>();
                temp.add(fromFile.get(0));
                fromFile.remove(0);
                //log("TEXT="+temp.get(0));

                //adding to temp list
                for(int i=0;i<fromFile.size();i++){
                        if(sortString(fromFile.get(i)).equals(sortString(temp.get(0)))) {
                            temp.add(fromFile.get(i));
                            //log(fromFile.get(i) + " == " + temp.get(0));
                        }
                }
                for(int i=0;i< temp.size();i++){
                    fromFile.remove(temp.get(i));
                }
                resoult.add(temp);
            }
        }catch (IOException e){
            throw new RuntimeException();
        }

        List<List<String>> sortedResoult = resoult.stream()
                .sorted(Comparator.comparingInt((List<String> e) -> e.size()).reversed())
                .collect(Collectors.toList());

        return sortedResoult;
    }

    public String getAnagramsFor(String word) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append(word);
        result.append(": ");

        List<String> lol = new ArrayList<>();

        try{
            lol = Files.readAllLines(Path.of(path));
        }catch (Exception e){
            e.printStackTrace();
        }

        List<String> fromFile = lol.stream()
                .map(line -> line.split(" "))
                .flatMap(tablica -> Arrays.stream(tablica))
                .collect(Collectors.toList());

        if(!fromFile.contains(word)){
            result.append("null");
            return result.toString();
        }

        List<String> list = new ArrayList<>();
        for(int i=0;i<fromFile.size();i++){
            if(sortString(word).equals(sortString(fromFile.get(i))) && !word.equals(fromFile.get(i))){
                list.add(fromFile.get(i));
            }
        }

        result.append(list.toString());

        return result.toString();
    }

    private List<String> getListFromFile(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    public static String sortString(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
    private void log(String message){
        System.out.println("[ANAGRAMS] : " + message);
    }
}
