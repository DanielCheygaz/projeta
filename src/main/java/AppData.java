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
    private Sale activeSale = null;

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
        ticketList.add(new Ticket(5,sessionList.get(3),9.50,"estudante"));




    }

    public void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    public List<GenreStats> getTopSellingGenres() {
        Map<String, Integer> genreCount = new HashMap<>();
        int totalTickets = 0;

        // Iterar sobre todos os bilhetes vendidos
        for (Ticket ticket : ticketList) {
            Session session = ticket.getSession(); // Obter a sessão associada
            if (session != null) {
                Movie movie = session.getMovie(); // Obter o filme da sessão
                if (movie != null) {
                    Genre genre = movie.getGenre(); // Obter o género do filme
                    if (genre != null) {
                        String genreName = genre.getName();
                        genreCount.put(genreName, genreCount.getOrDefault(genreName, 0) + 1);
                        totalTickets++;
                    }
                }
            }
        }

        // Criar a lista de estatísticas com percentagem
        List<GenreStats> stats = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            double percentage = totalTickets > 0 ? (entry.getValue() * 100.0) / totalTickets : 0.0;
            stats.add(new GenreStats(entry.getKey(), percentage));
        }

        // Ordenar por percentagem descendente (opcional)
        stats.sort((a, b) -> Double.compare(b.getPercentage(), a.getPercentage()));

        return stats;
    }

    public List<SessionStats> getSessionOccupancyStats() {
        List<SessionStats> stats = new ArrayList<>();

        for (Session session : sessionList) {
            int totalSeats = session.getRoom().getNumberRows() * session.getRoom().getNumberColumns();
            int occupied = session.getOccupiedSeats().size();
            double occupancyRate = totalSeats > 0 ? (occupied * 100.0) / totalSeats : 0.0;

            stats.add(new SessionStats(session.getID(), occupancyRate));
        }

        // Ordena por taxa de ocupação (opcional)
        stats.sort((a, b) -> Double.compare(b.getOccupancyRate(), a.getOccupancyRate()));

        return stats;
    }

    private LinkedList<Product> soldProducts = new LinkedList<>();

    public void addSoldProduct(Product product) {
        soldProducts.add(product);
    }

    public LinkedList<Product> getSoldProducts() {
        return soldProducts;
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

    public Sale getActiveSale() {
        return activeSale;
    }

    public void startSale(){
        this.activeSale = new Sale();
    }
}
