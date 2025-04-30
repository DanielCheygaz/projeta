import java.util.Date;

public class Session {
    private int id;
    private Date data;
    private Movie movie;
    private Room room;

    public Session(int id, Date data, Movie movie, Room room) {
        this.id = id;
        this.data = data;
        this.movie = movie;
        this.room = room;
    }

    public Date getData() {
        return data;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public int getID() { return id; }
}
