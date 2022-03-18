package spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spring.boot.domain.JsonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <h1>测试TestController</h1>
 */
@Slf4j
@RestController
@RequestMapping("/springboot")
public class TestController {
    private final ObjectMapper objectMapper;

    public TestController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * <h2>测试json转化</h2>
     * url:127.0.0.1:8000/imooc/springboot/jackson
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/jackson")
    public JsonEntity testJackson() throws JsonProcessingException {
        JsonEntity jsonEntity = JsonEntity.builder()
                .name("张三").address("万达广场").birthday(new Date()).build();
        String json = objectMapper.writeValueAsString(jsonEntity);
        log.info("JsonEntity 转化为 json字符串：{}", json);

        JsonEntity entity = objectMapper.readValue(json, JsonEntity.class);
        return entity;
    }
}
