package com.kltn.api_gateway.repository;

import com.kltn.api_gateway.dto.request.IntrospectRequest;
import com.kltn.api_gateway.dto.response.ApiResponse;
import com.kltn.api_gateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface AuthenticateClient {
    @PostExchange(url = "/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<IntrospectResponse> introspect(@RequestBody IntrospectRequest request);
}