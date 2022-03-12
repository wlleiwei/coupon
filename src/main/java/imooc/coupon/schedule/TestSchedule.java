package imooc.coupon.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName TestSchedule.java
 * @Author weilei
 * @Description 测试Springboot schedule
 * @CreateTime 2022年03月10日 16:48
 */


@Slf4j
@Service
public class TestSchedule {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * <h2>fixedRate 开始执行后的3s后执行</h2>
     */
    @Scheduled(fixedRate = 3000)
    public void testScheduleFixedRate() throws InterruptedException {
        log.info("1.testScheduleFixedRate -> {}", LocalDateTime.now().format(formatter));
        Thread.sleep(2000);
    }

    /**
     * <h2>fixedDelay 执行完毕后的3s后执行</h2>
     */
    @Scheduled(fixedDelay = 3000)
    public void testScheduleFixedDelay() throws InterruptedException {
        log.info("2.testScheduleFixedDelay -> {}", LocalDateTime.now().format(formatter));
        Thread.sleep(2000);
    }

    /**
     * <h2>initialDelay 第一次1s后，以后3s</h2>
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 1000,fixedDelay = 3000)
    public void testScheduleInitialDelay() throws InterruptedException {
        log.info("3.testScheduleInitialDelay -> {}", LocalDateTime.now().format(formatter));
        Thread.sleep(2000);
    }

    /**
     * <h2>cron表达式：秒 分 时 日 月 星期 [年份]</h2>
     * @throws InterruptedException
     */
    @Scheduled(cron = "5 * * * * *")
    public void testScheduleCron() throws InterruptedException {
        log.info("4.testScheduleCron -> {}", LocalDateTime.now().format(formatter));
        Thread.sleep(2000);
    }

}
