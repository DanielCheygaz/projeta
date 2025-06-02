public class Product {
    public String name;
    public double price;
    public int units;

    public Product(String name, double price, int units) {
        this.name = name;
        this.price = price;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getUnits() {
        return units;
    }

    public void addUnits(int units){
        this.units += units;
    }

    public void editProduct(String name, double price, int units){
        this.name = name;
        this.price = price;
        this.units = units;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
