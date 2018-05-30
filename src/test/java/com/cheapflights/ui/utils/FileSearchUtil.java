package com.cheapflights.ui.utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSearchUtil {


    public static List<String> getDirectoryFiles(String folderPath, String extension){
        List<String> files = new ArrayList<>();
        try {
            DirectoryStream<Path> stream= Files.newDirectoryStream(Paths.get(folderPath),
                    path -> path.toString().endsWith(extension));
            for (Path file : stream){
                files.add(file.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


}
