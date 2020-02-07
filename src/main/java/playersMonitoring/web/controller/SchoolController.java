package playersMonitoring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import playersMonitoring.web.domain.Chessplayer;
import playersMonitoring.web.domain.School;
import playersMonitoring.web.repos.ChessplayerRepo;
import playersMonitoring.web.repos.SchoolRepo;

import java.util.List;
import java.util.Map;

@Controller
public class SchoolController {
    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private ChessplayerRepo chessplayerRepo;

    @GetMapping("/schools")
    public String school(Map<String, Object> model) {
        Iterable<Chessplayer> players = chessplayerRepo.findAll();
        model.put("players", players);

        Iterable<School> schools = schoolRepo.findAll();
        model.put("schools", schools);

        return "schoolPage";
    }

    @GetMapping("/addschool")
    public String addschool(Map<String, Object> model) {

        return "addSchoolPage";
    }

    @PostMapping("/addschool")
    public String add(@RequestParam String schoolName, Map<String, Object> model) {
        //проверяем, что такой школы не существует
        School school1 = schoolRepo.findByName(schoolName).orElse(null);
        if (school1 == null) {
            //сохраняем новую школу в бд
            School school = new School(schoolName);
            //передаем новую школу
            model.put("newSchools", List.of(schoolRepo.save(school)));
        } else {
            //школа уже существует
            model.put("oldSchools", List.of(schoolRepo.save(school1)));
        }

        //передаем все школы
        Iterable<School> schools = schoolRepo.findAll();
        model.put("schools", schools);
        return "schoolPage";
    }
}
