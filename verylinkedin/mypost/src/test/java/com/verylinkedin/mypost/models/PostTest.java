package com.verylinkedin.mypost.models;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class PostTest {
    @Autowired
    private PostRepository postRepository;
    @Test
    void createPostTest() {
        Post post = Post.builder()
                .userId("123456789")
                .content("Test")
                .build();
        post.setPublic(true);
        postRepository.save(post);

        assertEquals(postRepository.findById("123456789"), true);
    }

    @Test
    void getId() {
    }

    @Test
    void getContent() {
    }

    @Test
    void getIsPublic() {
    }

    @Test
    void getMedia() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void setId() {
    }

    @Test
    void setContent() {
    }

    @Test
    void setIsPublic() {
    }

    @Test
    void setMedia() {
    }

    @Test
    void builder() {
    }
}