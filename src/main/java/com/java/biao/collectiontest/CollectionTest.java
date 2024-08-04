package com.java.biao.collectiontest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionTest {

    public static void main(String[] args) {
        int cap = 1<<4;
        float load = 0.56f;
        Map<String, String> hashMap = new HashMap<>(cap, load);
    }
}
