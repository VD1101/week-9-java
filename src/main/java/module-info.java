module com.javaassignment.week9labapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    // Optional UI-related modules (you can remove if unused)
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    // Open package for reflection (for FXML + Gson)
    opens com.javaassignment.week9labapi to javafx.fxml, com.google.gson;

    exports com.javaassignment.week9labapi;
}
