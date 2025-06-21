//João Couto - 2221443

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class RoomTestCase {
    private Room room;
    private Movie movie;
    private Session session;
    private Genre genre;
    @BeforeEach

    void setUp(){
        genre = new Genre("Ação");
        room = new Room(1,5,5,true,false);
        movie = new Movie("Teste Filme", 120, "Descrição",genre,true,false);
        session = new Session(1, new Date(2026 - 1900, 0, 1, 0, 0),movie,room);
        AppData.getInstance().getSessionList().add(session);

    }

    @Test
    public void testConstructor(){
        assertEquals(1,room.getRoomNumber());
        assertEquals(5, room.getNumberRows());
        assertEquals(5, room.getNumberColumns());
        assertEquals(true,room.isImax());
        assertEquals(false,room.isDolbyAtmos());
    }

    @Test
    public void testDeleteRoomWithSession(){
        RoomManagerWindow rmw = new RoomManagerWindow();
        boolean canDelete = rmw.canBeDeleted(room);
        assertFalse(canDelete, "A sala não deveria poder ser removida porque está associada a uma sessão.");

    }

    @Test
    public void testGetNumberOfSeats(){
       assertEquals(25,room.getNumberOfSeats());
    }

    @Test
    public void testChangeSeats(){
        room.setNumberOfSeats(10,15);
        assertEquals(150,room.getNumberOfSeats());
    }

    @Test
    public void testSetImaxAndDolbyAtmos() {
        room.setImax(false);
        room.setDolbyAtmos(true);

        assertFalse(room.isImax());
        assertTrue(room.isDolbyAtmos());
    }
}
