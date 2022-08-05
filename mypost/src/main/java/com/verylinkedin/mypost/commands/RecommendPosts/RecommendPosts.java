package com.verylinkedin.mypost.commands.RecommendPosts;


import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioService;
import com.verylinkedin.mypost.models.Media;
import com.verylinkedin.mypost.models.Post;
import java.util.List;

public record RecommendPosts(RecommendPostsRequest request, MinioService minioService,
                             PostRepository postRepository) implements Command {
    public String execute() {
        List<Post> result = postRepository.findByLabel(request.label());

        result.removeIf( s -> (s!=null && s.getBanned().contains(request.curUserId())));

        for (Post post : result) {
            if (post.getMedia() != null) {
                for (Media media : post.getMedia()) {
                    media.setHigh_quality_image_id((media.getHigh_quality_link(minioService)));
                    media.setLow_quality_image_id(media.getLow_quality_link(minioService));
                }
            }
        }
        String json = new Gson().toJson(result);
        return json;

    }

}
