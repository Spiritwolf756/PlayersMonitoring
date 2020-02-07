package playersMonitoring.web.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import playersMonitoring.web.domain.Chessplayer;

import java.util.List;
import java.util.Optional;

public interface ChessplayerRepo extends JpaRepository<Chessplayer, Long> {
    List<Chessplayer> findAll();

    Optional<Chessplayer> findById(int id);

}
