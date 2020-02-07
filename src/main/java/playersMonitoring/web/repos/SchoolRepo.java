package playersMonitoring.web.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import playersMonitoring.web.domain.School;

import java.util.Optional;

public interface SchoolRepo extends JpaRepository<School, Long> {
    Optional<School> findByName(String name);
    Optional<School> findById(Long id);
}
