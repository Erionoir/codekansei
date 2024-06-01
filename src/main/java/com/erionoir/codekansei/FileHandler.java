package com.erionoir.codekansei;

// Import necessary classes from the Checkstyle library
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.api.Configuration;

// Import necessary classes from Java's built-in classes
import java.io.File;
import java.util.Collections;

// Define the FileHandler class
public class FileHandler {
    // Define a method to analyze a file using a given Checkstyle configuration
    public void analyzeFile(File file, Configuration config) throws Exception {
        // Create a new Checkstyle Checker
        Checker checker = new Checker();
        // Set the class loader for the Checker
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        // Configure the Checker with the given configuration
        checker.configure(config);
        // Add a listener to the Checker to handle audit events
        checker.addListener(new AuditListener() {
            // Handle the auditStarted event
            @Override
            public void auditStarted(AuditEvent event) {}
            // Handle the auditFinished event
            @Override
            public void auditFinished(AuditEvent event) {}
            // Handle the fileStarted event
            @Override
            public void fileStarted(AuditEvent event) {}
            // Handle the fileFinished event
            @Override
            public void fileFinished(AuditEvent event) {}
            // Handle the addError event
            @Override
            public void addError(AuditEvent event) {
                // Print the line number and message of the error
                System.out.println("Error at line " + event.getLine() + ": " + event.getMessage());
            }
            // Handle the addException event
            @Override
            public void addException(AuditEvent event, Throwable throwable) {
                // Print the message of the exception
                System.out.println("Exception: " + throwable.getMessage());
            }
        });
        // Process the file with the Checker
        checker.process(Collections.singletonList(file));
    }
}