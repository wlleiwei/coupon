package spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommandLineBootRunner.java
 * @Author weilei
 * @Description TODO
 * @CreateTime 2022年03月16日 10:07
 */
@Slf4j
@Component
public class CommandLineBootRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineBootRunner->{}",args);
    }
}
