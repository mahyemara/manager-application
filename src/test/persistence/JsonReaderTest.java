package persistence;


import exceptions.InvalidHealthInsuranceType;
import model.ListOfAnimal;
import model.Animal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfAnimal listOfAnimal = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected to catch exception
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ListOfAnimal listOfAnimal = reader.read();
            assertEquals("My Animals", listOfAnimal.getPetOwnerName());
            assertEquals(0, listOfAnimal.getListSize());
        } catch (IOException | InvalidHealthInsuranceType e) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ListOfAnimal listOfAnimal = reader.read();
            assertEquals("My Animals", listOfAnimal.getPetOwnerName());
            List<Animal> animals = listOfAnimal.getListOfAnimal();
            assertEquals(2, animals.size());
            checkAnimal("toothless", 10, "cat", "premium", animals.get(0));
            checkAnimal("coco", 11, "bird", "basic", animals.get(1));
        } catch (IOException | InvalidHealthInsuranceType e) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderGeneralWorkRoomExceptionUpdate() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoomExc.json");
        try {
            ListOfAnimal listOfAnimal = reader.read();
            assertEquals("My Animals", listOfAnimal.getPetOwnerName());
            List<Animal> animals = listOfAnimal.getListOfAnimal();
            assertEquals(1, animals.size());
            checkAnimal("toothless", 10, "cat", "notBasicOrPremiumOrNull", animals.get(0));
            fail("should've caught exception!");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            // expected!
        }
    }
}