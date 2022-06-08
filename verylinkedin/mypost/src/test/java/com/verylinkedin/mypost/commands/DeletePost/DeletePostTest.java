package com.verylinkedin.mypost.commands.DeletePost;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureDataMongo
@ExtendWith(MockitoExtension.class)
class DeletePostTest {
    private String postId = "123456";
    private String userId = "123456";
    private String content = "Hello World";
    @Mock
    private PostRepository mockPostRepository;
    private ArrayList<Post> postsRepo = new ArrayList<Post>();
    private DeletePost deletePostUnderTest;
    private CreatePost createPostUnderTest;

    @BeforeEach
    void setUp() {
        createPostUnderTest = new CreatePost(new CreatePostRequest(userId, content), mockPostRepository);
        Post post = Post.builder()
                .userId(userId)
                .content(content)
                .build();
        post.setId(postId);
        postsRepo.add(post);
        mockPostRepository.save(post);
        deletePostUnderTest = new DeletePost(new DeletePostRequest(postId), mockPostRepository);
    }

    @Test
    void testExecute() {
        // Setup
        // Run the test
        String output = "false";
        final String resultCreate = (String) createPostUnderTest.execute();
        JsonObject jsonObject = new JsonParser().parse(resultCreate).getAsJsonObject();
        System.out.println(resultCreate);
        final String result = (String) deletePostUnderTest.execute();
        JsonObject jsonObjectDelete = new JsonParser().parse(result).getAsJsonObject();

//        System.out.println(mockPostRepository.findAll());
//        System.out.println(result);
        assertEquals(jsonObjectDelete.get("success").getAsString(), "true");
        // Verify the results
    }
}
