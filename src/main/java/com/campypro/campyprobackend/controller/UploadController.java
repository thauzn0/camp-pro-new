package com.campypro.campyprobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    @PostMapping
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Map.of("message", "Dosya seçilmedi!");
        }
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetPath = Paths.get(uploadDir).resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

            return Map.of("fileName", fileName, "url", fileUrl);
        } catch (IOException e) {
            return Map.of("message", "Dosya yüklenemedi: " + e.getMessage());
        }
    }

    // 🆕 GET endpoint: uploads klasöründeki tüm dosyaları listeler
    @GetMapping
    public ResponseEntity<List<String>> listUploadedFiles() {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists() || dir.listFiles() == null) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            List<String> fileUrls = Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                    .filter(File::isFile)
                    .map(file -> ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/")
                            .path(file.getName())
                            .toUriString())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileUrls);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(List.of("Hata oluştu: " + e.getMessage()));
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {
        Path file = Paths.get(uploadDir).resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}