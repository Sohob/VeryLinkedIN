package com.verylinkedin.mypost.GetPost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.commands.GetPost.GetPosts;
import com.verylinkedin.mypost.commands.GetPost.GetPostsRequest;
import com.verylinkedin.mypost.minio.config.MinioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPostsTest {

    @Mock
    private GetPostsRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    @Mock
    private MinioService minioService;
    private GetPosts getPostsUnderTest;

    @BeforeEach
    void setUp() {
        getPostsUnderTest = new GetPosts(mockRequest, minioService, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = getPostsUnderTest.execute();

        // Verify the results
    }
}
