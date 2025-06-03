import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Session {
    private final int id;
    private Date date;
    private Movie movie;
    private Room room;
    private List<Seat> occupiedSeats = new ArrayList<>();

    public Session(int id, Date date, Movie movie, Room room) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public int getID() { return id; }

    public void updateSession(Date date, Movie movie, Room room){
        this.date = date;
        this.movie = movie;
        this.room = room;
    }

    // Geração de todos os lugares da sala
    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int row = 0; row < room.getNumberRows(); row++) {
            for (int col = 0; col < room.getNumberColumns(); col++) {
                seats.add(new Seat(row, col));
            }
        }
        return seats;
    }

    public List<Seat> getOccupiedSeats() {
        return occupiedSeats;
    }

    public boolean isSeatOccupied(Seat seat) {
        return occupiedSeats.contains(seat);
    }

    public void occupySeat(Seat seat) {
        if (!occupiedSeats.contains(seat)) {
            occupiedSeats.add(seat);
        }
    }
}
