package com.verylinkedin.mypost;


import com.google.common.reflect.ClassPath;
import com.verylinkedin.mypost.AddComment.AddComment;
import com.verylinkedin.mypost.AddComment.AddCommentRequest;
import com.verylinkedin.mypost.BanUser.BanUser;
import com.verylinkedin.mypost.BanUser.BanUserRequest;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibility;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.CreateMedia.CreateMedia;
import com.verylinkedin.mypost.CreateMedia.CreateMediaRequest;
import com.verylinkedin.mypost.CreatePost.CreatePost;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.EditPost.EditPost;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import com.verylinkedin.mypost.GetPost.GetPost;
import com.verylinkedin.mypost.GetPost.GetPostRequest;
import com.verylinkedin.mypost.GetPost.GetPosts;
import com.verylinkedin.mypost.GetPost.GetPostsRequest;
import com.verylinkedin.mypost.LikePost.LikePost;
import com.verylinkedin.mypost.LikePost.LikePostRequest;
import com.verylinkedin.mypost.deletePost.DeletePost;
import com.verylinkedin.mypost.deletePost.DeletePostRequest;
//import org.reflections.Reflections;
//import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();

    public CommandMap() throws IOException {
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

    public static Class getCommandClass(String type){
        return commandMap.get(type);
    }
    public static Class getRequestClass(String type){
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
