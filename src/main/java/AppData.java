import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AppData {
    public static AppData instance = null;
    private static LinkedList<Product> productList = new LinkedList<>();
    private static LinkedList<Genre> genreList = new LinkedList<>();
    private static LinkedList<Movie> movieList = new LinkedList<>();
    private static LinkedList<Room> roomList = new LinkedList<>();
    private static LinkedList<Session> sessionList = new LinkedList<>();

    public AppData() {
        productList.add(new Product("CocaCola",15,1.2));
        productList.add(new Product("KitKat",12,1.5));
        productList.add(new Product("Snickers",22,1.5));
        productList.add(new Product("Água",43,1));
        productList.add(new Product("Tabaco",100000,10.50));
        productList.add(new Product("Gelatina",236,341));

        genreList.add(new Genre("Ação"));
        genreList.add(new Genre("Drama"));
        genreList.add(new Genre("Terror"));

        movieList.add(new Movie("Avatar",180,"Os gajos azuis à porrada e tal", genreList.get(0)));
        movieList.add(new Movie("Titanic",123, genreList.get(1)));
        movieList.add(new Movie("Annabelle",123, genreList.get(2)));

        roomList.add(new Room(1));
        roomList.add(new Room(2));
        roomList.add(new Room(3));
        roomList.add(new Room(4));

        sessionList.add(new Session(new Date(2025,5,12,12,30),movieList.get(0),roomList.get(0)));
        sessionList.add(new Session(new Date(2025,5,12,12,30),movieList.get(1),roomList.get(1)));
        sessionList.add(new Session(new Date(2025,5,12,12,30),movieList.get(2),roomList.get(2)));

    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
            carregarDados();
        }
        return instance;
    }

    private static void carregarDados() {}

    public static LinkedList<Product> getProductList() {
        return productList;
    }

    public static LinkedList<Genre> getGenreList() {
        return genreList;
    }

    public static LinkedList<Movie> getMovieList() {
        return movieList;
    }

    public static LinkedList<Room> getRoomList() {
        return roomList;
    }

    public static LinkedList<Session> getSessionList() {
        return sessionList;
    }
}
