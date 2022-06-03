package com.verylinkedin.mypost.commands.ChangeVisibility;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChangeVisibilityTest {

    @Mock
    private ChangeVisibilityRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private ChangeVisibility changeVisibilityUnderTest;

    @BeforeEach
    void setUp() {
        changeVisibilityUnderTest = new ChangeVisibility(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = changeVisibilityUnderTest.execute();

        // Verify the results
    }
}
