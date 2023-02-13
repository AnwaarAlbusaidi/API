package com.mySystem;
import okhttp3.*;
import java.io.IOException;
/**
 * Main class is responsible for sending a request to Google Distance Matrix API and handling the response.
 */
public class Main {
    /**
     * The main method sends the request to the API and handles the response.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        UserInputHandler userInput = new UserInputHandler();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
          WriteAndRead writeAndRead = new WriteAndRead();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();

        System.out.println("Enter your origins in term (place name,country name): ");
        String origin = userInput.getUserChoiceString();
        urlBuilder.addQueryParameter("origins", origin);
        System.out.println("Enter your origins in term (place name,country name): ");
        String destination = userInput.getUserChoiceString();
        urlBuilder.addQueryParameter("destinations", destination);
        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "YOUR_API_KEY");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();//object chaining (class Builder has three methods url(),get(),build()

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
         //   System.out.println(responseBody);
           //   writeAndRead.writeToJsonFile(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writeAndRead.ReadFromJsonFile();
    }
}