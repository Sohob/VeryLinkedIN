package com.verylinkedin.mypost.commands.GetPost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPostTest {

    @Mock
    private GetPostRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;
    @Mock
    private MinioService minioService;

    private GetPost getPostUnderTest;

    @BeforeEach
    void setUp() {
        getPostUnderTest = new GetPost(mockRequest, minioService,mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = getPostUnderTest.execute();

        // Verify the results
    }
}
