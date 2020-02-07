package playersMonitoring.web.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chessplayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String surname;
    private Double elo;

    @ManyToOne(fetch = FetchType.EAGER)
    private School school;

    @OneToMany(mappedBy = "chessplayer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatchDetails> matchDetails;

    public Chessplayer() {
    }

    public Chessplayer(String name, String lastname, String surname, School school) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.elo = 400d;
        this.school = school;
    }

    public Chessplayer(String name, String lastname, String surname, Double elo, School school) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.elo = elo;
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getElo() {
        return elo;
    }

    public void setElo(Double elo) {
        this.elo = elo;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + lastname + " " + elo + " [" + school + ']';
    }
}
