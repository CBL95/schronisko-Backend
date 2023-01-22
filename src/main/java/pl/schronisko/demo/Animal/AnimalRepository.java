package pl.schronisko.demo.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AnimalRepository
        extends JpaRepository<Animal, Long> {

    Optional<Animal> findAnimalByName(String name);


}
