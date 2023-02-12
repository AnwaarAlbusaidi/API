package com.mySystem;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();
        urlBuilder.addQueryParameter("origins", "TRA, Muscat");
        urlBuilder.addQueryParameter("destinations", "Oman,Adam");
        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "YOUR_API_KEY"/*"AIzaSyBfsoqSjTk1B9TokwfMTmg2NPsO2WhO638"*/);

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();//object chaining (class Builder has three methods url(),get(),build()

//        try (Response response = client.newCall(request).execute()) {
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        FileReader reader = null;
        try {
            reader = new FileReader("gmap_distance_matrix_response.json");
            Gson gson = new Gson();
            HashMap<String, Object> map = gson.fromJson(reader, HashMap.class);

            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) map.get("rows");
            ArrayList<Map<String, Object>> elements = (ArrayList<Map<String, Object>>) rows.get(0).get("elements");
            Map<String, Object> duration = (Map<String, Object>) elements.get(0).get("duration");
            String durationText = (String) duration.get("text");

            System.out.println("duration: " + durationText);
          //  System.out.println(map);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}