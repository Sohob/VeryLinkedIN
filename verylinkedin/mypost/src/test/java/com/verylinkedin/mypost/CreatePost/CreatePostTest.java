package com.verylinkedin.mypost.CreatePost;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreatePostTest {
    private final String userID = "userId";
    private final String content = "Hello World";
    private final String label = "AI";
    @Mock
    private PostRepository mockPostRepository;
    private CreatePost createPostUnderTest;

    @BeforeEach
    void setUp() {
        createPostUnderTest = new CreatePost(new CreatePostRequest(userID, content,label), mockPostRepository);
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
