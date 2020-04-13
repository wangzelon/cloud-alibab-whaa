package com.whaa.redislock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collections;
import java.util.UUID;

/**
 * created by @author wangzelong 2020/4/13 11:26
 */
public class RedisTest {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_KEY = "lock_key";

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(15);
        config.setMaxIdle(30);
        jedisPool = new JedisPool(config, "172.18.0.182", 6379, 6000, null,
                2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            String requestId = UUID.randomUUID().toString();
            new Thread(() -> {
                boolean lock = lock(jedisPool.getResource(), LOCK_KEY, requestId, 180);
                System.out.println(lock);
                boolean unlock = unlock(jedisPool.getResource(), LOCK_KEY, requestId);
                System.out.println(unlock);
            }, String.valueOf(i)).start();
        }
    }


    public static boolean lock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean unlock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
