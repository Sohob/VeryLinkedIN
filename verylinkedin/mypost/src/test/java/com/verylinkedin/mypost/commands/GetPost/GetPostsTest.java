package com.verylinkedin.mypost.commands.GetPost;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GetPostsTest {

    @Mock
    private GetPostsRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private GetPosts getPostsUnderTest;

    @BeforeEach
    void setUp() {
        getPostsUnderTest = new GetPosts(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final String result = getPostsUnderTest.execute();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
