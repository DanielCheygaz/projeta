import java.util.*;

public class AppData {
    public static AppData instance = null;
    private LinkedList<Product> productList = new LinkedList<>();
    private LinkedList<Genre> genreList = new LinkedList<>();
    private LinkedList<Movie> movieList = new LinkedList<>();
    private LinkedList<Room> roomList = new LinkedList<>();
    private LinkedList<Session> sessionList = new LinkedList<>();
    private LinkedList<Ticket> ticketList = new LinkedList<>();
    private LinkedList<Bundle> bundleList = new LinkedList<>();

    // TODO: meter os "new LinkedList<>()" para dentro do construtor quando removermos os dados estáticos
    public AppData() {
        productList.add(new Product("CocaCola",1.2,(int)(Math.random()*101)));
        productList.add(new Product("KitKat",1.5,(int)(Math.random()*101)));
        productList.add(new Product("Snickers",1.5,(int)(Math.random()*101)));
        productList.add(new Product("Água",1,(int)(Math.random()*101)));
        productList.add(new Product("Tabaco",10.50,(int)(Math.random()*101)));
        productList.add(new Product("Gelatina",341,(int)(Math.random()*101)));

        genreList.add(new Genre("Ação"));
        genreList.add(new Genre("Drama"));
        genreList.add(new Genre("Terror"));
        genreList.add(new Genre("Suspance"));
        genreList.add(new Genre("Documentário"));
        genreList.add(new Genre("Animação"));

        movieList.add(new Movie("Avatar",180,"Saving pandora", genreList.get(0),true,true));
        movieList.add(new Movie("Titanic",123, "", genreList.get(1),false,false));
        movieList.add(new Movie("Annabelle",123, "Boooooo", genreList.get(2),true,false));

        roomList.add(new Room(1,5,5,true,false));
        roomList.add(new Room(2,10,15,false,false));
        roomList.add(new Room(3,20,5,false,true));
        roomList.add(new Room(4,30,10,true,true));

        sessionList.add(new Session(1, new Date(2025-1900,06-1,12,12,30),movieList.get(0),roomList.get(0)));
        sessionList.add(new Session(2, new Date(2025-1900,06-1,13,12,30),movieList.get(1),roomList.get(1)));
        sessionList.add(new Session(3, new Date(2025-1900,06-1,14,12,30),movieList.get(2),roomList.get(2)));
        sessionList.add(new Session(4, new Date(2025-1900,06-1,21,12,30),movieList.get(2),roomList.get(2)));

        ticketList.add(new Ticket(1,sessionList.get(0),10,"estudante"));
        ticketList.add(new Ticket(2,sessionList.get(1),15,"estudante"));
        ticketList.add(new Ticket(3,sessionList.get(2),8,"estudante"));
        ticketList.add(new Ticket(4,sessionList.get(0),9.50,"estudante"));
        ticketList.add(new Ticket(4,sessionList.get(3),9.50,"estudante"));

        bundleList.add(new Bundle(1,"Combo Pipocas",2, new LinkedList<>(Arrays.asList(productList.get(0),productList.get(1))), 2.5));
        bundleList.add(new Bundle(2,"Promoção Especial",3, new LinkedList<>(Arrays.asList(productList.get(2),productList.get(3))), 3.5));
        bundleList.add(new Bundle(3,"Bundle de chocolate",4, new LinkedList<>(Arrays.asList(productList.get(4),productList.get(5))), 5.5));
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
            carregarDados();
        }
        return instance;
    }

    private static void carregarDados() {}

    public LinkedList<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public void removeProduct(Product product){
        productList.remove(product);
    }

    public LinkedList<Genre> getGenreList() {
        return genreList;
    }

    public void addGenre(Genre genre){
        genreList.add(genre);
    }

    public void removeGenre(Genre genre){
        genreList.remove(genre);
    }

    public LinkedList<Movie> getMovieList() {
        return movieList;
    }

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    public void removeMovie(Movie movie){
        movieList.remove(movie);
    }

    public boolean moviesContainTitle(String title){
        for(Movie movie: movieList){
            if(movie.getName().toUpperCase().compareTo(title.toUpperCase())==0){
                return true;
            }
        }
        return false;
    }

    public LinkedList<Room> getRoomList() {
        return roomList;
    }

    public LinkedList<Session> getSessionList() {
        return sessionList;
    }

    public void addSession(Session session){
        sessionList.add(session);
    }

    public void removeSession(Session session){
        sessionList.remove(session);
    }

    public LinkedList<Ticket> getTicketList() {
        return ticketList;
    }

    public LinkedList<Bundle> getBundleList() {
        return bundleList;
    }
}
