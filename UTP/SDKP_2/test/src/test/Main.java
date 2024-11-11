package test;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String a = "Ala ma kota 123";
        double xrate = 1.5;
        String s = a.substring(a.lastIndexOf(" ") , a.length()-1);
        System.out.println("int="+s);
        System.out.println("double="+Double.valueOf(Double.valueOf(s)*xrate));
        System.out.println("Integer="+(int)(Double.valueOf(s)*xrate));



    }
}
