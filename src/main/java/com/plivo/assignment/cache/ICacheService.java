package com.plivo.assignment.cache;

import java.util.Map;

public interface ICacheService<K, T> {


    T getValue(K key);

    void put(K k, T t, int seconds);

    boolean getLimit(K k, int limit, int expiry);

}
