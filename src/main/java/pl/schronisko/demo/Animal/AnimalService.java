package pl.schronisko.demo.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class AnimalService {


    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll(Sort.by("id"));
    }

    public void addNewAnimal(Animal animal) {
        Optional<Animal> animalOptional = animalRepository.findAnimalByName(animal.getName());
        if (animalOptional.isPresent()){
            throw new IllegalStateException("this name is already taken");
        }
        animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
          boolean exists =  animalRepository.existsById(animalId);
                  if (!exists) {
                      throw new IllegalStateException("animal with id " + animalId + " does not exists");
                  }
                  animalRepository.deleteById(animalId);
    }

    @Transactional
    public void updateAnimal(Long animalId, String name, String category, Sex sex, Integer age, String color, Size size){
        Animal animal = animalRepository.findById(animalId).orElseThrow(()->new IllegalStateException(
                "Animal with id " + animalId + " does not exists"));
        if (name !=null && name.length()>0 && !Objects.equals(animal.getName(),name)
        ){
            animal.setName(name);
        }
        if (category !=null && category.length()>0 && !Objects.equals(animal.getCategory(),category)
        ){
            animal.setCategory(category);
        }
        if (sex !=null  && !Objects.equals(animal.getSex(),sex)
        ){
            animal.setSex(sex);
        }
        if (age !=null && age>0 && !Objects.equals(animal.getAge(),age)
        ){
            animal.setAge(age);
        }
        if (color !=null && color.length()>0 && !Objects.equals(animal.getColor(),color)
        ){
            animal.setColor(color);
        }
        if (size !=null && !Objects.equals(animal.getSize(),size)
        ){
            animal.setSize(size);
        }
    }

}
