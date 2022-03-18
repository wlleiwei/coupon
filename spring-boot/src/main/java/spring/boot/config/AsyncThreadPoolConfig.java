package spring.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncThreadPoolConfig.java
 * @Author weilei
 * @Description 自定义异步线程池配置
 * @CreateTime 2022年03月12日 14:42
 */
@Slf4j
@Configuration
public class AsyncThreadPoolConfig implements AsyncConfigurer {

    /**
     * <h2>线程池配置</h2>
     *
     * @return
     */
    @Bean(name = "executor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池中的核心线程数，有新任务时会创建新线程，直到达到CorePoolSize，新来的任务会当道阻塞队列中等待执行
        executor.setCorePoolSize(10);
        //线程池中最大的线程数
        executor.setMaxPoolSize(20);
        //等待队列容量，当队列中任务的数量等于QueueCapacity时，会创建新的线程去处理任务，前提是线程池中的总线程数<=MaxPoolSize
        executor.setQueueCapacity(20);
        //线程池中除核心线程外线程在空闲时不会立即回收，在等待KeepAliveSeconds时间后仍然时空闲状态时便会回收
        executor.setKeepAliveSeconds(60);
        //线程名称前缀
        executor.setThreadNamePrefix("AsyncTest->");
        //等待线程执行完毕后关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //线程池中任务的等待时间，超过这个时间直接销毁
        executor.setAwaitTerminationSeconds(60);
        //拒绝策略，线程池中的线程数达到max_size的时候，如何处理新任务
        //CallerRunsPolicy：提交任务的线程自己去执行任务
        //AbortPolicy：默认的拒绝策略，抛出RejectedExecutionException异常
        //DiscardPolicy：直接丢弃任务，美誉任何异常抛出
        //DiscardOldestPolicy：丢弃最老的任务，把最早进入到工作队列的任务抛弃掉，把新任务加入到工作队列
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程池初始化
        executor.initialize();
        return executor;
    }

    /**
     * <h2>异步任务异常处理</h2>
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.info("线程{}处理发生异常,throwable:{},method:{}, params:{}", throwable, method, objects);
            throwable.printStackTrace();
            //TODO 发送告警信息
        };
    }
}
