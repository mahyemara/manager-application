package model;

import exceptions.InvalidHealthInsuranceType;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// ROBUST CLASS!
// Represents Animal Pet Owner owns
public class Animal implements Writable {
    private String healthInsuranceType;
    private LinkedList<String> vaccinesTaken;
    private String name;
    private int age;
    private String type;

    // constructor
    // MODIFIES: this
    // EFFECTS: specify animal name, set healthInsuranceType = none, vaccines taken = empty and age =0.
    public Animal(String name) {
        this.name = name;
        this.type = "";
        this.healthInsuranceType = "none";
        this.vaccinesTaken = new LinkedList<>();
        this.age = 0;
    }

    // EFFECTS: returns a new Animal with givenName
    public Animal petWithName(String givenName) {
        return new Animal(givenName);
    }

    // MODIFIES: this
    // EFFECTS: modify animal's type with givenType
    public void updateType(String givenType) {
        this.type = givenType;
    }

    // MODIFIES: this
    // EFFECTS: modifies healthInsuranceType to updatedHealthInfo
    public void updateHealthInsurance(String updatedHealthInfo) throws InvalidHealthInsuranceType {
        if (basicOrPremiumOrNone(updatedHealthInfo)) {
            healthInsuranceType = updatedHealthInfo;
        } else {
            throw new InvalidHealthInsuranceType();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds vaccine to vaccinesTaken
    public void updateVaccinesTaken(String vaccine) {
        vaccinesTaken.add(vaccine);
    }

    // MODIFIES: this
    // EFFECTS: modifies age to newAge
    public void updateAge(int newAge) {
        age = newAge;
    }

    // EFFECTS: returns true only if healthInsuranceType is basic, premium, or none
    public boolean basicOrPremiumOrNone(String healthInsuranceType) {
        return (healthInsuranceType.equals("basic")
                || (healthInsuranceType.equals("premium"))
                || (healthInsuranceType.equals("none")));
    }

    // EFFECTS: returns healthInsuranceType
    public String getHealthInsuranceType() {
        return this.healthInsuranceType;
    }

    // EFFECTS: returns vaccinesTaken
    public LinkedList<String> getVaccinesTaken() {
        return this.vaccinesTaken;
    }

    // EFFECTS: returns name
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns age
    public int getAge() {
        return this.age;
    }

    // EFFECTS: returns type
    public String getType() {
        return this.type;
    }

    // EFFECTS: creates a JSON Object from animal information
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("type", type);
        json.put("health", healthInsuranceType);
        json.put("vaccines", vaccinesTaken);
        return json;
    }

}