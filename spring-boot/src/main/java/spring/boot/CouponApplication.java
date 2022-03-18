package spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <h2>@EnableScheduling开启定时任务，@EnableAsync开启异步任务</h2>
 * @ClassName CouponApplication.java
 * @Author weilei
 * @Description 启动类
 * @CreateTime 2022年03月10日 16:47
 */
@EnableAsync
@SpringBootApplication
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }
}
