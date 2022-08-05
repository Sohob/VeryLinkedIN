package com.verylinkedin.mypost;

import com.verylinkedin.mypost.models.Post;
import com.verylinkedin.mypost.util.Fields;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Integer> {

    // Post findById(String id);
    void deleteById(String id);
    List<Post> findByUserId(String id);
    Post findById(String id);
    List<Post> findByLabel(Fields label);

}
