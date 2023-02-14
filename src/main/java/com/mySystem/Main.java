package com.mySystem;
/**
 * Main class is responsible for sending a request to Google Distance Matrix API and handling the response.
 */
public class Main {
    /**
     * The main method sends the request to the API and handles the response.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        RequestMaker requestMaker = new RequestMaker();

        requestMaker.makeRequest(args[0].split(":") , args[1].split(":"));
    }
}