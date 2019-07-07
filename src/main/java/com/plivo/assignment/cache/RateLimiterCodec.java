package com.plivo.assignment.cache;

import java.nio.*;
import java.nio.charset.*;

import io.lettuce.core.codec.*;

public class RateLimiterCodec implements RedisCodec<String, Integer> {

    private Charset charset = Charset.forName("UTF-8");

    public RateLimiterCodec() {

    }

    @Override
    public String decodeKey(ByteBuffer bytes) {
        return charset.decode(bytes).toString();
    }

    @Override
    public Integer decodeValue(ByteBuffer byteBuffer) {
        try {
            return Integer.parseInt(charset.decode(byteBuffer).toString());

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteBuffer encodeKey(String key) {
        return charset.encode(key);
    }

    @Override
    public ByteBuffer encodeValue(Integer value) {
        try {
            return charset.encode(CharBuffer.allocate(value));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
