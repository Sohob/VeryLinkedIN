//package com.verylinkedin.mypost.commands.AddComment;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.verylinkedin.mypost.PostRepository;
//import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
//import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class AddCommentTest {
//    private final String userID = "userId";
//    private final String content = "Hello World";
//    private final String label = "AI";
//    @Mock
//    private PostRepository mockPostRepository;
//    private CreatePost createPostUnderTest;
////    @Mock
////    private AddCommentRequest mockRequest;
//
//    private AddComment addCommentUnderTest;
//
//    @BeforeEach
//    void setUp() {
//
//        createPostUnderTest = new CreatePost(new CreatePostRequest(userID, content,label), mockPostRepository);
//        final String result = (String) createPostUnderTest.execute();
//        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//        System.out.println(jsonObject);
//        addCommentUnderTest = new AddComment(new AddCommentRequest( jsonObject.get("id").getAsString(), userID, "edit"), mockPostRepository);
//    }
//
//    @Test
//    void testExecute() {
//        // Setup
//        // Run the test
//        final Object result = addCommentUnderTest.execute();
//
//        // Verify the results
//    }
//}
