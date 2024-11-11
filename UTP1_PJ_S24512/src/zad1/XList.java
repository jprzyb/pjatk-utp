package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T>{

    //  CONSTRUCTORS
    public XList(T... elements) {
        Collections.addAll(this, elements);
    }
    public XList(Collection<T> collection) {
        super(collection);
    }

    //  OFS
    public static <K> XList<K> of (K... e) {
        return new XList<K>(e);
    }
    public static <K> XList<K> of (Collection<K> collection) {
        return new XList<K>(collection);
    }

    //  TOKENS OF
    public static XList<String> tokensOf(String s, String regex) {
        return XList.of(s.split(regex));
    }
    public static XList<String> tokensOf(String s) {
        return XList.tokensOf(s, "\\s");
    }

    //  CHARS OF
    public static XList<String> charsOf(String s) {
        return XList.tokensOf(s, "");
    }

    //  EQUALS
    public XList<T> clone() {
        return XList.of(this);
    }
    public XList<T> union(Collection<T> collection) {
        XList<T> temp  = this.clone();
        temp.addAll(collection);
        return temp;
    }
    public final XList<T> union(T... elements) {
        return this.union(XList.of(elements));
    }
    public XList<T> diff(Collection<T> collection) {
        XList<T> nList = new XList<T>();

        for (T t: this) {
            if (!collection.contains(t)) {
                nList.add(t);
            }
        }

        return nList;
    }
    public XList<T> unique() {
        return XList.of(new LinkedHashSet<T>(this));
    }

    //  COMBINATIONS
    public XList<XList<String>> combine() {
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }
    public <W> XList<W> collect(Function<T, W> fn) {
        XList<W> temp = new XList<W>();

        for (T el: this) {
            temp.add(fn.apply(el));
        }

        return temp;
    }
    public String join(String tj) {
        return this.stream().map(Object::toString).collect(Collectors.joining(tj));
    }
    public String join() {
        return this.join("");
    }
    public void forEachWithIndex(BiConsumer<T, Integer> fn) {
        for (int i = 0; i < this.size(); i++) {
            fn.accept(this.get(i), i);
        }
    }
} // e. o. class.
