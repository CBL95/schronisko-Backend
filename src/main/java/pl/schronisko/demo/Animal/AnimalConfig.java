package pl.schronisko.demo.Animal;

import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AnimalConfig {

    @Bean
    @Builder
    CommandLineRunner commandLineRunner(AnimalRepository repository) {
        return args -> {
            Animal reksiotestowy = Animal.builder()
                    .name("Reksio")
                    .category(Category.Pies)
                    .sex(Sex.Samiec)
                    .age(3)
                    .color("Biało-bury")
                    .size(Size.Normalny)
                    .build();

            Animal garfieldtestowy = Animal.builder()
                    .name("Garfield")
                    .category(Category.Kot)
                    .sex(Sex.Samiec)
                    .age(5)
                    .color("Rudy")
                    .size(Size.Duży)
                    .build();

            repository.saveAll(
                    List.of(reksiotestowy, garfieldtestowy)
            );
        };


    }

}
