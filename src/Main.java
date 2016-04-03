import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.search.domain.Index;
import org.search.handler.InitHandler;
import org.search.handler.RequestHandler;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LJT on 2016/3/26.
 */

public class Main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
/*        System.out.println(jedis.ping());
        jedis.set("my-redis", "fuxk the nosql database");
        String str = jedis.get("my-redis");
        System.out.println(str);
        jedis.hset("base", "name", "ljt");
        jedis.hset("base", "age", "21");
        Map<String, String> map = jedis.hgetAll("base");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " :  " + map.get(key));
        }
        String age = jedis.hget("base", "aaa");
        //String age = jedis.hget("base", "age");
        System.out.println(age);
        jedis.close();
        String str1 = "我爱你";
        List<Term> list = IndexAnalysis.parse(str1);
        for (int i = 0 ; i < list.size(); i ++) {
            System.out.println(list.get(i).toString().substring(0, list.get(i).toString().indexOf("/")));
        }
        System.out.println(list);
        System.out.println(ToAnalysis.parse(str1));*/
        File file = new File("G:\\project\\redisdemo\\testfile");
        InitHandler initHandler = new InitHandler(jedis);
        initHandler.Init(file);
        RequestHandler requestHandler = new RequestHandler(jedis);
        List<Index> indexs = requestHandler.getResultList("太阳");
        for (int i = 0; i < indexs.size(); i++) {
            System.out.println(indexs.get(i).getTitle() + " " + indexs.get(i).getCount());
        }
    }
}
