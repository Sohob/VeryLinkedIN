package com.verylinkedin.mypost;


import com.google.common.reflect.ClassPath;
import com.verylinkedin.mypost.commands.AddComment.AddComment;
import com.verylinkedin.mypost.commands.AddComment.AddCommentRequest;
import com.verylinkedin.mypost.commands.BanUser.BanUser;
import com.verylinkedin.mypost.commands.BanUser.BanUserRequest;
import com.verylinkedin.mypost.commands.ChangeVisibility.ChangeVisibility;
import com.verylinkedin.mypost.commands.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.commands.CreateMedia.CreateMedia;
import com.verylinkedin.mypost.commands.CreateMedia.CreateMediaRequest;
import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.commands.DeletePost.DeletePost;
import com.verylinkedin.mypost.commands.DeletePost.DeletePostRequest;
import com.verylinkedin.mypost.commands.EditPost.EditPost;
import com.verylinkedin.mypost.commands.EditPost.EditPostRequest;
import com.verylinkedin.mypost.commands.GetPost.GetPost;
import com.verylinkedin.mypost.commands.GetPost.GetPostRequest;
import com.verylinkedin.mypost.commands.GetPost.GetPosts;
import com.verylinkedin.mypost.commands.GetPost.GetPostsRequest;
import com.verylinkedin.mypost.commands.LikePost.LikePost;
import com.verylinkedin.mypost.commands.LikePost.LikePostRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();

    public CommandMap() throws IOException {

        requestMap.put("com.verylinkedin.core.postsRequests.CreateMediaRequest", CreateMediaRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.CreateMediaRequest", CreateMedia.class);
        requestMap.put("com.verylinkedin.core.postsRequests.AddCommentRequest", AddCommentRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.AddCommentRequest", AddComment.class);
        commandMap.put("com.verylinkedin.core.postsRequests.BanUserRequest", BanUser.class);
        requestMap.put("com.verylinkedin.core.postsRequests.BanUserRequest", BanUserRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.ChangeVisibilityRequest", ChangeVisibility.class);
        requestMap.put("com.verylinkedin.core.postsRequests.ChangeVisibilityRequest", ChangeVisibilityRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.CreateMediaRequest", CreateMedia.class);
        requestMap.put("com.verylinkedin.core.postsRequests.CreateMediaRequest", CreateMediaRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.CreatePostRequest", CreatePost.class);
        requestMap.put("com.verylinkedin.core.postsRequests.CreatePostRequest", CreatePostRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.DeletePostRequest", DeletePost.class);
        requestMap.put("com.verylinkedin.core.postsRequests.DeletePostRequest", DeletePostRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.EditPostRequest", EditPost.class);
        requestMap.put("com.verylinkedin.core.postsRequests.EditPostRequest", EditPostRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.GetPostRequest", GetPost.class);
        requestMap.put("com.verylinkedin.core.postsRequests.GetPostRequest", GetPostRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.GetPostsRequest", GetPosts.class);
        requestMap.put("com.verylinkedin.core.postsRequests.GetPostsRequest", GetPostsRequest.class);
        commandMap.put("com.verylinkedin.core.postsRequests.LikePostRequest", LikePost.class);
        requestMap.put("com.verylinkedin.core.postsRequests.LikePostRequest", LikePostRequest.class);
    }

    public static HashMap<String, Class> getCommandMap() {
        return commandMap;
    }

    public static Class getCommandClass(String type) {
        return commandMap.get(type);
    }

    public static Class getRequestClass(String type) {
        return requestMap.get(type);
    }

    public List<Class> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(clazz -> clazz.load())
                .collect(Collectors.toList());
    }
}
