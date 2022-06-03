package com.verylinkedin.mypost.commands.BanUser;

import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BanUserTest {

    @Mock
    private BanUserRequest mockRequest;
    @Mock
    private PostRepository mockPostRepository;

    private BanUser banUserUnderTest;

    @BeforeEach
    void setUp() {
        banUserUnderTest = new BanUser(mockRequest, mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final Object result = banUserUnderTest.execute();

        // Verify the results
    }
}
