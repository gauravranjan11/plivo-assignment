package com.plivo.assignment.cache.service.impl;


import com.plivo.assignment.cache.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class KeyCacheServiceImpl implements ICacheService<String, String> {

    @Autowired
    RedisManager redisManager;


    @Override
    public boolean containsKey(String s) {
        return false;
    }

    @Override
    public String getValue(String key) {
        return (String) redisManager.get(key);
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void put(String key, String value, int seconds) {

        redisManager.put(key, value, seconds);
    }

    @Override
    public boolean getLimit(String s, int limit, int expiry) {
        return redisManager.checkLimit(s, limit, expiry);
    }

    @Override
    public Map<byte[], byte[]> getAll() {
        return null;
    }
}
