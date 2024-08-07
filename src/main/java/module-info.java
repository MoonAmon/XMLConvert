module org.noom.xmlconvert {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.noom.xmlconvert to javafx.fxml;
    exports org.noom.xmlconvert;
}