package com.verylinkedin.mypost.commands.GetPost;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioService;
import com.verylinkedin.mypost.models.Media;
import com.verylinkedin.mypost.models.Post;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record GetPost(GetPostRequest request, MinioService minioService, PostRepository postRepository) implements Command {
    public Object execute() {


        // Message build = MessageBuilder.withBody((new String(json)).getBytes()).build();

        Post post =  postRepository.findById(request.postId());

            if (post!=null && post.getMedia()!=null){

                for ( Media media : post.getMedia())
                {
                    media.setHigh_quality_image_id((media.getHigh_quality_link(minioService)));
                    media.setLow_quality_image_id(media.getLow_quality_link(minioService));
                }}

        //byte[] body = message.getBody();
        String json = new Gson().toJson(post );

        // Message build = MessageBuilder.withBody((new String(json)).getBytes()).build();
        return json ;

    }


}
