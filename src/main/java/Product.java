public class Product {
    public String name;
    public double price;
    public int units;

    public Product(String name, int units, double price) {
        this.name = name;
        this.units = units;
        this.price = price;
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
}
