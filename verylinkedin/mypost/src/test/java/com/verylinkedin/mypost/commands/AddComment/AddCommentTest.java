package com.verylinkedin.mypost.commands.AddComment;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddCommentTest {

    @Mock
    private AddCommentRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private AddComment addCommentUnderTest;

    @BeforeEach
    void setUp() {
        addCommentUnderTest = new AddComment(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = addCommentUnderTest.execute();

        // Verify the results
    }
}
