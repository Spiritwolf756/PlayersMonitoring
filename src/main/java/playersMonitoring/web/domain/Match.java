package playersMonitoring.web.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp matchTime;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatchDetails> matchDetails;

    public void setMatchDetails(List<MatchDetails> matchDetails) {
        this.matchDetails = matchDetails;
    }

    public List<MatchDetails> getMatchDetails() {
        return this.matchDetails;
    }

    public Match() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = calendar.getTimeInMillis();

        this.matchTime = new Timestamp(time);
    }

    public Match(Timestamp matchTime) {
        this.matchTime = matchTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Timestamp matchTime) {
        this.matchTime = matchTime;
    }
}
