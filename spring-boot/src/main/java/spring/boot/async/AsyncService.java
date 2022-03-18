package spring.boot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncService.java
 * @Author weilei
 * @Description 异步处理服务
 * @CreateTime 2022年03月12日 14:31
 */
@Slf4j
@Service
public class AsyncService {

    /**
     * <h2>@Async 默认配置，每次都会创建新线程，需要自定义线程池配置</h2>
     * 默认corePoolSize=1，queueCapacity=Integer.MAX_VALUE,导致只有一个线程来处理异步任务，效率底下，通常需要自定义线程池配置
     * @throws InterruptedException
     */
    @Async("executor")
    public void asyncProcess() throws InterruptedException {
        log.info("asyncProcess task,current thread : {}",Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
    }

    @Async("executor")
    public Future<Integer> asyncProcessWithReturn() throws InterruptedException {
        log.info("asyncProcess task with return,current thread : {}",Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return new AsyncResult<>(100);
    }
}
