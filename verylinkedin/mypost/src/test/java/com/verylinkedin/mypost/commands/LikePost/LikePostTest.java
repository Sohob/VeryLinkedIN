package com.verylinkedin.mypost.commands.LikePost;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LikePostTest {

    @Mock
    private LikePostRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private LikePost likePostUnderTest;

    @BeforeEach
    void setUp() {
        likePostUnderTest = new LikePost(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = likePostUnderTest.execute();

        // Verify the results
    }
}
