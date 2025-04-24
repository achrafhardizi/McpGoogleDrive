package com.example.talkhiss.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class DriveApiConfig {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Value("${google.service.account.key}")
    private String serviceAccountKeyPath;

    @Bean
    public Drive driveService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        
        // Load service account credentials
        InputStream in = new ClassPathResource(serviceAccountKeyPath).getInputStream();
        if (in == null) {
            throw new IOException("Resource not found: " + serviceAccountKeyPath);
        }
        
        // Create credentials from service account key file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(in)
                .createScoped(SCOPES);
        
        // Build the Drive service
        return new Drive.Builder(
                HTTP_TRANSPORT, 
                JSON_FACTORY, 
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(applicationName)
                .build();
    }
}
