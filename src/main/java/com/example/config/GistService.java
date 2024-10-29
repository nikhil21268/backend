package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class GistService {

    @Value("${github.token}")
    private String githubToken;

    public String createSecretGist(String markdownContent, String fileName) {
        String apiUrl = "https://api.github.com/gists";
        Map<String, Object> payload = new HashMap<>();
        payload.put("description", "Project Summary");
        payload.put("public", false);

        Map<String, Object> files = new HashMap<>();
        Map<String, String> fileContent = new HashMap<>();
        fileContent.put("content", markdownContent);
        files.put(fileName, fileContent);
        payload.put("files", files);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "token " + githubToken);

        // Use a custom request factory or interceptor to add headers
        // For simplicity, this is omitted here

        Map<String, Object> response = restTemplate.postForObject(apiUrl, payload, Map.class);
        return (String) response.get("html_url");
    }

    public void saveMarkdownToFile(String content, String projectTitle) {
        String fileName = projectTitle + ".md";
        Path path = Paths.get("exported_gists/" + fileName);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
