package com.verylinkedin.groupchat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository
                extends MongoRepository<Message, Integer> {

}
