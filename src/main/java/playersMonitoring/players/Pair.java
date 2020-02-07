package playersMonitoring.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import playersMonitoring.web.domain.Chessplayer;
import playersMonitoring.web.repos.ChessplayerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Pair {
    private Chessplayer player1;
    private Chessplayer player2;
    @Autowired
    private ChessplayerRepo chessplayerRepo;

    public Pair() {
    }

    private void setPlayer1(Chessplayer player1) {
        this.player1 = player1;
    }

    private Chessplayer getPlayer1() {
        return player1;
    }

    private void setPlayer2(Chessplayer player2) {
        this.player2 = player2;
    }

    private Chessplayer getPlayer2() {
        return player2;
    }

    public Chessplayer[] getPair() {
        //получаем список всех игроков
        Iterable<Chessplayer> allplayersIter = chessplayerRepo.findAll();

        List<Chessplayer> allplayers = new ArrayList<>();
        allplayersIter.forEach(allplayers::add);

        if (allplayers.size() == 0)
            return null;

        //генерим случайное число
        Random rnd = new Random((System.currentTimeMillis()));
        int rndInt = rnd.nextInt(allplayers.size());
        //берем случайного игрока
        setPlayer1(allplayers.get(rndInt));
        //удаляем его из коллекции
        allplayers.remove(rndInt);

        //идем по циклу, пока не найдем школу, отличную от школы первого игрока
        //Chessplayer rndplayer;
        Chessplayer rndplayer;
        //Chessplayer rndplayer=allplayers.get(rndInt)
        for (int i = 0; i < allplayers.size(); ) {
            //берем случайного игрока
            rndInt = rnd.nextInt(allplayers.size());
            rndplayer = allplayers.get(rndInt);
            //проверяем на соответствие школы
            if (!player1.getSchool().equals(
                    rndplayer.getSchool())) {
                setPlayer2(rndplayer);
                return new Chessplayer[]{player1, player2};
            } else {
                //если не подошел - удаляем из коллекции
                allplayers.remove(rndInt);
            }

        }

        return null;
        //List<Chessplayer> player12 = chessplayerRepo.findRandom(0, PageRequest.of(0, 10));
        //player1=player12.get(0);

    }

}
