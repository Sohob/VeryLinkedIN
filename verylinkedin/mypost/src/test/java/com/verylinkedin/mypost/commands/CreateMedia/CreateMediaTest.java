//package com.verylinkedin.mypost.commands.CreateMedia;
//
//import com.verylinkedin.mypost.MediaRepository;
//import com.verylinkedin.mypost.models.Media;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@AutoConfigureDataMongo
//class CreateMediaTest {
//
//    @Mock
//    private CreateMediaRequest mockRequest;
//    @Mock
//    private MediaRepository mockMediaRepository;
//
//    private CreateMedia createMediaUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        createMediaUnderTest = new CreateMedia(mockRequest, mockMediaRepository);
//    }
//
//    @Test
//    void testExecute() {
//        // Setup
//        final Media expectedResult = new Media("id", "type");
//
//        // Run the test
//        final Media result = createMediaUnderTest.execute();
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }
//}
