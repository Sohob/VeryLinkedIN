package com.verylinkedin.mypost.commands.CreateMedia;

public record CreateMediaRequest(byte[] file, String imageName, String contentType, String postId) {
}
