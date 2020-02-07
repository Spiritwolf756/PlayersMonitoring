package playersMonitoring.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import playersMonitoring.players.Game;
import playersMonitoring.players.Pair;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Bean
    Game game() {
        return new Game(pair());
    }

    @Bean
    Pair pair() {
        return new Pair();
    }

    @Autowired
    Pair pair;
    @Autowired
    Game letsFigh;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        Game game = ctx.getBean(Game.class);
        game.go();
    }
}
