package com.example.lld.abstractFactory.aws;

import com.example.lld.abstractFactory.CloudFactory;
import com.example.lld.abstractFactory.module.BlobStore;
import com.example.lld.abstractFactory.module.MessageQueue;

public class AwsFactory implements CloudFactory {
    @Override
    public BlobStore createBlobStore() {
        return new AwsBlobStore();
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AwsMessageQueue();
    }
}
