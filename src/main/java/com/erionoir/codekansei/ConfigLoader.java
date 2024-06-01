package com.erionoir.codekansei;

// Import necessary classes from the Checkstyle library
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

// Import necessary classes from Java's built-in classes
import java.util.Properties;
import java.io.IOException;

// Define the ConfigLoader class
public class ConfigLoader {
    // Define a method to load a Checkstyle configuration from a given URL
    public Configuration loadConfig(String url) throws IOException, CheckstyleException {
        // Get the system properties
        Properties properties = System.getProperties();
        // Load the Checkstyle configuration from the given URL, expanding any properties in the configuration
        Configuration config = ConfigurationLoader.loadConfiguration(url, new PropertiesExpander(properties));
        // Return the loaded configuration
        return config;
    }
}