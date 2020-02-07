package playersMonitoring.web.domain;

import javax.persistence.*;

@Entity
public class MatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Chessplayer chessplayer;

    private Integer winner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Match match;

    private double ratingChange;

    public MatchDetails() {
    }

    public MatchDetails(Chessplayer chessplayer, Integer winner, Match match, double ratingChange) {
        this.chessplayer = chessplayer;
        this.winner = winner;
        this.match = match;
        this.ratingChange = ratingChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chessplayer getChessplayer() {
        return chessplayer;
    }

    public void setChessplayer(Chessplayer chessplayer) {
        this.chessplayer = chessplayer;
    }

    public Integer isWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public double getRatingChange() {
        return ratingChange;
    }

    public void setRatingChange(double ratingChange) {
        this.ratingChange = ratingChange;
    }

    @Override
    public String toString() {
        return chessplayer + " is " + winner + " [" + ratingChange + "]";
    }
}
