package spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationBootRunner.java
 * @Author weilei
 * @Description TODO
 * @CreateTime 2022年03月16日 10:04
 */
@Slf4j
@Component
public class ApplicationBootRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationBootRunner->{}",args);
    }
}
