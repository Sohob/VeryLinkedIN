package com.verylinkedin.mypost.commands.EditPost;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EditPostTest {

    @Mock
    private EditPostRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private EditPost editPostUnderTest;

    @BeforeEach
    void setUp() {
        editPostUnderTest = new EditPost(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = editPostUnderTest.execute();

        // Verify the results
    }
}
