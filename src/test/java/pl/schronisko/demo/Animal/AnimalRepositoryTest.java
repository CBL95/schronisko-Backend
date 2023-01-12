package pl.schronisko.demo.Animal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }


}