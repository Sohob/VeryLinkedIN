package com.verylinkedin.core.auth.commands;

import com.verylinkedin.core.auth.repository.CacheRepository;
import com.verylinkedin.core.auth.requests.RemoveRedisRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class RemoveTokenCommand implements Command{
    private final RemoveRedisRequest request;
    private final CacheRepository cacheRepository;
    public RemoveTokenCommand(RemoveRedisRequest request, CacheRepository cacheRepository) {
        this.request = request;
        this.cacheRepository = cacheRepository;
    }
    @Override
    public Object execute() {
        Optional<String> s = cacheRepository.get(request.token().getToken());
        if (s.isPresent()) {
            cacheRepository.remove(request.token().getToken());
            return ResponseEntity.ok("Token removed: " + request.token().getToken());
        }
        return ResponseEntity.badRequest().body("Token not in Cache!");
    }
}
