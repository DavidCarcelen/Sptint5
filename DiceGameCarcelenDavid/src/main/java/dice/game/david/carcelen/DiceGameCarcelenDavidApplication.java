package dice.game.david.carcelen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dice.game.david.carcelen.model.repository.jpa")
@EnableMongoRepositories(basePackages = "dice.game.david.carcelen.model.repository.mongo")
public class DiceGameCarcelenDavidApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameCarcelenDavidApplication.class, args);
	}

}

//games id null?
//exception empty gamelist