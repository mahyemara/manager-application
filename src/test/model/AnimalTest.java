package model;

import java.util.LinkedList;

import exceptions.InvalidHealthInsuranceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private Animal newAnimal;
    private Animal newAnimal2;

    @BeforeEach
    public void setUp() {
        newAnimal = new Animal("toothless");
        newAnimal2 = new Animal("coco");
    }


    @Test
    // Testing petWithName() AND getName()
    public void testPetWithName() {
        newAnimal2 = newAnimal.petWithName("toothless");
        assertEquals(newAnimal2.getName(), newAnimal.getName());
    }

    @Test
    // Testing updateType() AND getType()
    public void testUpdatePet() {
        newAnimal.updateType("bird");
        assertEquals("bird", newAnimal.getType());
    }


    @Test
    // Testing updateHealthInsurance() AND getHealthInsuranceType()
    public void testUpdateHealthInfo() {
        // updatedHealthInfo is either basic or premium or none
        try {
            newAnimal.updateHealthInsurance("basic");
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            fail("not supposed to catch!");
        }
        assertEquals("basic", newAnimal.getHealthInsuranceType());
    }

    @Test
    public void testUpdateHealthInfo2() {
        // updatedHealthInfo is neither basic nor premium nor none
        try {
            newAnimal.updateHealthInsurance("what");
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            // expected to catch
        }
        assertEquals("none", newAnimal.getHealthInsuranceType());
    }

    @Test
    // Test updateVaccinesTaken AND getVaccinesTaken()
    public void TestUpdateVaccinesTaken() {
        newAnimal.updateVaccinesTaken("rabies");
        LinkedList<String> vaccines = new LinkedList<>();
        vaccines.add("rabies");
        assertEquals(newAnimal.getVaccinesTaken().size(),1);
        assertEquals(newAnimal.getVaccinesTaken(),vaccines);
    }

    @Test
    // testing updateAge() AND getAge()
    public void testUpdateAge(){
        newAnimal.updateAge(20);
        assertEquals(20, newAnimal.getAge());
    }

    @Test
    public void testBasicOrPremiumOrNone(){
        assertFalse(newAnimal.basicOrPremiumOrNone("what"));
        assertTrue(newAnimal.basicOrPremiumOrNone("basic"));
        assertTrue(newAnimal.basicOrPremiumOrNone("premium"));
        assertTrue(newAnimal.basicOrPremiumOrNone("none"));

    }
}