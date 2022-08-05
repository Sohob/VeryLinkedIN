package com.verylinkedin.groupchat;


import com.verylinkedin.groupchat.classes.GroupChat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GroupRepository
        extends MongoRepository<GroupChat, Integer> {
    List<GroupChat> findById(String id);
}
