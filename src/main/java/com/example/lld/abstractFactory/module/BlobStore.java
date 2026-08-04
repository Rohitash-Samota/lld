package com.example.lld.abstractFactory.module;

public interface BlobStore {
    void upload(String fileName, byte[] data);
}
