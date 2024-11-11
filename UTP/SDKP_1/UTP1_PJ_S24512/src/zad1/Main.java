/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1,2,3,5,7,13,17,19,23)/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("q", "qwerty", "a" , "abc" , "x" , "xyz" );/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test2(src2));

    List<Integer> src4 = Arrays.asList(
        Integer.MAX_VALUE+1,
        Integer.MIN_VALUE+2,
        0b10000000_00000000_00000000_00000000+4,
        0x80_00_00_00,
        020_000_000_000+5);
    System.out.println(test4(src4));

    }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>() {
      @Override
      public boolean select(Integer val) {
        return val < 10;
      }
    }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
      @Override
      public Integer map(Integer val) {
        return val + 10;
      }
    };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return ListCreator.collectFrom(src).when(sel).mapEvery(map); /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  
  }

  public List<Integer> test2(List<String> src) {
    Selector Selector<String> sel = new Selector<String>() {
      @Override
      public boolean select(String el) {
        return el.length() > 3;
      }
    };/*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper   Mapper<Integer, String> map = new Mapper<Integer, String>() {
      @Override
      public Integer map(String el) {
        return el.length() + 10;
      }
    };/*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);/*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */
  }

  public List<String> test4(List<Integer> src) {
    /*    Podczas tworzenia poniższch selektorów oraz maperów nie używaj lambda wyrażeń. */
    Selector negativeSelector;/*<-- zwraca true tylko jeśli przekazany argument jest ujemny. Przykład: 100 -> false */
    Mapper absoluteMapper;  /*<-- zwraca wartość bezwzględną dowolnej przekazanej mu liczby.   Przykład: -224 -> 224 */
    Selector evenSelector;/*<-- zwraca true tylko jeśli przekazana mu liczba jest parzysta.  Przykład: 100 -> true */
    Mapper reverseMapper;  /*<-- zwraca przekazaną mu liczbę w postaci odwróconego Stringa.  Przykład: 204 -> "402"  */

    /* zwrot wyniku uzyskanego przez wywołanie statycznej metody klasy ListCreator */
    return collectFrom(src).when(negativeSelector).mapEvery(absoluteMapper).when(evenSelector).mapEvery(reverseMapper);
  }

  public static void main(String[] args) {
    new Main();
  }
}
