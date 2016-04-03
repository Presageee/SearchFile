package org.search.handler;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.search.Constant;
import org.search.domain.Index;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LJT on 2016/3/28.
 */
public class IndexHandler {
    public int analysisText(String title, String text, Jedis redis) {
        List<Term> terms = IndexAnalysis.parse(text);
        int len = terms.size();
        int textLength = 0;
        for (int i = 0; i < len; i ++) {
            Term tmp = terms.get(i);
            if (Constant.isSymbol(tmp.toString())) {
                continue;
            }
            textLength++;
            String token = null;
            if (!(tmp.getName()).equals("")) {
                token = tmp.toString().substring(0, tmp.toString().indexOf("/"));
            } else {
                continue;
            }
            String cnt = redis.hget(token, title);
            if (cnt != null) {
                int result = Integer.parseInt(cnt.toString());
                result ++;
                redis.hset(token, title, result + "");
            } else {
                redis.hset(token, title, 1 + "");
            }
        }
        return textLength;
    }

    public boolean analysisFile(File file, Jedis redis) throws Exception {
        String title = file.getName();
        String fileLength = redis.get(title);
        if (fileLength != null) {
            System.out.println("title is exist");
            return false;
        }
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        int len = 0;
        String line = null;
        while ((line = buffer.readLine()) != null) {
            int tmpLen = analysisText(title, line, redis);
            len += tmpLen;
        }
        redis.set(title, len + "");
        return true;
    }

    public List<Index> getIndexList(String keyword, Jedis redis) {
        List<Index> indexs = new ArrayList<>();
        Map<String, String> map = redis.hgetAll(keyword);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Index index = new Index();
            index.setKey(keyword);
            index.setTitle(key);
            index.setCount(Integer.parseInt(map.get(key)));
            index.setLength(Integer.parseInt(redis.get(key)));
            indexs.add(index);
        }
        return indexs;
    }
}
