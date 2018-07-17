package com.nearsoft.referrals.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {


    String store(MultipartFile file);


    void deleteFile(String fileName) throws IOException;

    FileSystemResource getFileSystemResource(String fileName);
}