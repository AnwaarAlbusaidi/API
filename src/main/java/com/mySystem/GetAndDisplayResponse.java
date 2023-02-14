package com.mySystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetAndDisplayResponse {
    WriteAndRead readFile = new WriteAndRead();
    public void DisplayResponse(){
        HashMap<String, Object> map = readFile.readJsonFile();
        System.out.println(map.toString());

    }

    public void getDurationText() {
        HashMap<String, Object> map = readFile.readJsonFile();
        ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) map.get("rows");
        ArrayList<Map<String, Object>> elements = (ArrayList<Map<String, Object>>) rows.get(0).get("elements");
        Map<String, Object> duration = (Map<String, Object>) elements.get(0).get("duration");

        System.out.println("duration: " + duration.get("text"));
    }

}
