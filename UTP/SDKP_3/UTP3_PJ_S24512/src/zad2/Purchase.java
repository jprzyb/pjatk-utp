/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad2;


import java.util.Comparator;
import java.util.List;

public class Purchase implements Comparable<Purchase>{

    private String id;
    private String imie;
    private String nazwisko;
    private String nazwa;
    private Double cena;
    private Double ilosc;

    public Purchase(String id, String nazwisko, String imie, String nazwa, Double cena, Double ilosc){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.cena =ilosc*cena;
    }

    public String getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Double getCena() {
        return cena;
    }

    public Double getIlosc() {
        return ilosc;
    }

    @Override
    public String toString() {
        return id+";"+nazwisko+";"+imie+";"+nazwa+";"+cena+";"+ilosc;
    }

    public String toStringK() {
        return id+";"+nazwisko+";"+imie+";"+nazwa+";"+ilosc+"(koszt: "+cena+")";
    }

    @Override
    public int compareTo(Purchase o) {
        return 0;
    }

}
