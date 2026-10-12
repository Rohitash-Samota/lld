package com.example.lld.hash_map;

import java.util.ArrayList;
import java.util.LinkedList;

class HashMapCode {
    static class HashMap<K,V>{
        private class Node{
            private K key;
            private V value;
            public Node(K key, V value){
                this.key=key;
                this.value=value;
            }
        }

        private int n; //-- total no od nodes
        private int N; // -- total no of bucket
        private LinkedList<Node> buckets[];

        @SuppressWarnings("unchecked")
        public HashMap(){
            this.N=4;
            this.buckets=new LinkedList[4];
            for (int i = 0; i < 4; i++) {
                // intilize the null linklist
                this.buckets[i] = new LinkedList<>();
            }
        }
        private int hashFunction(K key){ // 0 to n
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        private int searchInLL(K key , int bi){
            LinkedList<Node> ll= buckets[bi];
            for(int i=0;i<ll.size();i++){
                if (ll.get(i).key==key) {
                    return i;
                }
            }
            return -1;
        }

        private void rehashingFunction(){
            LinkedList<Node> oldBuckets[] = buckets;
            buckets = new LinkedList[N*2];
            for(int i=0;i<2*N;i++){
                buckets[i]= new LinkedList<>();
            }

            for (int idx = 0; idx < oldBuckets.length; idx++) {
                LinkedList<Node> currLL= oldBuckets[idx];
                for(int i=0;i<currLL.size();i++){
                    Node node = currLL.get(i);
                    put(node.key, node.value);
                }
            }

        }
        public void put(K key , V value){
            int bi = hashFunction(key);
            int di = searchInLL(key,bi);  //data index
            if(di==-1){
                buckets[bi].add(new Node(key, value));
                n++;
            }else{
                Node node =  buckets[bi].get(di);
                node.value=value;
            }
            double lambda  = (double)n/N;
            if (lambda>2.0) {
                rehashingFunction();
            }
        }

        public void remove(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di==-1) {
                System.err.println("Key not found in => "+ key);
            }

            LinkedList<Node> oldLinkList = buckets[bi];
            LinkedList<Node> newLinkList = new LinkedList<>();
            for(int i=0;i<oldLinkList.size();i++){
                Node node = oldLinkList.get(i);
                if (i!=di) {
                    newLinkList.add(new Node(node.key,node.value));
                }
            }
            buckets[bi]=newLinkList;
        }

        public boolean contentsKey(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di==-1) {
                System.err.println("Key not found in => "+ key);
            }
            LinkedList<Node> currLinkList = buckets[bi];
            for(int i=0;i<currLinkList.size();i++){
                if (i==di) {
                    return true;
                }
            }
            return false;
        }

        public V getValue(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di==-1) {
                System.err.println("Key not found in => "+ key);
            }
            LinkedList<Node> currLinkList = buckets[bi];
            for(int i=0;i<currLinkList.size();i++){
                Node node = currLinkList.get(i);
                if (i==di) {
                    return node.value;
                }
            }
            return null;
        }

        public ArrayList<K> getAllKeys(){
            ArrayList<K> ans= new ArrayList<>();
            int biSize = buckets.length;
            for(int i=0;i<biSize;i++){
                LinkedList<Node> currLL = buckets[i];
                for(int j=0;j<currLL.size();j++){
                    Node node = currLL.get(j);
                    ans.add(node.key);
                }
            }
            return ans;
        }

        public boolean isEmpty(){
            return false;
        }

    }

    public static void main(String[] ...args){
        HashMap<String ,String> mp = new HashMap<>();
        mp.put("Rohit", "Samota");
        mp.put("Nitu", "Khichar");
        for(String key:mp.getAllKeys()){
            System.err.println("Key=> " + key);
        }
    }
}
