package com.Nearsoft.referrals.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {


    void store(MultipartFile file);


    void deleteFile() throws IOException;

    String getFileName();

}