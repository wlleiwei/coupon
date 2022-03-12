package imooc.coupon.async;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncServiceTest.java
 * @Author weilei
 * @Description 异步服务测试类
 * @CreateTime 2022年03月12日 15:50
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncServiceTest {

    @Autowired
    private AsyncService asyncService;
    @Test
    public void testAsyncProcess() throws InterruptedException {
        asyncService.asyncProcess();
    }

    @Test
    public void testAsyncProcessWithReturn() throws Exception{
        Future<Integer> integerFuture = asyncService.asyncProcessWithReturn();
        //log.info("testAsyncProcessWithReturn->{}",integerFuture.get());
        log.info("testAsyncProcessWithReturn->{}",integerFuture.get(1, TimeUnit.SECONDS));
    }

}
