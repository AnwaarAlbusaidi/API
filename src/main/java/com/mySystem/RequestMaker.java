package com.mySystem;

import okhttp3.*;

import java.io.IOException;

/**
 * The RequestMaker class is responsible for making HTTP requests to the Google Distance Matrix API and
 * writing the response to a JSON file.
 */
public class RequestMaker {

    /**
     * Sends a HTTP GET request to the Google Distance Matrix API with the specified origin and destination
     * locations, and writes the response to a file if it doesn't already exist. Then reads the response from
     * the file and prints it to the console.
     * @param origin      an array of two Strings specifying the origin location in the format ["parameter", "value"].
     * @param destination an array of two Strings specifying the destination location in the format ["parameter", "value"].
     */
    public void makeRequest(String[] origin, String[] destination) {
        WriteAndRead writeAndRead = new WriteAndRead();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();

        urlBuilder.addQueryParameter(origin[0], origin[1]);
        urlBuilder.addQueryParameter(destination[0], destination[1]);

        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "YOUR_API_KEY");

            Request request = new Request.Builder()
                    .url(urlBuilder.build().toString())
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                writeAndRead.writeToJsonFile(responseBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
