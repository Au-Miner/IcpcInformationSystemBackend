package com.icpcinformationsystembackend;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import redis.clients.jedis.Jedis;

import java.util.*;

// @SpringBootTest
class IcpcInformationSystemBackendApplicationTests {
    @Test
    void contextLoads() {
        ArrayList<String> sss = new ArrayList<>();
        sss.add("111");
        sss.add("222");
        sss.add("333");
        sss.remove(sss.size() - 1);
        show(sss);
    }
    void show(ArrayList<String> ss) {
        for (String tmp : ss) {
            System.out.println(tmp);
        }
    }
    void swap(ArrayList<String> ss, int l, int r) {
        String tmp = ss.get(l);
        ss.set(l, ss.get(r));
        ss.set(r, tmp);
    }
}
