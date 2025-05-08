import java.util.*;

public class AppData {
    public static AppData instance = null;
    private LinkedList<Product> productList = new LinkedList<>();
    private LinkedList<Genre> genreList = new LinkedList<>();
    private LinkedList<Movie> movieList = new LinkedList<>();
    private LinkedList<Room> roomList = new LinkedList<>();
    private LinkedList<Session> sessionList = new LinkedList<>();
    private LinkedList<Stock> stockList = new LinkedList<>();
    private LinkedList<Ticket> ticketList = new LinkedList<>();

    public AppData() {
        productList.add(new Product("CocaCola",1.2));
        productList.add(new Product("KitKat",1.5));
        productList.add(new Product("Snickers",1.5));
        productList.add(new Product("Água",1));
        productList.add(new Product("Tabaco",10.50));
        productList.add(new Product("Gelatina",341));

        for(Product product: getProductList()){
            stockList.add(new Stock(product, (int)(Math.random()*101)));
        }

        genreList.add(new Genre("Ação"));
        genreList.add(new Genre("Drama"));
        genreList.add(new Genre("Terror"));

        movieList.add(new Movie("Avatar",180,"Os gajos azuis à porrada e tal", genreList.get(0),true,true));
        movieList.add(new Movie("Titanic",123, genreList.get(1)));
        movieList.add(new Movie("Annabelle",123, "Boooooo", genreList.get(2),true,false));

        roomList.add(new Room(1,5,5,true,false));
        roomList.add(new Room(2,10,15,false,false));
        roomList.add(new Room(3,20,5,false,true));
        roomList.add(new Room(4,30,10,true,true));

        sessionList.add(new Session(1, new Date(2025,5,12,12,30),movieList.get(0),roomList.get(0)));
        sessionList.add(new Session(2, new Date(2025,5,12,12,30),movieList.get(1),roomList.get(1)));
        sessionList.add(new Session(3, new Date(2025,5,12,12,30),movieList.get(2),roomList.get(2)));

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


    public void addStock(Stock stock){
        stockList.add(stock);
    }
    public LinkedList<Stock> getStockList() {
        return stockList;
    }

    public void removeStock(Stock stock){
        stockList.remove(stock);
    }

    public LinkedList<Product> getProductList() {
        return productList;
    }

    public LinkedList<Genre> getGenreList() {
        return genreList;
    }

    public LinkedList<Movie> getMovieList() {
        return movieList;
    }

    public void removeMovie(Movie movie){
        movieList.remove(movie);
    }

    public LinkedList<Room> getRoomList() {
        return roomList;
    }

    public LinkedList<Session> getSessionList() {
        return sessionList;
    }

    public LinkedList<Ticket> getTicketList() {
        return ticketList;
    }
}
