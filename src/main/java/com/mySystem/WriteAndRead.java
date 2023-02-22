package com.mySystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;

/**
 * The WriteAndRead class provides methods for writing response data to a JSON file
 * and reading the JSON data from the file.
 */
public class WriteAndRead {

    private static final String RESPONSE_FILE_NAME = "response.json";
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
        File file = new File(RESPONSE_FILE_NAME);
        if (!file.exists()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object responseObject = gson.fromJson(responseBody, Object.class);

            try (FileWriter file1 = new FileWriter(RESPONSE_FILE_NAME)) {
                gson.toJson(responseObject, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads the JSON data from the "response.json" file and prints the duration
     * data to the console.
     */

    public HashMap<String, Object> readJsonFile() {
        FileReader reader = null;
        try {
            reader = new FileReader(RESPONSE_FILE_NAME);
            Gson gson = new Gson();
            return gson.fromJson(reader, HashMap.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
