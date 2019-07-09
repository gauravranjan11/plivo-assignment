package com.plivo.assignment.cache;

import org.slf4j.*;
import org.springframework.stereotype.*;

import io.lettuce.core.*;
import io.lettuce.core.api.*;
import io.lettuce.core.api.sync.*;
import io.lettuce.core.codec.*;

@Service
public class RedisManager {

    private static final Logger logger = LoggerFactory.getLogger(
            RedisManager.class);


    RedisClient                             redisClient;
    StatefulRedisConnection<Object, Object> connection;
    RedisCommands<Object, Object>           commands;
    RedisCommands<String, Integer>          incr;

    public RedisManager() {
        try {
            RedisURI redisURI = RedisURI.Builder
                    .redis("plivo-redis", 6379)
                    .build();

            this.redisClient = RedisClient.create(redisURI);
            RedisCommands<Object, Object> connCommand = redisClient.connect(
                    CompressionCodec.valueCompressor(new SerializedObjectCodec(),
                                                     CompressionCodec.CompressionType.GZIP)).sync();
            RedisCommands<String, Integer> incrCommand = redisClient.connect(new RateLimiterCodec()).sync();
            this.connection
                    = connCommand.getStatefulConnection();
            this.commands = this.connection.sync();
            this.incr = incrCommand.getStatefulConnection().sync();

        }
        catch (Exception ex) {
            logger.error("Error in getting redis connection", ex);

        }
    }


    public String put(Object key, Object value, long time) {

        return commands.setex(key, time, value);

    }


    public Object get(Object key) {

        return commands.get(key);

    }

    public boolean checkLimit(String key, int limit, int expiry)

    {
        Long exists = incr.exists(key);
        int currentVal = 0;
        if (exists == 1) {
            currentVal = incr.get(key);
        }

        if (exists == 1 && currentVal >= limit) {
            return false;
        }
        else if (exists == 1 && currentVal < limit) {
            incr.watch(key);

            incr.multi();
            incr.incr(key);
            incr.exec();
            return true;
        }
        else {
            incr.watch(key);

            incr.multi();
            incr.incr(key);
            incr.expire(key, expiry);
            incr.exec();
            return true;
        }

    }
}
