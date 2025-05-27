import java.util.Date;

public class Session {
    private int id;
    private Date date;
    private Movie movie;
    private Room room;

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
}
