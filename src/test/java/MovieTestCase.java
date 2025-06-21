// Feito por Daniel Chagas
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTestCase {
    private static Genre genre;
    private static Movie movie;

    @BeforeEach
    public void setUp(){
        genre = new Genre("Ação");
        movie =  new Movie("Avatar",180,"Saving pandora", genre,true,true);
    }

    @Test
    public void testConstructor(){
        assertEquals("Avatar", movie.getName());
        assertEquals(180, movie.getDuration());
        assertEquals(genre,movie.getGenre());
        assertEquals("Saving pandora", movie.getDescription());
        assertEquals(true, movie.isImax());
        assertEquals(true, movie.isDolbyAtmos());
    }

    @Test
    public void testUpdateMovie(){
        Genre newGenre = new Genre("Suspance");
        Movie newMovie = new Movie("Oppenheimer",173,"", newGenre,false,true);
        movie.updateMovie("Oppenheimer",173,"", newGenre,false,true);
        assertEquals(newMovie.toString(),movie.toString());
    }

}
