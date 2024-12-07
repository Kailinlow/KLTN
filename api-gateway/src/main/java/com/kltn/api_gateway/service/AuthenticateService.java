package com.kltn.api_gateway.service;

import com.kltn.api_gateway.dto.request.IntrospectRequest;
import com.kltn.api_gateway.dto.response.ApiResponse;
import com.kltn.api_gateway.dto.response.IntrospectResponse;
import com.kltn.api_gateway.repository.AuthenticateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final AuthenticateClient authenticateClient;

    public Mono<IntrospectResponse> introspect(String token) {
        return authenticateClient.introspect(IntrospectRequest.builder()
                .token(token)
                .build());
    }
}
