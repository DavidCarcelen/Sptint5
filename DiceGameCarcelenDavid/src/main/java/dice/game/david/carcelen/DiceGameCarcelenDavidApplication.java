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
/*Pruebas Unitarias:
Pruebas enfocadas en métodos individuales dentro de las clases.
Se utilizan mocks para aislar el método en prueba.

Pruebas de Integración:
Pruebas que verifican la integración de múltiples componentes, como controladores y servicios.

Pruebas de Contrato con Pact:
Pruebas que aseguran que el consumidor de un servicio y el proveedor del servicio tienen un
entendimiento común de las interacciones*/

//readme
