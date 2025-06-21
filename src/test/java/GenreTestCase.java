// Feito por Daniel Chagas
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
    public void testConstructor(){
        assertEquals("Ficção Científica", genre.getName());
    }

    @Test
    public void testSetName(){
        assertThrows(IllegalArgumentException.class, ()->{genre.setName("");}, "IllegalArgumentException expected");
        genre.setName("Drama");
        assertEquals("Drama", genre.getName());
    }
}
