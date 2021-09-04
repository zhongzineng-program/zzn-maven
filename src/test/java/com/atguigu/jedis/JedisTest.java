package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.List;

/**
 * @author 爱Ctrl+C的菜鸡
 * @create 2021/9/2 15:28
 */
public class JedisTest {
    @Test
    public void testJedis(){

        //声明Redis服务器的地址
        String host = "192.168.117.100";

        //创建Jedis对象
        Jedis jedis = new Jedis(host, Protocol.DEFAULT_PORT);

        //调用那些和Redis命令同名的方法操作Redis
        String ping = jedis.ping();
        System.out.println("ping ="+ping);

        Long len = jedis.lpush("animal:list", "dog", "cat", "pig");
        System.out.println("len ="+len);

        //遍历创建的key
        List<String> animalList = jedis.lrange("animal:list", 0, -1);
        for(String animal : animalList){
            System.out.println("animal="+animal);
        }
        //关闭连接
        jedis.close();
    }
    @Test
    //使用JedisPool来操作redis
    public void testJedisPool(){
        //声明Redis服务器的地址
        String host = "192.168.117.100";

        //创建JedisPool对象
        JedisPool jedisPool = new JedisPool(host, Protocol.DEFAULT_PORT);

        //通过JedisPool对象获取jedis对象
        Jedis jedis = jedisPool.getResource();

        String ping = jedis.ping();
        System.out.println("ping = "+ping);
        //关闭连接
        jedis.close();
        //v 1.0.3
        //v 1.0.4 by hot-fix
        //v 1.0.4 by master
    }
}
