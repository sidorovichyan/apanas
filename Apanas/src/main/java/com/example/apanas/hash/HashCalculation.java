package com.example.apanas.hash;

import com.example.apanas.entity.Triangle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HashCalculation {
    Map<Triangle,String> hashMap = new HashMap<>();

    public boolean isContain(Triangle key) {
        return hashMap.containsKey(key);
    }

    public void addToMap(Triangle key, String  obj) {
        hashMap.put(key, obj);
    }

    public String getParameters(Triangle key) {
        return hashMap.get(key);
    }
}
