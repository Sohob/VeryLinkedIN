package com.verylinkedin.mypost.models;

import com.verylinkedin.mypost.minio.config.MinioService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.nio.file.Path;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Media {
    @Id
    private String id;
    private String low_quality_image_id = String.valueOf(new ObjectId());
    private String high_quality_image_id = String.valueOf(new ObjectId());
    private String postId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLow_quality_image_id() {
        return low_quality_image_id;
    }

    public void setLow_quality_image_id(String low_quality_image_id) {
        this.low_quality_image_id = low_quality_image_id;
    }

    public String getLow_quality_link(MinioService minioService) {
        return minioService.getURL(Path.of(String.valueOf(low_quality_image_id)));
    }

    public String getHigh_quality_link(MinioService minioService) {
        return minioService.getURL(Path.of(String.valueOf(high_quality_image_id)));

    }

    public String getHigh_quality_image_id() {
        return high_quality_image_id;
    }

    public void setHigh_quality_image_id(String high_quality_image_id) {
        this.high_quality_image_id = high_quality_image_id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
