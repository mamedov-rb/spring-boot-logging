package com.example.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseTrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;

    private final FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        final RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
        return null;
    }
}
