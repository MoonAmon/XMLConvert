package org.noom.xmlconvert;

public class FileConversionData {
    private String fileName;
    private String targetPath;

    public FileConversionData(String fileName, String targetPath) {
        this.fileName = fileName;
        this.targetPath = targetPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
