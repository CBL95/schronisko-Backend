package pl.schronisko.demo.Animal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Configuration
public class AnimalConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnimalRepository repository) {
        return args -> {
           Animal burek = new Animal(
                    "Burek",
                    "Cat",
                    Sex.MALE,
                    3,
                    "Brown",
                    Size.BIG
            );

            Animal reksio = new Animal(
                    "Reksio",
                    "Dog",
                    Sex.FEMALE,
                    2,
                    "White",
                    Size.SMALL
            );

            repository.saveAll(
                    List.of(burek,reksio)
            );
        };


    }

}
