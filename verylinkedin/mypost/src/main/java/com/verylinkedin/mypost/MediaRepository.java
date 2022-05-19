package com.verylinkedin.mypost;

import com.verylinkedin.mypost.models.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepository extends MongoRepository<Media, Integer> {

    Media findById(String id);
    void deleteById(String id);
}
