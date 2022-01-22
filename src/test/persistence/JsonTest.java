package persistence;

import model.Animal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

        protected void checkAnimal(String name, int age, String type, String health,  Animal animal) {
            assertEquals(name, animal.getName());
            assertEquals(age, animal.getAge());
            assertEquals(type, animal.getType());
            assertEquals(health, animal.getHealthInsuranceType());
        }
    }


