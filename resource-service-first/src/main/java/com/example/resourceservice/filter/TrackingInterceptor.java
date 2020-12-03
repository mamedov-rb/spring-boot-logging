package com.example.resourceservice.filter;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static com.example.resourceservice.filter.RequestTrackingFilter.CORRELATION_ID;

public class TrackingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(final HttpRequest request,
                                        final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        final HttpHeaders headers = request.getHeaders();
        headers.add(CORRELATION_ID, ThreadContext.get(CORRELATION_ID));
        return execution.execute(request, body);
    }
}
