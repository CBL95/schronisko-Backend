package pl.schronisko.demo.Animal;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

//@Getter
//@Setter
//@AllArgsConstructor

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
    private String sex;
    private Integer age;
    private String color;
    private String size;

    public Animal() {

    }

    public Animal(Long id, String name, String category, String sex, Integer age, String color, String size) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sex = sex;
        this.age = age;
        this.color = color;
        this.size = size;
    }

    public Animal(String name, String category, String sex, Integer age, String color, String size) {
        this.name = name;
        this.category = category;
        this.sex = sex;
        this.age = age;
        this.color = color;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
