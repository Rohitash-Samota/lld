package com.example.lld.abstractFactory.aws;

import org.springframework.stereotype.Component;

import com.example.lld.abstractFactory.module.BlobStore;

@Component
public class AwsBlobStore implements BlobStore {
    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("AwsBlobStore: uploading " + fileName + " (" + (data == null ? 0 : data.length) + " bytes)");
    }
}
