package persistence;

import exceptions.InvalidHealthInsuranceType;
import model.Animal;
import model.ListOfAnimal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfAnimal read() throws IOException, InvalidHealthInsuranceType {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ListOfAnimal parseWorkRoom(JSONObject jsonObject) throws InvalidHealthInsuranceType {
        String name = jsonObject.getString("name");
        ListOfAnimal listOfAnimal = new ListOfAnimal(name);
        addAnimals(listOfAnimal, jsonObject);
        return listOfAnimal;
    }

    // MODIFIES: listOfAnimal
    // EFFECTS: parses animals from JSON object and adds them to listOfAnimal
    private void addAnimals(ListOfAnimal listOfAnimal, JSONObject jsonObject) throws InvalidHealthInsuranceType {
        JSONArray jsonArray = jsonObject.getJSONArray("animals");
        for (Object json : jsonArray) {
            JSONObject nextAnimal = (JSONObject) json;
            addAnimal(listOfAnimal, nextAnimal);
        }
    }

    // MODIFIES: listOfAnimal
    // EFFECTS: parses animal from JSON object and adds it to listOfAnimals
    private void addAnimal(ListOfAnimal listOfAnimal, JSONObject jsonObject) throws InvalidHealthInsuranceType {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String type = jsonObject.getString("type");
        String health = jsonObject.getString("health");
        Animal animal = new Animal(name);
        animal.updateAge(age);
        animal.updateType(type);
        listOfAnimal.addAnimal(animal);
        animal.updateHealthInsurance(health);
    }
}
