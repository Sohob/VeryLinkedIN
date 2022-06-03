package com.verylinkedin.mypost.commands.DeletePost;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePostTest {

    @Mock
    private DeletePostRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private DeletePost deletePostUnderTest;

    @BeforeEach
    void setUp() {
        deletePostUnderTest = new DeletePost(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = deletePostUnderTest.execute();

        // Verify the results
    }
}
