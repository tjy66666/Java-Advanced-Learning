package com.tjy.advanced.week02;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author tjy
 * @date 2023/8/7
 * @apiNote
 */
public class GCLogAnalysis {
    private static Random random = new Random();

    public static void main(String[] args) {
        //当前毫秒时间戳
        long startMillis = System.currentTimeMillis();
        //持续运行毫秒数
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);
        //结束时间戳
        long endMillis = startMillis + timeoutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        //缓存一部分对象进入老年代
        int cacheSize = 2000;
        Object[] cacheObjects = new Object[cacheSize];
        //再此时间范围内循环
        while (System.currentTimeMillis() < endMillis){
            //生成垃圾对象
            Object garbage = generateGarbage(10 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize){
                cacheObjects[randomIndex] = garbage;
            }

        }
        System.out.println("执行结束！共生成对象次数："+counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt();
        int type = randomSize % 4;
        Object result = null;
        switch (type){
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder sb = new StringBuilder();
                String randomString = "randomString-Anything";
                while (sb.length() < randomSize){
                    sb.append(randomString);
                    sb.append(max);
                    sb.append(randomSize);
                }
                result = sb.toString();
                break;
        }
        return result;
    }
}
