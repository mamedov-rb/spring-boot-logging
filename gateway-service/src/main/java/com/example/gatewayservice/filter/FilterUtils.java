package com.example.gatewayservice.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilterUtils {

    public static final String PRE_FILTER_TYPE = "pre";

    public static final String POST_FILTER_TYPE = "post";

    public static final String CORRELATION_ID = "x-correlation-id";

    public String getCorrelationId() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        return Optional
                .ofNullable(ctx.getRequest().getHeader(CORRELATION_ID))
                .orElse(ctx.getZuulRequestHeaders().get(CORRELATION_ID));
    }

    public void setCorrelationId(final String correlationId) {
        RequestContext.getCurrentContext()
                .addZuulRequestHeader(CORRELATION_ID, correlationId);
    }
}

