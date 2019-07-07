package com.plivo.assignment.cache;

import java.util.Map;

public interface ICacheService<K, T> {

    boolean containsKey(K k);

    T getValue(K key);

    void remove(K key);

    void put(K k, T t,int seconds);

    boolean getLimit(K k,int limit,int expiry);
    
    Map<byte[],byte[]> getAll();
}
