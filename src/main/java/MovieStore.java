import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


interface Predicate {
    boolean matches(Movie movie);
}


public class MovieStore {
    private LinkedList<Movie> movies = new LinkedList<Movie>();

    public void add(Movie movie) { movies.add(movie); }

    private List<Movie> findBy(Predicate predicate) {
        List<Movie> filteredMovies = movies.
                stream().
                filter(movie -> predicate.matches(movie)).
                collect(Collectors.toList());

        return filteredMovies;
    }


    public List<Movie> findByPartialTitle(String partialTittle) {
        Predicate predicate = movie -> movie.title.toLowerCase().contains(partialTittle.toLowerCase());
        return findBy(predicate);
    }

    public List<Movie> findByDirector(String director) {
        Predicate predicate = movie -> movie.director.equals(director);
        return findBy(predicate);
    }

    public List<Movie> findByReleaseYear(int from, int to) {
        Predicate predicate = movie -> movie.releaseYear > from && movie.releaseYear < to;
        return findBy(predicate);
    }


}
