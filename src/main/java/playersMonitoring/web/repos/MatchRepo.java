package playersMonitoring.web.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import playersMonitoring.web.domain.Chessplayer;
import playersMonitoring.web.domain.Match;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MatchRepo extends JpaRepository<Match, Long> {

    List<Match> findAll();

    List<Match> findByMatchTimeAfter(Date date);

}