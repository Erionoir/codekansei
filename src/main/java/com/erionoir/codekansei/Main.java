package com.erionoir.codekansei;
import com.erionoir.codekansei.FileHandler;
import com.erionoir.codekansei.ConfigLoader;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Files;

// Main class that extends the JavaFX Application class
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        // Add a filter to only show Java files
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files", "*.java"));
        // Open the file chooser dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        // If a file was selected
        if (selectedFile != null) {
            // Create a new FileHandler
            FileHandler fileHandler = new FileHandler();
            // Create a new ConfigLoader
            ConfigLoader configLoader = new ConfigLoader();
            try {
                // Define the URL of the checkstyle configuration, it is using sun_checks.xml from the Checkstyle repository.
                URL website = new URL("https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/sun_checks.xml");
                // Open a stream to the URL
                InputStream in = website.openStream();
                // Create a temporary file to store the configuration
                Path tempFile = Files.createTempFile("config", ".xml");
                // Copy the configuration from the URL to the temporary file
                Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
                // Load the configuration
                Configuration config = configLoader.loadConfig(tempFile.toString());
                // Analyze the selected file with the loaded configuration
                fileHandler.analyzeFile(selectedFile, config);
            } catch (Exception e) {
                // Print any exceptions that occur
                e.printStackTrace();
            }
        }
    }

    // Main method that launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}