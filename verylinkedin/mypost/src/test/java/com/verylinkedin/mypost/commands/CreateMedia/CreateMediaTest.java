//package com.verylinkedin.mypost.commands.CreateMedia;
//
//import com.verylinkedin.mypost.PostRepository;
//import com.verylinkedin.mypost.minio.config.MinioService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(MockitoExtension.class)
//class CreateMediaTest {
//
//    @Mock
//    private CreateMediaRequest mockCreateMediaRequest;
//    @Mock
//    private MinioService mockMinioService;
//    @Mock
//    private PostRepository mockPostRepository;
//
//    private CreateMedia createMediaUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        createMediaUnderTest = new CreateMedia(mockCreateMediaRequest, mockMinioService, mockPostRepository);
//    }
//
//    @Test
//    void testExecute() {
//        // Setup
//        // Run the test
//        final String result = createMediaUnderTest.execute();
//
//        // Verify the results
//        assertThat(result).isEqualTo("result");
//    }
//
//
//
//}
