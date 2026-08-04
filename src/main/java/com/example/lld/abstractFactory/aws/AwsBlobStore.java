package com.example.lld.abstractFactory.aws;
 
import com.example.lld.abstractFactory.module.BlobStore;

public class AwsBlobStore implements BlobStore{
    @Override
    public BlobStore upload(String fileName, byte[] data){
        return new BlobStore();
    }
}
