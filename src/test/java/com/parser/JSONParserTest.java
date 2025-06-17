package com.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {
    private JSONParser parser;
    
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        parser = new JSONParser();
    }

    @Test
    void testWriteAndReadPerson() throws IOException {
        // Create test data
        Person person = new Person("John Doe", 30);
        File outputFile = tempDir.resolve("person.json").toFile();

        // Write to file
        parser.writeJsonToFile(outputFile.getPath(), person);

        // Read from file
        Person loadedPerson = parser.readJsonFromFile(outputFile.getPath(), Person.class);

        // Verify
        assertNotNull(loadedPerson);
        assertEquals("John Doe", loadedPerson.getName());
        assertEquals(30, loadedPerson.getAge());
    }

    @Test
    void testWriteAndReadPersonList() throws IOException {
        // Create test data
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 35)
        );
        File outputFile = tempDir.resolve("people.json").toFile();

        // Write to file
        parser.writeJsonToFile(outputFile.getPath(), people);

        // Read from file
        List<Person> loadedPeople = parser.readJsonListFromFile(outputFile.getPath(), Person.class);

        // Verify
        assertNotNull(loadedPeople);
        assertEquals(2, loadedPeople.size());
        assertEquals("Alice", loadedPeople.get(0).getName());
        assertEquals(25, loadedPeople.get(0).getAge());
        assertEquals("Bob", loadedPeople.get(1).getName());
        assertEquals(35, loadedPeople.get(1).getAge());
    }

    @Test
    void testWriteAndReadMap() throws IOException {
        // Create test data
        Map<String, Object> data = Map.of(
            "name", "John",
            "age", 30,
            "city", "New York"
        );
        File outputFile = tempDir.resolve("data.json").toFile();

        // Write to file
        parser.writeJsonToFile(outputFile.getPath(), data);

        // Read from file
        Map<String, Object> loadedData = parser.readJsonFromFile(outputFile.getPath());

        // Verify
        assertNotNull(loadedData);
        assertEquals("John", loadedData.get("name"));
        assertEquals(30, loadedData.get("age"));
        assertEquals("New York", loadedData.get("city"));
    }

    @Test
    void testObjectToJsonString() throws IOException {
        Person person = new Person("John Doe", 30);
        String jsonString = parser.objectToJsonString(person);
        
        assertTrue(jsonString.contains("\"name\":\"John Doe\""));
        assertTrue(jsonString.contains("\"age\":30"));
    }

    @Test
    void testJsonStringToObject() throws IOException {
        String jsonString = "{\"name\":\"John Doe\",\"age\":30}";
        Person person = parser.jsonStringToObject(jsonString, Person.class);
        
        assertNotNull(person);
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
    }
} 