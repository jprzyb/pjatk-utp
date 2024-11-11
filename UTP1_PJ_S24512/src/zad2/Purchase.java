/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad2;


import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public String id;
    public String lastName;
    public String name;
    public String product;
    public double price;
    public double count;
    public static List<Purchase> PURCHASES = new ArrayList<>();
    public Purchase(String id, String lastName, String name, String product, double price, double count) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.product = product;
        this.price = price;
        this.count = count;
        PURCHASES.add(this);
    }

    @Override
    public String toString() {
        return id + ';' + lastName + ';' + name + ';' + product + ';' + price + ";" + count ;
    }
    public static List<Purchase> getList(){
        return PURCHASES;
    }

    public String getLastName(){
        return lastName;
    }
    public Double getPrice(){
        return price;
    }
    public String getId(){
        return id;
    }
}
