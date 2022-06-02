package com.verylinkedin.core.auth.repository;

import java.util.Optional;

/**
 * CacheRepository
 */
public interface CacheRepository {

    /**
     * Store the key value pair in cache
     *
     * @param key   String key
     * @param value Integer value
     */
    void put(String key, Boolean value);

    /**
     * Retrieve value from cache when key is provided
     *
     * @param key String key
     * @return Optional String
     */
    Optional<String> get(String key);

    /**
     * Remove value from cache
     *
     * @param key String key
     */
    void remove(String key);

}
