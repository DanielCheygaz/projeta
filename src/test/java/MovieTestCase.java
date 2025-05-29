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
    public void testGetName(){
        assertEquals("Avatar", movie.getName());
    }

    @Test
    public void testGetDuration(){
        assertEquals(180, movie.getDuration());
    }

    @Test
    public void testGetGenre(){
        assertEquals(genre,movie.getGenre());
    }

    @Test
    public void testGetDescription(){
        assertEquals("Saving pandora", movie.getDescription());
    }

    @Test
    public void testIsImax(){
        assertEquals(true, movie.isImax());
    }

    @Test
    public void testIsDolbyAtmos(){
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
