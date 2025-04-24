package com.example.talkhiss.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriveApiService {

    private final Drive driveService;

    public List<File> listFiles(int pageSize) throws IOException {
        FileList result = driveService.files().list()
                .setPageSize(pageSize)
                .setFields("nextPageToken, files(id, name, mimeType, size, createdTime)")
                .execute();
        return result.getFiles();
    }
    
    public File getFile(String fileId) throws IOException {
        return driveService.files().get(fileId).execute();
    }
    
    // Method to search files by name
    public List<File> searchFiles(String query) throws IOException {
        FileList result = driveService.files().list()
                .setQ("name contains '" + query + "'")
                .setFields("nextPageToken, files(id, name, mimeType, size, createdTime)")
                .execute();
        return result.getFiles();
    }
    
    // Method to create a folder
    public File createFolder(String folderName) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        return driveService.files().create(fileMetadata)
                .setFields("id, name")
                .execute();
    }
}