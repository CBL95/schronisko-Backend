package pl.schronisko.demo.Animal;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    private AnimalService underTest;
    @Mock
    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
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
    void canRegisterNewAnimal() {
        //given
        Animal animal = new Animal(
                "Doggo",
                Category.Pies,
                Sex.Samiec,
                4,
                "Brown",
                Size.Duży
        );
        //when
        underTest.addNewAnimal(animal);

        //then
        ArgumentCaptor<Animal> animalArgumentCaptor = ArgumentCaptor.forClass(Animal.class);

        verify(animalRepository).save(animalArgumentCaptor.capture());

        Animal capturedAnimal = animalArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedAnimal).isEqualTo(animal);

    }

    @Test
    void shouldThrowIllegalStateException_WhenAnimalNameIsAlreadyTaken() {
        // Given
        Animal animal = new Animal(
                "Lucario",
                Category.Pies,
                Sex.Samiec,
                4,
                "Brown",
                Size.Duży
        );
        when(animalRepository.findAnimalByName("Lucario")).thenReturn(Optional.of(animal));

        // When/Then
        assertThatThrownBy(() -> underTest.addNewAnimal(animal))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("this name is already taken");
    }

    @Test
    void shouldDeleteAnimals_WhenAnimalsExist_ShouldDeleteAnimals() {
        // Given
        Long animalId = 1L;
        Animal animal = new Animal(
                "Doggo",
                Category.Pies,
                Sex.Samiec,
                4,
                "Brown",
                Size.Duży
        );

        when(animalRepository.existsById(animalId)).thenReturn(true);

        // When
        underTest.deleteAnimal(animalId);
        // Then
        verify(animalRepository).deleteById(animalId);

    }

    @Test
    void shouldThrowIllegalArgumentException_WhenAnimalIdIsNull() {
        // Given
        Long animalId = null;

        // When & Then
        assertThatThrownBy(() -> underTest.deleteAnimal(animalId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("id cannot be null");
    }

    @Test
    void shouldThrowIllegalStateException_WhenAnimalsDoesNotExist() {
        // Given
        Long animalId = 1L;
        when(animalRepository.existsById(animalId)).thenReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.deleteAnimal(animalId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("animal with id " + animalId + " does not exists");
        verify(animalRepository, never()).deleteById(animalId);
    }

    @Test
    void updateAnimals_WhenAnimalsExist_ShouldUpdateAnimals() {
        // Given
        Long animalId = 1L;
        Animal animal = new Animal(
                animalId,
                "Doggo",
                Category.Pies,
                Sex.Samiec,
                4,
                "Brown",
                Size.Duży
        );
        when(animalRepository.findById(animalId)).thenReturn(Optional.of(animal));

        // When
        underTest.updateAnimal(animalId, "NewName", Category.Kot, Sex.Samica, 5, "black", Size.Mały);

        // Then
        assertThat(animal.getName()).isEqualTo("NewName");
        assertThat(animal.getCategory()).isEqualTo(Category.Kot);
        assertThat(animal.getAge()).isEqualTo(5);
        assertThat(animal.getSex()).isEqualTo(Sex.Samica);
        assertThat(animal.getColor()).isEqualTo("black");
        assertThat(animal.getSize()).isEqualTo(Size.Mały);
        verify(animalRepository).save(animal);
    }

    @Test
    void updateAnimals_whenAnimalsNotExist_ShouldThrowIllegalStateException() {
        // Given
        Long animalId = 100L;
        // When
        assertThatThrownBy(() -> underTest.updateAnimal(animalId, "NewName", Category.Kot, Sex.Samiec, 5, "black", Size.Mały))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Animal with id " + animalId + " does not exists");
    }
}