package com.javaassignment.week9labapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class SpaceXFetcher {
    public static void main(String[] args) {
        String uri = "https://api.spacexdata.com/v5/launches/latest";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String jsonString = response.body();

            System.out.println("Response from SpaceX API:");
            System.out.println(jsonString);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
