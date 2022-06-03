package com.verylinkedin.mypost.commands.CreateMedia;

import org.springframework.web.multipart.MultipartFile;

public record CreateMediaRequest(byte[] file , String imageName ,String contentType  , String postId ) {
}
