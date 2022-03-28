package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * <h1> 记录请求的耗时 </h1>
 *
 * @author 魏磊
 */

@Slf4j
@Component
public class AccessLogFilter extends AbstractPostFilter {
    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    /**
     * 具体过滤器来实现执行逻辑
     *
     * @return 执行结果
     */
    @Override
    protected Object execute() {
        long startStamp = (long) context.get("startTime");
        String uri = context.getRequest().getRequestURI();
        long endStamp = System.currentTimeMillis();
        log.info("uri:{},time:{}", uri, endStamp - startStamp);
        return success();
    }
}
