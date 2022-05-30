package com.verylinkedin.mypost;

import com.verylinkedin.mypost.models.Post;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.yaml.snakeyaml.tokens.DocumentEndToken;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Integer> {

    Post findById(String id);
    void deleteById(String id);
    List<Post>  findByUserId(String id);
}
