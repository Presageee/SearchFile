package org.search.handler;

import redis.clients.jedis.Jedis;

import java.io.File;

/**
 * Created by LJT on 2016/4/3.
 */
public class InitHandler {
    private IndexHandler indexHandler;
    private Jedis redis;

    public InitHandler(Jedis redis) {
        super();
        this.redis = redis;
        indexHandler = new IndexHandler();
    }

    public void Init(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int len = files.length;
            for (int i = 0; i < len; i++) {
                try {
                    indexHandler.analysisFile(files[i], redis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println(file.getName() + " is not directory");
            return;
        }
    }
}
