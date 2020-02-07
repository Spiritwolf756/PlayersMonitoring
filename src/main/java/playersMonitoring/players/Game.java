package playersMonitoring.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import playersMonitoring.web.domain.Chessplayer;
import playersMonitoring.web.domain.Match;
import playersMonitoring.web.domain.MatchDetails;
import playersMonitoring.web.repos.ChessplayerRepo;
import playersMonitoring.web.repos.MatchDetailsRepo;
import playersMonitoring.web.repos.MatchRepo;

import java.util.Random;

@Component
public class Game {
    @Autowired
    private Pair pair;
    @Autowired
    private ChessplayerRepo chessplayerRepo;
    @Autowired
    private MatchDetailsRepo matchDetailsRepo;
    @Autowired
    private MatchRepo matchRepo;

    public Game(Pair pair) {
        this.pair = pair;
    }

    public void go() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //получаем пару случайных игроков
                    Chessplayer[] players = pair.getPair();
                    //проверяем, что игроки существуют
                    if (players != null) {
                        //выбираем случайным образом победителя
                        Random rnd = new Random((System.currentTimeMillis()));
                        int win1 = rnd.nextInt(3);
                        int win2 = 2; //ничья
                        if (win1 == 0) // проиграл
                            win2 = 1; //победил
                        if (win1 == 1) //победил
                            win2 = 0; //проиграл
                        System.out.println(win1);
                        System.out.println("Первый игрок: " + players[0]);
                        System.out.println("Второй игрок: " + players[1]);

                        try {
                            //сохраняем первоначальные значения эло игроков
                            double elo1 = players[0].getElo();
                            double elo2 = players[1].getElo();
                            //Double[] newScore = Elo.getElo(players[0].getElo(), players[1].getElo(), rndInt);
                            //System.out.println("Изменение рейтинга первого игрока: " + players[0].getElo() + " -> " + Elo.getElo(players[0].getElo(), players[1].getElo(), win1));

                            //изменяем эло у первого игрока
                            players[0].setElo(Elo.getElo(players[0].getElo(), players[1].getElo(), win1));
                            chessplayerRepo.save(players[0]);

                            //изменяем эло у второго игрока
                            players[1].setElo(Elo.getElo(players[1].getElo(), players[0].getElo(), win2));
                            chessplayerRepo.save(players[1]);
                            System.out.println("РЕЗУЛЬТАТЫ:");
                            System.out.println("Первый игрок: " + players[0]);
                            System.out.println("Второй игрок: " + players[1]);

                            //сохраняем данные о матче
                            Match match = new Match();
                            matchRepo.save(match);
                            //сохраняем данные в матчдетейлс о первом игроке
                            MatchDetails matchDetails = new MatchDetails(players[0], win1, match, Math.round((players[0].getElo() - elo1) * 10) / 10d);
                            matchDetailsRepo.save(matchDetails);
                            //сохраняем данные в матчдетейлс о втором игроке
                            matchDetails = new MatchDetails(players[1], win1, match, Math.round((players[1].getElo() - elo2) * 10) / 10d);
                            matchDetailsRepo.save(matchDetails);

                            //System.out.println("Изменение рейтинга второго игрока: " + players[1].getElo() + " -> " + Elo.getElo(players[1].getElo(), players[0].getElo(), win2));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }
}
