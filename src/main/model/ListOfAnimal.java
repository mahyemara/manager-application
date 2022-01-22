package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Represents ListOfAnimal Pet Owner owns
public class ListOfAnimal implements Writable {
    private LinkedList<Animal> listOfAnimal;
    private Animal animal;
    private String petOwnerName;

    // constructor
    // MODIFIES: this
    // EFFECTS: assigns animal to new LinkedList<>()
    public ListOfAnimal(String petOwnerName) {
        this.petOwnerName = petOwnerName;
        listOfAnimal = new LinkedList<>();
    }

    // REQUIRES: animal not in list
    // MODIFIES: this
    // EFFECTS: adds newAnimal to animals
    public void addAnimal(Animal newAnimal) {
        if (!(animalIsInList(newAnimal))) {
            listOfAnimal.add(newAnimal);
        }
    }

    // EFFECTS: returns true iff newAnimal is in animals
    public boolean animalIsInList(Animal newAnimal) {
        return listOfAnimal.contains(newAnimal);
    }

    // EFFECTS: returns true iff there exists an animal whose name is nextInput
    public boolean checkDuplicateName(String nextInput) {
        boolean result = false;
        for (Animal pet : listOfAnimal) {
            result = pet.getName().equals(nextInput);
        }
        return result;
    }

    // MODIFIES: this
    // EFFECTS: if animal is in list, it would replace animal in list
    public void replacePet(Animal animal) {
        for (int i = 0; i < getListSize(); i++) {
            if (animalIsInList(animal)) {
                listOfAnimal.set(i, animal);
            }
        }
    }

    // EFFECTS: returns LinkedList<String> containing all animal names in animals
    public LinkedList<String> getAnimalNamesInList() {
        LinkedList<String> listOfNames = new LinkedList<>();
        for (Animal animal : listOfAnimal) {
            listOfNames.add(animal.getName());
        }
        return listOfNames;
    }

    // EFFECTS: returns animal whose name is the same as name
    public Animal getAnimalInList(String name) {
        Animal animalFound = new Animal("");
        for (Animal a : listOfAnimal) {
            if (a.getName().equals(name)) {
                animalFound = a;
            } else {
                return animalFound;
            }
        }
        return animalFound;
    }


    public String getPetOwnerName() {
        return petOwnerName;
    }

    // EFFECTS: returns true iff animals is Empty
    public boolean isEmpty() {
        return listOfAnimal.isEmpty();
    }

    // EFFECTS: returns animals size
    public int getListSize() {
        return listOfAnimal.size();
    }

    // EFFECTS: returns LinkedList<Animal> animals
    public LinkedList<Animal> getListOfAnimal() {
        return listOfAnimal;
    }

    // MODIFIES: this
    // EFFECTS: remove animal with name from listOfAnimals
    public String removePet(String name) {
        String output;
        animal = getAnimalInList(name);
        if (!(animalIsInList(animal))) {
            output = "This animal is not in list. No animal has been removed.";
        } else {
            listOfAnimal.remove(animal);
            output = "Your pet has been removed successfully\n";
        }
        return output;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", petOwnerName);
        json.put("animals", animalsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray animalsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Animal animal : listOfAnimal) {
            jsonArray.put(animal.toJson());
        }
        return jsonArray;
    }
}