package edu.mooncoder.model.tools.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class StringFileContent {
    private final String content;
    
    public StringFileContent(File file) throws IOException {
        this.content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    public String getContent() {
        return content;
    }
}
