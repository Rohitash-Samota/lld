package com.example.lld.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.dto.lru.LRUNode;

@Service
public class LRUCacheService<K, V> {

    private final int capacity = 4;

    private final Map<K, LRUNode<K, V>> cacheMap;

    private final LRUNode<K, V> head;
    private final LRUNode<K, V> tail;

    // Create LRU where create empty head and tail node assign
    public LRUCacheService() {

        cacheMap = new HashMap<>();

        head = new LRUNode<>();
        tail = new LRUNode<>();

        head.setNext(tail);
        tail.setPrev(head);
    }

    // fir check key contain in cache map after get existing node if have remove this node nd add on front 
    // full capacity than remove tail node 
    // create new node and add in front 
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

    // check contain if have than remove and add at front
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
