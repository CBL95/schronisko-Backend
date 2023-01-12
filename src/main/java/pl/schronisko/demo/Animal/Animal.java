package pl.schronisko.demo.Animal;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Getter
@Setter
@ToString



@Entity
@Table
@Builder
@CrossOrigin("http://localhost:8082/")
public class Animal {
    @Id
    @SequenceGenerator(
            name="animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_sequence"
    )

    private Long id;
    private String name;
    private String category;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Integer age;
    private String color;
    @Enumerated(EnumType.STRING)
    private Size size;

    public Animal() {

    }

    public Animal(Long id, String name, String category, Sex sex, Integer age, String color, Size size) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sex = sex;
        this.age = age;
        this.color = color;
        this.size = size;
    }

    public Animal(String name, String category, Sex sex, Integer age, String color, Size size) {
        this.name = name;
        this.category = category;
        this.sex = sex;
        this.age = age;
        this.color = color;
        this.size = size;
    }

}
