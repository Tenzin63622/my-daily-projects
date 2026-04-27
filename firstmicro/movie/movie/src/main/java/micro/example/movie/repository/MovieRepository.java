package micro.example.movie.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import micro.example.movie.entity.Movie;
public interface MovieRepository extends JpaRepository<Movie, Long> {}
