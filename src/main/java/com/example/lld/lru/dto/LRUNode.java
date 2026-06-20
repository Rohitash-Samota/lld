package com.example.lld.lru.dto;

public class LRUNode<K, V> {

    private K key;
    private V value;

    private LRUNode<K, V> prev;
    private LRUNode<K, V> next;

    public LRUNode() {
    }

    public LRUNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public LRUNode<K, V> getPrev() {
        return prev;
    }

    public void setPrev(LRUNode<K, V> prev) {
        this.prev = prev;
    }

    public LRUNode<K, V> getNext() {
        return next;
    }

    public void setNext(LRUNode<K, V> next) {
        this.next = next;
    }
}