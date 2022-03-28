package spring.boot.main;

import lombok.extern.slf4j.Slf4j;
import spring.boot.domain.Demo;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MultiThreadTest {
    private String s = Demo.getDemo().getLiquidateCode();

    private void execute(int i) {
        //模拟赋值
        s = "liquidateCode:" + i;
        try {
            //模拟去数据库查询当前商户是否存在
            TimeUnit.MILLISECONDS.sleep(100);
            //模拟入库操作
            log.info("输出{}->{}", i, s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        MultiThreadTest test = new MultiThreadTest();
        //模拟多线程调用
        for (int i = 0; i < 2; i++) {
            final int b = i;
            new Thread(() -> {
                test.execute(b);
            }).start();
        }

    }
}
