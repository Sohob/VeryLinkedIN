package com.verylinkedin.groupchat;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GroupRepository
        extends MongoRepository<GroupChat, Integer> {
    List<GroupChat> findById(String id);
}
