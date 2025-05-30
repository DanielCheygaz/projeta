import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenreTestCase {
    private static Genre genre;

    @BeforeEach
    public void setUp(){
        genre = new Genre("Ficção Científica");
    }

    @Test
    public void testGetName(){
        assertEquals("Ficção Científica", genre.getName());
    }

    @Test
    public void testSetName(){
        genre.setName("Drama");
        assertEquals("Drama", genre.getName());
    }
}
