package com.verylinkedin.mypost.commands.UnlikePost;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnlikePostTest {

    @Mock
    private UnlikePostRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private UnlikePost unlikePostUnderTest;

    @BeforeEach
    void setUp() {
        unlikePostUnderTest = new UnlikePost(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = unlikePostUnderTest.execute();

        // Verify the results
    }
}
