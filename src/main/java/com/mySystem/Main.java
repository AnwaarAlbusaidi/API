package com.mySystem;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
          WriteAndRead writeAndRead = new WriteAndRead();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();

        System.out.println("Enter your origins in term (place name,country name): ");
        urlBuilder.addQueryParameter("origins", scan.nextLine());
        System.out.println("Enter your origins in term (place name,country name): ");
        urlBuilder.addQueryParameter("destinations", scan.nextLine());
        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "YOUR_API_KEY");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();//object chaining (class Builder has three methods url(),get(),build()

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
            writeAndRead.writeToJsonFile(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writeAndRead.ReadFromJsonFile();
    }
}