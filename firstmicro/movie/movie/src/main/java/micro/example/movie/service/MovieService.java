package micro.example.movie.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import micro.example.movie.repository.MovieRepository;
import micro.example.movie.entity.Movie;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

    public Movie add(Movie p) {
        return repo.save(p);
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public Movie getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Movie update(Long id, Movie updatedProduct) {

        Movie existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("movie not found"));

        existing.setName(updatedProduct.getName());
        existing.setLanguage(updatedProduct.getLanguage());
        existing.setGenre(updatedProduct.getGenre());
        existing.setDuration(updatedProduct.getDuration());

        return repo.save(existing);

    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}