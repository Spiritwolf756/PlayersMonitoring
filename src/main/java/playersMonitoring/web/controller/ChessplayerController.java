package playersMonitoring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import playersMonitoring.web.domain.Chessplayer;
import playersMonitoring.web.domain.Match;
import playersMonitoring.web.domain.MatchDetails;
import playersMonitoring.web.domain.School;
import playersMonitoring.web.repos.ChessplayerRepo;
import playersMonitoring.web.repos.MatchRepo;
import playersMonitoring.web.repos.SchoolRepo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ChessplayerController {
    @Autowired
    private ChessplayerRepo chessplayerRepo;
    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private MatchRepo matchRepo;


    @GetMapping("/players")
    public String players(Map<String, Object> model) {
        //достаем всех игроков и передаем их для отображения на странице
        Iterable<Chessplayer> players = chessplayerRepo.findAll();
        model.put("players", players);


        return "playersPage";
    }

    @GetMapping("/addplayer")
    public String addplayer(Map<String, Object> model) {
        //достаем всех игроков и передаем их для отображения на странице
        Iterable<Chessplayer> players = chessplayerRepo.findAll();
        model.put("players", players);

        //достаем все школы и передаем их для отображения на странице
        Iterable<School> schools = schoolRepo.findAll();
        model.put("schools", schools);

        return "addPlayerPage";
    }

    @PostMapping("/addplayer")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String lastname, @RequestParam String school, Map<String, Object> model) {
        School school1 = schoolRepo.findByName(school).orElse(null);
        //проверяем, есть ли школа с заданным названием
        if (school1 != null) {
            //создаем и сохраняем пользователя
            Chessplayer player = new Chessplayer(name, lastname, surname, school1);
            //передаем нового игрока
            model.put("newPlayers", List.of(chessplayerRepo.save(player)));

            //достаем всех игроков и передаем их для отображения на странице
            Iterable<Chessplayer> players = chessplayerRepo.findAll();
            model.put("players", players);

            return "playersPage";
        }
        System.out.println("ERROR");
        return "error";
    }

    @GetMapping("/delplayer/{id}")
    public String del(@PathVariable("id") int id, Map<String, Object> model) {
        Chessplayer player = chessplayerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        chessplayerRepo.delete(player);

        //достаем всех игроков и передаем их для отображения на странице
        Iterable<Chessplayer> players = chessplayerRepo.findAll();
        model.put("players", players);

        return "playersPage";
    }

    @GetMapping("/bestplayers")
    public String best(Map<String, Object> model) {

        //Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);
        Date date = calendar.getTime();
        System.out.println(date.toString());
        List<Match> matchs = matchRepo.findByMatchTimeAfter(date);


        Map<Chessplayer, Double> result = matchs.stream() //преобразуем в стрим
                // .map(Match::getMatchDetails) //мапа деталий матчей
                .flatMap(Match -> Match.getMatchDetails().stream()) //мапа деталей матча

                .collect(Collectors.groupingBy(MatchDetails::getChessplayer, Collectors.summingDouble(MatchDetails::getRatingChange)))

                .entrySet()
                .stream()
                .sorted(Map.Entry.<Chessplayer, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        model.put("players", result);

        return "bestPlayers";

    }
}
