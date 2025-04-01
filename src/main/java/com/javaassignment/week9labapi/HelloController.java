package com.javaassignment.week9labapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Desktop;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    @FXML
    private Label launchInfoLabel;

    @FXML
    private ImageView launchImageView;

    @FXML
    private Hyperlink wikipediaLink;

    @FXML
    protected void loadLaunchData() {
        new Thread(() -> {
            try {
                String uri = "https://api.spacexdata.com/v5/launches/latest";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String jsonString = response.body();

                JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

                String name = jsonObject.get("name").getAsString();
                int flightNumber = jsonObject.get("flight_number").getAsInt();
                String dateUtc = jsonObject.get("date_utc").getAsString();
                boolean success = jsonObject.get("success").getAsBoolean();

                String imageUrl = "";
                JsonObject links = jsonObject.getAsJsonObject("links");
                if (links != null && links.has("patch")) {
                    JsonObject patch = links.getAsJsonObject("patch");
                    if (patch.has("small") && !patch.get("small").isJsonNull()) {
                        imageUrl = patch.get("small").getAsString();
                    }
                }

                String wikipedia = "";
                if (links.has("wikipedia") && !links.get("wikipedia").isJsonNull()) {
                    wikipedia = links.get("wikipedia").getAsString();
                }

                Launch launch = new Launch(name, flightNumber, dateUtc, success, wikipedia, imageUrl);

                Platform.runLater(() -> {
                    launchInfoLabel.setText("🚀 " + launch.getName()
                            + "\n✈️ Flight #: " + launch.getFlightNumber()
                            + "\n🗓 Date: " + launch.getDateUtc()
                            + "\n✅ Success: " + launch.isSuccess());

                    if (!launch.getImageUrl().isEmpty()) {
                        launchImageView.setImage(new Image(launch.getImageUrl()));
                    }

                    if (!launch.getWikipedia().isEmpty()) {
                        wikipediaLink.setText("Open Wikipedia");
                        wikipediaLink.setOnAction(e -> {
                            try {
                                Desktop.getDesktop().browse(new URI(launch.getWikipedia()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        wikipediaLink.setVisible(true);
                    } else {
                        wikipediaLink.setVisible(false);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
