package org.search.handler;

import org.search.IndexComparator;
import org.search.domain.Index;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by LJT on 2016/4/3.
 */
public class RequestHandler {
    private IndexHandler indexHandler;
    private Jedis redis;

    public RequestHandler(Jedis redis) {
        super();
        this.redis = redis;
        this.indexHandler = new IndexHandler();
    }

    public List<Index> getResultList(String keyword) {
        List<Index> indexs = this.indexHandler.getIndexList(keyword, redis);
        indexs.sort(new IndexComparator());
        return indexs;
    }
}
