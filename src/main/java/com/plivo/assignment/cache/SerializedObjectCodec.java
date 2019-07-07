package com.plivo.assignment.cache;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryo.io.*;

import org.objenesis.strategy.*;

import java.nio.*;

import io.lettuce.core.codec.*;

public class SerializedObjectCodec implements RedisCodec<Object, Object> {

    Kryo kryo;
    private static final int byteSize = 10240;

    public SerializedObjectCodec() {
        this.kryo = new Kryo();
        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

    }

    @Override
    public Object decodeKey(ByteBuffer byteBuffer) {

        byte[] arr = new byte[byteBuffer.remaining()];
        byteBuffer.get(arr);
        ByteBufferInput input = new ByteBufferInput(byteBuffer.remaining());
        input.setBuffer(arr);
        Object o = kryo.readClassAndObject(input);

        input.close();
        return o;
    }

    @Override
    public Object decodeValue(ByteBuffer byteBuffer) {


        byte[] arr = new byte[byteBuffer.remaining()];
        byteBuffer.get(arr);
        ByteBufferInput input = new ByteBufferInput(byteBuffer.remaining());
        input.setBuffer(arr);
        Object o = kryo.readClassAndObject(input);

        input.close();
        return o;
    }

    @Override
    public ByteBuffer encodeKey(Object o) {

        ByteBufferOutput redisKey = new ByteBufferOutput(byteSize);

        kryo.writeClassAndObject(redisKey, o);
        ByteBuffer byteBuffer = ByteBuffer.wrap(redisKey.toBytes());
        redisKey.close();

        return byteBuffer;
    }

    @Override
    public ByteBuffer encodeValue(Object o) {

        ByteBufferOutput redisValue = new ByteBufferOutput(byteSize);

        kryo.writeClassAndObject(redisValue, o);
        ByteBuffer byteBuffer = ByteBuffer.wrap(redisValue.toBytes());
        redisValue.close();

        return byteBuffer;
    }
}
