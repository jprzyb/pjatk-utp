/**
 * @author Wyrzykowski Tadeusz S17315
 */

package zad1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private ArrayList<T> list;

    private ListCreator(ArrayList<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(new ArrayList<>(list));
    }

    public ListCreator<T> when(Selector<T> selector) {
        Iterator<T> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (!selector.select(iterator.next())) {
                iterator.remove();
            }
        }

        return this;
    }

    public <F> List<F> mapEvery(Mapper<T, F> mapper) {
        List<F> mapped = new ArrayList<>();

        for (T t : list) {
            mapped.add(mapper.map(t));
        }

        return mapped;
    }
}
