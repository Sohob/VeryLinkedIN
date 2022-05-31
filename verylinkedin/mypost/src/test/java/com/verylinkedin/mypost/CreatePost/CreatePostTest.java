package com.verylinkedin.mypost.CreatePost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePostTest {

    @Mock
    private PostRepository mockPostRepository;

    private CreatePost createPostUnderTest;

    @BeforeEach
    void setUp() {
        createPostUnderTest = new CreatePost(new CreatePostRequest("userId", "content"), mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = createPostUnderTest.execute();

        // Verify the results
    }
}
