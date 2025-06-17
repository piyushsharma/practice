package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private final ObjectMapper objectMapper;

    public JSONParser() {
        this.objectMapper = new ObjectMapper();
        // Enable pretty printing for better readability
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Read JSON from a file into a Map
     * @param filePath Path to the JSON file
     * @return Map containing the JSON data
     */
    public Map<String, Object> readJsonFromFile(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Read JSON from a file into a specific class
     * @param filePath Path to the JSON file
     * @param valueType Class to deserialize into
     * @return Object of the specified class
     */
    public <T> T readJsonFromFile(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), valueType);
    }

    /**
     * Read JSON from a file into a List of objects
     * @param filePath Path to the JSON file
     * @param valueType Class of the list elements
     * @return List of objects
     */
    public <T> List<T> readJsonListFromFile(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), 
            objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    /**
     * Write an object to a JSON file
     * @param filePath Path where the JSON file will be written
     * @param object Object to serialize
     */
    public void writeJsonToFile(String filePath, Object object) throws IOException {
        objectMapper.writeValue(new File(filePath), object);
    }

    /**
     * Convert an object to a JSON string
     * @param object Object to serialize
     * @return JSON string representation
     */
    public String objectToJsonString(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Convert a JSON string to an object
     * @param jsonString JSON string to parse
     * @param valueType Class to deserialize into
     * @return Object of the specified class
     */
    public <T> T jsonStringToObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }

    // Example usage
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();

            // Example 1: Reading and writing a simple object
            Person person = new Person("John Doe", 30);
            parser.writeJsonToFile("person.json", person);
            Person loadedPerson = parser.readJsonFromFile("person.json", Person.class);
            System.out.println("Loaded person: " + loadedPerson);

            // Example 2: Reading and writing a list of objects
            List<Person> people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 35)
            );
            parser.writeJsonToFile("people.json", people);
            List<Person> loadedPeople = parser.readJsonListFromFile("people.json", Person.class);
            System.out.println("Loaded people: " + loadedPeople);

            // Example 3: Reading and writing a Map
            Map<String, Object> data = Map.of(
                "name", "John",
                "age", 30,
                "city", "New York"
            );
            parser.writeJsonToFile("data.json", data);
            Map<String, Object> loadedData = parser.readJsonFromFile("data.json");
            System.out.println("Loaded data: " + loadedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Example class for demonstration
class Person {
    private String name;
    private int age;

    // Default constructor required for Jackson
    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters required for Jackson
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
