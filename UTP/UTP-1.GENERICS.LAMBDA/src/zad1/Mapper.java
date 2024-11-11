/**
 * @author Wyrzykowski Tadeusz S17315
 */

package zad1;

@FunctionalInterface
public interface Mapper<T, F> { // Uwaga: interfejs musi być sparametrtyzowany
    F map(T t);
}
