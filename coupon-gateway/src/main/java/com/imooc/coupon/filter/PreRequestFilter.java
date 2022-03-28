package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1> 记录请求的时间戳 </h1>
 *
 * @author 魏磊
 */

@Slf4j
@Component
public class PreRequestFilter extends AbstractPreZuulFilter {
    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 具体过滤器来实现执行逻辑
     *
     * @return 执行结果
     */
    @Override
    protected Object execute() {
        long timestamp = System.currentTimeMillis();
        context.set("startTime", timestamp);
        return success();
    }
}
