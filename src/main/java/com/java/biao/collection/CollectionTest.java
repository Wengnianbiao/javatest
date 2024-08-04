package com.java.biao.collection;

import java.util.HashMap;
import java.util.Map;

public class CollectionTest {

    public static void main(String[] args) {
        int cap = 1<<4;
        float load = 0.56f;
        Map<String, String> hashMap = new HashMap<>(cap, load);
    }
}
