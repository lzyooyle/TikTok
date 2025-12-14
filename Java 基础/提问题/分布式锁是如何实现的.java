package com.example.demoheimaduoxiancheng.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonManager {
    private static RedissonClient client;
    static {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("url")
                .setPassword(null)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(2);
        client = Redisson.create(config);
    }
    public static RedissonClient getClient() {
        return client;
    }
}


package com.example.demoheimaduoxiancheng.service;

import com.example.demoheimaduoxiancheng.config.RedissonManager;
import com.example.demoheimaduoxiancheng.controller.DemoController;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DemoService {

    private static final String LOCK_KEY = "order:lock:123456";

    public String getDemo() {
        RedissonClient client = RedissonManager.getClient();
        RLock lock = client.getLock(LOCK_KEY);
//        尝试获取锁
        try {
            if (lock.isLocked()) {
                try {
                    // 尝试加锁，最多等待 10 秒，上锁后 30 秒自动解锁
                    // 注意：30秒是看门狗自动续期的初始时间，实际会自动延长
                    lock.tryLock(10, 30, TimeUnit.SECONDS);

                    //模拟业务执行 20秒
                    Thread.sleep(20000);
                    System.out.println("业务执行完成");
                } catch (InterruptedException e) {
                    System.err.println("业务执行中断");
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                    System.out.println("锁已释放");
                }
            } else {
                System.out.println("获取锁失败，被其它服务占用");
            }
        } catch (RuntimeException e) {
            System.out.println("等待锁过程中被中断");
        }
        return null;
    }
}

