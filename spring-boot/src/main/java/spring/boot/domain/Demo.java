package spring.boot.domain;

import lombok.Data;

/**
 * <h1> Demo类，用于测试 </h1>
 */
@Data
public class Demo {
    private static Demo demo = new Demo();

    private String liquidateCode = "";

    public static Demo getDemo() {
        return demo;
    }
}
