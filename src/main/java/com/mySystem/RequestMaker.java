package com.mySystem;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class RequestMaker {

    public void makeRequest( String [] origin,String [] destination) {
        WriteAndRead writeAndRead = new WriteAndRead();
        UserInputHandler userInput = new UserInputHandler();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();

        urlBuilder.addQueryParameter(origin[0], origin[1]);
        urlBuilder.addQueryParameter(destination[0], destination[1]);

        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "YOUR_API_KEY");

        String filename = "response.json";
        File file = new File(filename);

        // if the file doesn't exist, make a new request and save the response to file
        if (!file.exists()) {
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

        // read the response from file
        writeAndRead.ReadFromJsonFile();
    }
}

