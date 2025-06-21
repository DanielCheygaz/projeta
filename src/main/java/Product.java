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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (units < 0) {
            throw new IllegalArgumentException("As unidades não podem ser negativas.");
        }

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
