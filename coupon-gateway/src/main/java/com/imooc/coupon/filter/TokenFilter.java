package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1> Token校验 </h1>
 *
 * @author 魏磊
 */
@Slf4j
@Component
public class TokenFilter extends AbstractPreZuulFilter {
    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        //返回数值越小，优先级越高
        return 1;
    }

    /**
     * 具体过滤器来实现执行逻辑
     *
     * @return 执行结果
     */
    @Override
    protected Object execute() {
        HttpServletRequest request = context.getRequest();
        log.info("{} request to {}", request.getMethod(), request.getRequestURL());
        String token = request.getParameter("token");
        //对token的处理
        if (StringUtils.isBlank(token)) {
            log.error("error:token is empty ！");
            return fail(401, "token error");
        }
        return success();
    }
}
