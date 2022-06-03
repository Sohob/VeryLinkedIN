package com.verylinkedin.mypost.commands.CreateMedia;

import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.models.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateMediaTest {

    @Mock
    private CreateMediaRequest mockRequest;
    @Mock
    private MediaRepository mockMediaRepository;

    private CreateMedia createMediaUnderTest;

    @BeforeEach
    void setUp() {
        createMediaUnderTest = new CreateMedia(mockRequest, mockMediaRepository);
    }

    @Test
    void testExecute() {
        // Setup
        final Media expectedResult = new Media("id", "type");

        // Run the test
        final Media result = createMediaUnderTest.execute();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
