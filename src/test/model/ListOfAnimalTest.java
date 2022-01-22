package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfAnimalTest {

    private ListOfAnimal animals;
    private Animal newAnimal;
    private Animal newAnimal2;
    private LinkedList<String> listOfNames;
    private LinkedList<Animal> listOfAnimals;

    @BeforeEach
    public void setUp() {
        animals = new ListOfAnimal("Your Pets");
        newAnimal = new Animal("toothless");
        newAnimal2 = new Animal("coco");
        listOfNames = new LinkedList<>();
        listOfAnimals = new LinkedList<>();
    }

    @Test
    // Testing addAnimal() AND animalIsInList() AND getListSize() AND isEmpty()
    public void testAddAnimal() {

        assertTrue(animals.isEmpty());
        animals.addAnimal(newAnimal);
        assertTrue(animals.animalIsInList(newAnimal));
        assertEquals(animals.getListSize(), 1);
        assertFalse(animals.isEmpty());

        // add newAnimal again
        animals.addAnimal(newAnimal);
        assertEquals(animals.getListSize(), 1);

        // add another animal
        newAnimal2 = new Animal("coco");
        animals.addAnimal(newAnimal2);
        assertTrue(animals.animalIsInList(newAnimal2));
        assertEquals(animals.getListSize(), 2);
    }
    @Test
    public void testReplacePet() {

        animals.addAnimal(newAnimal);

        // replace animal in list
        animals.replacePet(newAnimal);
        assertEquals(animals.getListSize(), 1);


        // replace animal not in list
        animals.replacePet(newAnimal2);
        assertEquals(animals.getListSize(), 1);
    }

    @Test
    public void testGetAnimalNamesInList() {

        animals.addAnimal(newAnimal);
        animals.addAnimal(newAnimal2);
        listOfNames.add(newAnimal.getName());
        listOfNames.add(newAnimal2.getName());

        // test get names in list
        animals.getAnimalNamesInList();
        assertEquals(animals.getAnimalNamesInList(), listOfNames);
    }

    @Test
    public void testGetAnimals() {
        animals.addAnimal(newAnimal);
        animals.addAnimal(newAnimal2);
        listOfAnimals.add(newAnimal);
        listOfAnimals.add(newAnimal2);

        // replace animal in list
        animals.getListOfAnimal();
        assertEquals(animals.getListOfAnimal(), listOfAnimals);
    }

    @Test
    public void testGetAnimalInListOfAnimal() {

        // animal name in list
        animals.addAnimal(newAnimal);
        assertEquals(animals.getAnimalInList(newAnimal.getName()), newAnimal);

        // Animal name not in list
        Animal newAnimal3 = animals.getAnimalInList(newAnimal.getName());
        assertEquals(animals.getAnimalInList(newAnimal.getName()), newAnimal3);
    }

    @Test
    public void testcheckDuplicateName() {

        animals.addAnimal(newAnimal);
        assertTrue(animals.checkDuplicateName(newAnimal.getName()));
        assertFalse(animals.checkDuplicateName(newAnimal2.getName()));
    }

    @Test
    public void testRemoveAnimal() {
        // add 1 pet, remove 1 pet
        animals.addAnimal(newAnimal);
        animals.removePet(newAnimal.getName());
        assertTrue(animals.isEmpty());

        // add 2 pets, remove 2 pets
        animals.addAnimal(newAnimal);
        animals.addAnimal(newAnimal2);
        animals.removePet(newAnimal.getName());
        animals.removePet(newAnimal2.getName());
        assertTrue(animals.isEmpty());

        // add 2 pets, remove 1 pet
        animals.addAnimal(newAnimal);
        animals.addAnimal(newAnimal2);
        animals.removePet(newAnimal.getName());
        List<String> animalNames = new LinkedList<>();
        animalNames.add("coco");
        assertEquals(animalNames, animals.getAnimalNamesInList());

        // remove pet not in list
        animals.removePet(newAnimal.getName());
        assertEquals("This animal is not in list. No animal has been removed.",
                animals.removePet(newAnimal.getName()));
    }
}