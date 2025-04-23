import java.util.ArrayList;
import java.util.List;

public class AppData {
    public static AppData instance = null;
    private static List<Product> productList = new ArrayList<>();

    public AppData() {
        productList.add(new Product("CocaCola",15,1.2));
        productList.add(new Product("KitKat",12,1.5));
        productList.add(new Product("Snickers",22,1.5));
        productList.add(new Product("Água",43,1));
        productList.add(new Product("Tabaco",100000,10.50));
        productList.add(new Product("Gelatina",236,341));
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
            carregarDados();
        }
        return instance;
    }

    private static void carregarDados() {}

    public static List<Product> getProductList() {
        return productList;
    }
}
