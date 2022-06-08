package com.verylinkedin.mypost.commands.CreatePost;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.verylinkedin.mypost.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatePostTest {
    private String userID = "userId";
    private String content = "Hello World";
    @Mock
    private PostRepository mockPostRepository;
    private CreatePost createPostUnderTest;

    @BeforeEach
    void setUp() {
        createPostUnderTest = new CreatePost(new CreatePostRequest(userID, content), mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        final String result = (String) createPostUnderTest.execute();
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        // Verify the results

        assertEquals(content, jsonObject.get("content").getAsString());
        assertEquals(userID, jsonObject.get("userId").getAsString());
    }
}
