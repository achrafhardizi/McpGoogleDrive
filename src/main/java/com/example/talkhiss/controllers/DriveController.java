package com.example.talkhiss.controllers;

import com.example.talkhiss.services.DriveApiService;
import com.google.api.services.drive.model.File;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/drive")
@AllArgsConstructor
public class DriveController {

    private final DriveApiService driveService;

    @GetMapping("/files")
    public ResponseEntity<List<File>> listFiles(@RequestParam(defaultValue = "10") int pageSize) {
        try {
            List<File> files = driveService.listFiles(pageSize);
            return ResponseEntity.ok(files);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<File> getFile(@PathVariable String fileId) {
        try {
            File file = driveService.getFile(fileId);
            return ResponseEntity.ok(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<File>> searchFiles(@RequestParam String query) {
        try {
            List<File> files = driveService.searchFiles(query);
            return ResponseEntity.ok(files);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/folders")
    public ResponseEntity<File> createFolder(@RequestParam String folderName) {
        try {
            File folder = driveService.createFolder(folderName);
            return ResponseEntity.ok(folder);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}