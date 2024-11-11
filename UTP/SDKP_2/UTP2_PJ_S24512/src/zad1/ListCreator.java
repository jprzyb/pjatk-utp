/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> extends ArrayList<T> {
    private List<T> list;

    public ListCreator(List<T> list){ // constructor
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<T>(list);
    }

    public ListCreator<T> when(Predicate<T> predicate){
        List<T> tmp = new ArrayList<T>();
        for (T element: this.list) {
            if(predicate.test(element)){
                tmp.add(element);
            }
        }

        return new ListCreator<T>(tmp);

    }

    public <R> List<R> mapEvery(Function<T, R> function){
        List<R> tmp = new ArrayList<R>();

        for (T element: this.list) {
            tmp.add(function.apply(element));
        }
        return tmp;
    }


}