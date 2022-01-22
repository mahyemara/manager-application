package persistence;


import exceptions.InvalidHealthInsuranceType;
import model.Animal;
import model.ListOfAnimal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfAnimal wr = new ListOfAnimal("My Animals");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfAnimal wr = new ListOfAnimal("My Animals");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My Animals", wr.getPetOwnerName());
            assertEquals(0, wr.getListSize());
        } catch (IOException | InvalidHealthInsuranceType e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfAnimal listOfAnimal = new ListOfAnimal("My Animals");
            Animal toothless = new Animal("toothless");
            toothless.updateAge(10);
            toothless.updateType("cat");
            try {
                toothless.updateHealthInsurance("premium");
            } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
                fail("not expected to catch");
            }
            listOfAnimal.addAnimal(toothless);
            Animal coco = new Animal("toothless");
            coco.updateAge(11);
            coco.updateType("bird");
            try {
                coco.updateHealthInsurance("basic");
            } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
                fail("not expected to catch");
            }
            listOfAnimal.addAnimal(coco);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(listOfAnimal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            listOfAnimal = reader.read();
            assertEquals("My Animals", listOfAnimal.getPetOwnerName());
            List<Animal> animals = listOfAnimal.getListOfAnimal();
            assertEquals(2, animals.size());
            checkAnimal("toothless",10,"cat","premium", animals.get(0));
            checkAnimal("toothless",11,"bird","basic", animals.get(1));

        } catch (IOException | InvalidHealthInsuranceType e) {
            fail("Exception should not have been thrown");
        }
    }
}