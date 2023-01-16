package pl.schronisko.demo.Animal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository underTest;

    @Test
    void itShouldCheckIfAnimalNameExists(){
        //given
        String name = "Lucario";
        Animal animal = new Animal(
                1337L,
                name,
                "Dog",
                Sex.MALE,
                4,
                "Brown",
                Size.BIG
        );
        underTest.save(animal);

        //when
        Optional<Animal> expected = underTest.findAnimalByName("Lucario");

        //then
        assertTrue(expected.isPresent());
        assertEquals("Lucario", expected.get().getName());

    }

    @Test
    void itShouldCheckIfAnimalNameDoesNotExists(){
        //given
        String name = "Lucario";

        //when
        Optional<Animal> expected = underTest.findAnimalByName("Lucario");

        //then
        assertFalse(expected.isPresent());

    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }


}