package com.example.lld.abstractFactory;

public interface CloudFactory {
    BlobStore createBlobStore();
    MessageQueue createMessageQueue();
}
