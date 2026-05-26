package com.example.lld.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.dto.LRUNode;

@Service
public class LRUCacheService<K, V> {

    private final int capacity = 4;

    private final Map<K, LRUNode<K, V>> cacheMap;

    private final LRUNode<K, V> head;
    private final LRUNode<K, V> tail;

    public LRUCacheService() {

        cacheMap = new HashMap<>();

        head = new LRUNode<>();
        tail = new LRUNode<>();

        head.setNext(tail);
        tail.setPrev(head);
    }

    public void put(K key, V value) {

        if (cacheMap.containsKey(key)) {

            LRUNode<K, V> existingNode = cacheMap.get(key);

            existingNode.setValue(value);

            removeNode(existingNode);
            addNode(existingNode);

            return;
        }

        if (cacheMap.size() == capacity) {

            LRUNode<K, V> lruNode = tail.getPrev();

            removeNode(lruNode);

            cacheMap.remove(lruNode.getKey());
        }

        LRUNode<K, V> newNode = new LRUNode<>(key, value);

        addNode(newNode);

        cacheMap.put(key, newNode);
    }

    public V get(K key) {

        if (!cacheMap.containsKey(key)) {
            return null;
        }

        LRUNode<K, V> node = cacheMap.get(key);

        removeNode(node);

        addNode(node);

        return node.getValue();
    }

    private void removeNode(LRUNode<K, V> node) {

        LRUNode<K, V> prevNode = node.getPrev();
        LRUNode<K, V> nextNode = node.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
    }

    private void addNode(LRUNode<K, V> node) {

        LRUNode<K, V> nextNode = head.getNext();

        head.setNext(node);

        node.setPrev(head);

        node.setNext(nextNode);

        nextNode.setPrev(node);
    }

    public void printCache() {

        LRUNode<K, V> current = head.getNext();

        while (current != tail) {

            System.out.println(
                    current.getKey() + " -> " + current.getValue());

            current = current.getNext();
        }
    }
}