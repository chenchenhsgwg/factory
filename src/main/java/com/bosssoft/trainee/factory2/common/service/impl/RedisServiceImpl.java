package com.bosssoft.trainee.factory2.common.service.impl;


import com.bosssoft.trainee.factory2.common.JedisExecutor;
import com.bosssoft.trainee.factory2.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Redis 工具类，只封装了几个常用的 redis 命令，
 * 可根据实际需要按类似的方式扩展即可。
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private static final String separator = System.getProperty("line.separator");

    @Autowired
    JedisPool jedisPool;

    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    private <T> T excuteByJedis(JedisExecutor<Jedis, T> j) {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Set<String> getKeys(String pattern) {
        return this.excuteByJedis(j -> j.keys(pattern));
    }

    @Override
    public String get(String key) {
        return this.excuteByJedis(j -> j.get(key.toLowerCase()));
    }

    @Override
    public String set(String key, String value) {
        return this.excuteByJedis(j -> j.set(key.toLowerCase(), value));
    }

    @Override
    public String set(String key, String value, Long milliscends) {
        String result = this.set(key.toLowerCase(), value);
        this.pexpire(key, milliscends);
        return result;
    }

    @Override
    public Long del(String... key) {
        return this.excuteByJedis(j -> j.del(key));
    }

    @Override
    public Boolean exists(String key) {
        return this.excuteByJedis(j -> j.exists(key));
    }

    @Override
    public Long pttl(String key) {
        return this.excuteByJedis(j -> j.pttl(key));
    }

    @Override
    public Long pexpire(String key, Long milliseconds) {
        return this.excuteByJedis(j -> j.pexpire(key, milliseconds));
    }

    @Override
    public Long zadd(String key, Double score, String member) {
        return this.excuteByJedis(j -> j.zadd(key, score, member));
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return this.excuteByJedis(j -> j.zrangeByScore(key, min, max));
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return this.excuteByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    @Override
    public Long zrem(String key, String... members) {
        return this.excuteByJedis(j -> j.zrem(key, members));
    }

}
