package pl.schronisko.demo.Animal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {


    private AnimalService underTest;
    @Mock private AnimalRepository animalRepository;

    @BeforeEach
    void setUp(){
        underTest = new AnimalService(animalRepository);
    }

    @Test
    void canGetAllAnimals() {
        // when
        underTest.getAnimals();
        //then
        verify(animalRepository).findAll(Sort.by("id"));
    }

    @Test
    @Disabled
    void canRegisterNewAnimal() {
        //given
        Animal animal = new Animal(
                "Doggo",
                "Dog",
                "Male",
                4,
                "Brown",
                "Big"
        );
        //when
        underTest.addNewAnimal(animal);

        //then
        ArgumentCaptor<Animal> animalArgumentCaptor = ArgumentCaptor.forClass(Animal.class);

        verify(animalRepository).save(animalArgumentCaptor.capture());

        Animal capturedAnimal = animalArgumentCaptor.getValue();

        assertThat(capturedAnimal).isEqualTo(animal);

    }

    @Test
    @Disabled
    void deleteAnimal() {
    }

    @Test
    @Disabled
    void updateAnimal() {
    }
}