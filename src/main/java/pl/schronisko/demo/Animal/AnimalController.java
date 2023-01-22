package pl.schronisko.demo.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping(path = "api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @PostMapping()
    public void registerNewAnimal(@RequestBody Animal animal) {
        animalService.addNewAnimal(animal);
    }

    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long animalId) {
        animalService.deleteAnimal(animalId);
    }

    @PutMapping(path = "{animalId}")
    public void updateAnimal(
            @PathVariable("animalId") Long animalId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Sex sex,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Size size) {
        animalService.updateAnimal(animalId, name, category, sex, age, color, size);
    }
}


