import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AppData {
    public static AppData instance = null;
    private static List<Product> productList = new ArrayList<>();
    private static List<Genre> genreList = new ArrayList<>();
    private static List<Movie> movieList = new ArrayList<>();
    private static List<Room> roomList = new ArrayList<>();
    private static List<Session> sessionList = new ArrayList<>();
    private static List<Ticket> ticketList = new ArrayList<>();

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

        ticketList.add(new Ticket(1,sessionList.get(0),10));
        ticketList.add(new Ticket(2,sessionList.get(1),15));
        ticketList.add(new Ticket(3,sessionList.get(2),8));
        ticketList.add(new Ticket(4,sessionList.get(0),9.50));


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

    public static List<Genre> getGenreList() {
        return genreList;
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public static List<Room> getRoomList() {
        return roomList;
    }

    public static List<Session> getSessionList() {
        return sessionList;
    }

    public static List<Ticket> getTicketList() { return ticketList; }
}
