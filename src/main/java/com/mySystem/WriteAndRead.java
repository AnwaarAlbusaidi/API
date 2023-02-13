package com.mySystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The WriteAndRead class provides methods for writing response data to a JSON file
 * and reading the JSON data from the file.
 */
public class WriteAndRead {

    /**
     * Constructor for WriteAndRead class
     */
    public WriteAndRead() {
    }

    /**
     * Writes the given response body data to a JSON file.
     * @param responseBody The response body to be written to the JSON file.
     */
    public void writeToJsonFile(String responseBody) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object responseObject = gson.fromJson(responseBody, Object.class);

        try (FileWriter file = new FileWriter("response.json")) {
            gson.toJson(responseObject, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the JSON data from the "response.json" file and prints the duration
     * data to the console.
     */
    public void ReadFromJsonFile() {
        FileReader reader = null;
        try {
            reader = new FileReader("response.json");
            Gson gson = new Gson();
            HashMap<String, Object> map = gson.fromJson(reader, HashMap.class);

            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) map.get("rows");
            ArrayList<Map<String, Object>> elements = (ArrayList<Map<String, Object>>) rows.get(0).get("elements");
            Map<String, Object> duration = (Map<String, Object>) elements.get(0).get("duration");
            String durationText = (String) duration.get("text");

            System.out.println("duration: " + durationText);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
