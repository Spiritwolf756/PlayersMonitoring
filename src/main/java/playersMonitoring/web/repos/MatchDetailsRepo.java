package playersMonitoring.web.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import playersMonitoring.web.domain.MatchDetails;

import java.util.List;

public interface MatchDetailsRepo extends JpaRepository<MatchDetails, Long> {
    List<MatchDetails> findAll();
}