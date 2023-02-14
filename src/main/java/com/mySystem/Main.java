package com.mySystem;

/**
 * The main class of the program.
 */
public class Main {
    /**
     * The main method of the program. Creates a {@link RequestMaker} object and calls its
     * {@link RequestMaker#makeRequest(String[], String[])} method with the command line arguments.
     * @param args the command line arguments. The first argument should be of the form "parameter:value"
     *             and specify the origin location. The second argument should be of the same form and
     *             specify the destination location.
     */
    public static void main(String[] args) {
        RequestMaker requestMaker = new RequestMaker();
        requestMaker.makeRequest(args[0].split(":"), args[1].split(":"));
    }
}
