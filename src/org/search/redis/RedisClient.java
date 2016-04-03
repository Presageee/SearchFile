package org.search.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by LJT on 2016/3/28.
 */
public class RedisClient {
    private static Jedis jedis;

    public static Jedis getInstance(String ip) {
        synchronized (RedisClient.class) {
            if (jedis != null) {
                jedis = new Jedis(ip);
            }
        }
        return jedis;
    }

    public static Jedis getInstance(String ip, int port) {
        synchronized (RedisClient.class) {
            if (jedis == null) {
                jedis = new Jedis(ip, port);
            }
        }
        return jedis;
    }
}
