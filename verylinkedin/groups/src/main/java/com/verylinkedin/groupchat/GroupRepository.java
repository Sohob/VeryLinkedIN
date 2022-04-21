package com.verylinkedin.groupchat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository
        extends MongoRepository<GroupChat, Integer> {
}
