package playersMonitoring.players;

import org.springframework.beans.factory.annotation.Autowired;
import playersMonitoring.web.repos.ChessplayerRepo;

public class Elo {
    @Autowired
    private ChessplayerRepo chessplayerRepo;

    private static int k = 16; //коэффициент максимального изменения рейтинга

    /**
     * @param x      - текущее эло 1 игрока
     * @param y      - текущее эло 2 игрока
     * @param winner - [0;2] - 0 - проиграл, 1 - выиграл, 2 - ничья
     * @return
     */
    public static Double getElo(double x, double y, int winner) throws Exception {
        if (winner < 0 || winner > 2)
            throw new Exception("Incorrect argument 'winner'. It must be [0;2]");
        double e = Math.round((1 / (1 + Math.pow(10, ((y - x) / 400)))) * 10) / 10d; //изменение рейтинга
        double z;
        if (winner == 2) {
            z = 0.5; //ничья
        } else {
            z = winner;
        }
        //player.setElo(x + k * (z - e));

        return Math.round((x + k * (z - e)) * 10) / 10d; //итоговый рейтинг с учетом кэффициента

    }
}
