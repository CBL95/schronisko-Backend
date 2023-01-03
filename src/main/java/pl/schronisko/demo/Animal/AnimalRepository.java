package pl.schronisko.demo.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnimalRepository
        extends JpaRepository<Animal, Long> {

    //@Query("SELECT s from Animal s WHERE s.id =?1")
    Optional<Animal> findAnimalById(Long id);


}
