public class Stock {
    private Product product;
    private int units;

    public Stock(Product product, int units) {
        this.product = product;
        this.units = units;
    }

    public Product getProduct() {
        return product;
    }

    public int getUnits() {
        return units;
    }

    public void addUnits(int units){
        this.units += units;
    }

    public void editStock(String name, int units, double price){
        product.editProduct(name,price);
        this.units = units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
