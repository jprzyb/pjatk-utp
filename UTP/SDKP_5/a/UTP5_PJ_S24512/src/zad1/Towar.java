package zad1;

public class Towar {

    double id;
    double weight;
    Towar(String a){
        this.id = Double.valueOf(a.substring(0,a.indexOf(" ")) );
        this.weight = Double.valueOf(a.substring(a.indexOf(" ")+1 , a.length()) );
    }

    @Override
    public String toString() {
        return id+" "+weight;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
