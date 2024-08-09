package org.noom.xmlconvert;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public static File[] folderSearch(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        // Verify if the directory is valid
        if (folder.isDirectory() && files != null) {
            return files;
        } else {
            return null;
        }
    }

    public static List<File> getTxtFiles(File[] files) {
        List<File> txtFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                txtFiles.add(file);
            }
        }
        return txtFiles;
    }

    public static List<String> getPathsFiles(List<File> files) {
        List<String> paths = new ArrayList<>(List.of());
        files.stream().forEach(e -> paths.add(e.getAbsolutePath()));
        return paths;
    }

    public static void convertTxtToXml(String txtFilePath, String xmlFilePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
