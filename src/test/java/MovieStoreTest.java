import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class MovieStoreTest {

    private final Movie marighella = new Movie("Marighella", "Wagner Moura", 2000);
    private final Movie laVidaEBella = new Movie("La vida é Bella", "Director 01", 2001);
    private final Movie anotherMovie = new Movie("Another Movie", "Director 02", 1999);
    private final Movie movie03 = new Movie("Star Trek", "Director 03", 2000);
    private final Movie movie04 = new Movie("Ring Light", "Wagner Moura", 2002);

    private final MovieStore movieStore = new MovieStore();

    @BeforeEach
    void setUp() {
        movieStore.add(marighella);
        movieStore.add(laVidaEBella);
        movieStore.add(movie03);
        movieStore.add(movie04);
    }

    @Test
    public void returnsNoResultsWhenNoTitlePartiallyMatchSearch() throws Exception {
        MovieStore movieStore = new MovieStore();

        List<Movie> results = movieStore.findByPartialTitle("abc");

        assertThat(results.size(), is(0));
    }

    @Test
    public void findsMoviesWhenTittleArePartiallyMatched() throws Exception {
        List<Movie> results = movieStore.findByPartialTitle("lla");

        assertThat(results.size(), is(2));
        assertThat(results, hasItems(laVidaEBella));
        assertThat(results, not(hasItems(anotherMovie)));
    }

    @Test
    public void findsMoviesWhenDirectorExactlyMatched() throws Exception {
        List<Movie> results = movieStore.findByDirector("Wagner Moura");

        assertThat(results.size(), is(2));
        assertThat(results, containsInAnyOrder(marighella, movie04));
        assertThat(results, not(hasItems(anotherMovie)));
    }

    @Test
    public void findsMoviesWhenReleaseYearIsBetweenTwoValues() throws Exception {
        List<Movie> results = movieStore.findByReleaseYear(1999, 2002);

        assertThat(results.size(), is(3));
        assertThat(results, containsInAnyOrder(marighella, laVidaEBella, movie03));
        assertThat(results, not(hasItems(movie04, anotherMovie)));
    }
}
