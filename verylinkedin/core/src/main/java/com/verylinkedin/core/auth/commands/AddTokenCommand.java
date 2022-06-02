package com.verylinkedin.core.auth.commands;

import com.verylinkedin.core.auth.repository.CacheRepository;
import com.verylinkedin.core.auth.requests.AddRedisRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class AddTokenCommand implements Command{
    private final AddRedisRequest request;
    private final CacheRepository cacheRepository;
    public AddTokenCommand(AddRedisRequest request, CacheRepository cacheRepository) {
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
