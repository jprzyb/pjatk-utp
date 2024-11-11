/**
 *
 *  @author Wyrzykowski Tadeusz S17315
 *
 */

package zad2;


public class Purchase {
    private String clientId;
    private String name;
    private String surname;
    private String product;
    private double quantity;
    private double unitPrice;
    private String fullLine;

    public Purchase(String fullLine) {
        this.fullLine = fullLine;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getFullLine() {
        return fullLine;
    }

    public void setFullLine(String fullLine) {
        this.fullLine = fullLine;
    }
}
