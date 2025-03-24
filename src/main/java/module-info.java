module com.javaassignment.week9labapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.javaassignment.week9labapi to javafx.fxml;
    exports com.javaassignment.week9labapi;
}