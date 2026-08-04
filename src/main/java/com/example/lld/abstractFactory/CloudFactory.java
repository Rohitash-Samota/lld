package com.example.lld.abstractFactory;

import com.example.lld.abstractFactory.module.BlobStore;
import com.example.lld.abstractFactory.module.MessageQueue;

public interface CloudFactory {
    BlobStore createBlobStore();

    MessageQueue createMessageQueue();
}
