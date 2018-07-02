package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;
    private String fileName;

    public StorageServiceImpl() {
        rootLocation = Paths.get("./");
    }

    @Override
    public void store(MultipartFile file) {
        try {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
            }
        } catch (NullPointerException nullPointerException) {

        }
    }

    @Override
    public void deleteFile() throws IOException {
        Files.delete(this.rootLocation.resolve(fileName));
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    public Path getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(Path rootLocation) {
        this.rootLocation = rootLocation;
    }
}
