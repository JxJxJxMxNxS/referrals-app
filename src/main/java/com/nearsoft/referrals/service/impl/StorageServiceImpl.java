package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;

    public StorageServiceImpl(@Value("${filesystem.storage.temporary.directory:'./'}") String path) throws IOException {

        rootLocation = Paths.get(path);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } catch (IOException e) {
            }
        } catch (NullPointerException nullPointerException) {

        }
        return null;
    }

    @Override
    public void deleteFile(String filename) throws IOException {
        Files.delete(this.rootLocation.resolve(filename));
    }

    @Override
    public FileSystemResource getFileSystemResource(String fileName) {
        return new FileSystemResource(new File(rootLocation + File.separator + fileName));
    }

    public Path getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(Path rootLocation) {
        this.rootLocation = rootLocation;
    }
}
