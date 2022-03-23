package com.imooc.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <h1> 自定义Zuul网关抽象过滤器，具体的过滤器实现继承此类 </h1>
 *
 * @author 魏磊
 */

public abstract class AbstractZuulFilter extends ZuulFilter {

    /**
     * 用于过滤器之间传递消息，数据保存在每个请求的ThreadLocal中
     */
    RequestContext requestContext;
    /**
     * 用于判断当前过滤器是否执行
     */
    private final static String NEXT = "next";

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return (boolean) requestContext.getOrDefault(NEXT, true);
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * @throws ZuulException if an error occurs during execution.
     */
    @Override
    public Object run() throws ZuulException {
        requestContext = RequestContext.getCurrentContext();
        return execute();
    }

    /**
     * 具体过滤器来实现执行逻辑
     *
     * @return 执行结果
     */
    protected abstract Object execute();


    /**
     * 执行时失败，直接返回，不执行后续的过滤器
     *
     * @param code 状态码
     * @param msg  错误信息
     */
    void fail(int code, String msg) {
        //后续的过滤器不执行
        requestContext.set(NEXT, false);
        //直接返回
        requestContext.setSendZuulResponse(false);
        requestContext.getResponse().setContentType("text/html:charset=UTF-8");
        requestContext.setResponseStatusCode(code);
        requestContext.setResponseBody(String.format("{\"result\":\"%s\"}", msg));
    }


    /**
     * 执行成功设置NEXT=TRUE，执行后续的过滤器
     */
    void success() {
        requestContext.set(NEXT, true);
    }
}
